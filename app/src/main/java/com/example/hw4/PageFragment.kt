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
        adapter =
            NewsListAdapter(
                Singleton.newsList,
                listener as NewsListAdapter.ItemClickListener
            )
        recyclerView?.adapter = adapter
        return rootView
    }

    private fun bindView(rootView: View) {
        recyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(rootView.context)
    }
}