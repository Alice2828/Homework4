package com.example.hw4

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PageFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var adapter: NewsListAdapter? = null
    private var listener: NewsListAdapter.ItemClickListener? = null
    private var fragmentButtonListener: NewsListAdapter.FragmentButtonListener? = null
    private var fragmentLikeListener: NewsListAdapter.FragmentLikeListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(
            R.layout.fragment_page, container, false
        )
        bindView(rootView)

        listener = object : NewsListAdapter.ItemClickListener {
            override fun itemClick(position: Int, item: News?) {
                val intent = Intent(rootView.context, NewsDetailActivity::class.java)
                intent.putExtra("news", item)
                startActivity(intent)
            }
        }
        fragmentButtonListener = object : NewsListAdapter.FragmentButtonListener {
            override fun myClick(news: News?, option: Int) {
                (activity as MainActivity?)!!.myClick(news, option)
            }
        }
        fragmentLikeListener = object : NewsListAdapter.FragmentLikeListener {
            override fun removeItemLike(news: News?) {
                (activity as MainActivity?)!!.removeItemLike(news)
            }
        }
        adapter =
            NewsListAdapter(
                Singleton.newsList,
                listener as NewsListAdapter.ItemClickListener,
                fragmentButtonListener as NewsListAdapter.FragmentButtonListener,
                fragmentLikeListener
            )
        recyclerView?.adapter = adapter
        return rootView
    }


    fun removeLike(news: News?) {
        adapter!!.removeLike(news!!)
    }

    fun bindView(rootView:View) {
        recyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(rootView.context)
    }
}