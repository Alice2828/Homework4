package com.example.hw4

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class FragmentLike : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LikedListAdapter
    private var listener: LikedListAdapter.ItemClickListener? = null
    private var fragmentLikeListener: LikedListAdapter.FragmentLikeListener? = null
    private var newsList: ArrayList<News>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        val rootView =
            inflater.inflate(R.layout.fragment_fragment_like, container, false) as ViewGroup
        recyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(rootView.context)
        listener = object : LikedListAdapter.ItemClickListener {
            override fun itemClick(position: Int, item: News?) {
                val intent = Intent(activity, NewsDetailActivity::class.java)
                intent.putExtra("news", item)
                startActivity(intent)
            }
        }
        fragmentLikeListener = object : LikedListAdapter.FragmentLikeListener {
            override fun removeItemLike(news: News?) {
                (activity as MainActivity?)?.removeItemLike(news)
            }
        }
        newsList = ArrayList()
        adapter = LikedListAdapter(newsList, listener, fragmentLikeListener)
        recyclerView.adapter=adapter
        return rootView
    }


    fun saveNews(news: News?) {
        if (news != null) {
            newsList?.add(news)
        }
        recyclerView.adapter?.notifyItemInserted(newsList!!.size - 1)
    }

    fun removeNews(news: News?) {
        if (newsList!!.indexOf(news) == 0) {
            newsList?.remove(news)
        } else {
            val position = newsList!!.indexOf(news)
            newsList?.remove(news)
            recyclerView.adapter?.notifyItemRemoved(position)
        }
    }

    fun removeLike(news: News?) {
        val n = newsList!!.indexOf(news)
        removeNews(news)
        adapter.notifyItemRemoved(n)
    }

}