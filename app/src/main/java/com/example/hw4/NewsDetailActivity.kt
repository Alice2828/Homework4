package com.example.hw4

import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import java.lang.String


class NewsDetailActivity : AppCompatActivity() {
    private var imageViewIntentResult: ImageView? = null
    private var logo: ImageView? = null
    private var author: TextView? = null
    private var likes: TextView? = null
    private var comments: TextView? = null
    private var data: TextView? = null
    private var likeBtn: ImageView? = null
    private var backBtn: Button? = null
    private lateinit var news: News
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        bindView()
        intents()
        buttons()
    }

    private fun intents() {
        news = intent.getSerializableExtra("news") as News
        Glide.with(this).load(news.image).into(imageViewIntentResult as ImageView)
        Glide.with(this).load(news.logo).into(logo as ImageView)
        author?.text = news.author
        likes?.text = "Нравится: " + String.valueOf(news.likesCnt)
        comments?.text = "Посмотреть все комментарии(30)"
        val s =
            "<b>" + news.author.toString() + "</b>" + " " + news.data
        data?.text = Html.fromHtml(s)
    }

    private fun bindView() {
        backBtn = findViewById(R.id.backBtn)
        imageViewIntentResult = findViewById(R.id.image)
        logo = findViewById(R.id.logo)
        author = findViewById(R.id.author)
        likes = findViewById(R.id.likes)
        comments = findViewById(R.id.comments)
        data = findViewById(R.id.data)
        likeBtn = findViewById(R.id.likeBtn)
    }

    private fun buttons() {
        backBtn?.setOnClickListener( { onBackPressed() })
        if (Singleton.newsList[Singleton.newsList.indexOf(news)].hearted)
            likeBtn?.setImageResource(R.drawable.hearted)
        else likeBtn?.setImageResource(R.drawable.heart)

    }
}