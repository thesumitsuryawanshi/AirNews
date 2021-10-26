package com.example.airnews.Fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.airnews.ItemsCLicked
import com.example.airnews.Model.DataModel
import com.example.airnews.MySingleton
import com.example.airnews.NewsRvAdapter
import com.example.airnews.databinding.FragmentBaseBinding

class F_Keyword(val keyword: String) : Fragment(), ItemsCLicked {


    lateinit var binding: FragmentBaseBinding
    lateinit var mAdapter: NewsRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         d("F-keyword", "fragment opened")
        mAdapter = NewsRvAdapter(this)
        binding.rvNews.adapter = mAdapter

        fetchData(keyword)

    }

    private fun fetchData(keyword: String) {

        val url =
            "https://newsapi.org/v2/top-headlines?q=${keyword}&apiKey=11c6dba5e88744338808d830416b0b8f"
        // https://newsapi.org/v2/top-headlines?q=trump&apiKey=11c6dba5e88744338808d830416b0b8f


        val jsonObjectRequest =

            object : JsonObjectRequest(Request.Method.GET, url, null, { response ->
                Log.d("test", "its working. \n business news = ${response.getString("status")}")

                val newsjsonarray = response.getJSONArray("articles")
                val newsarray = ArrayList<DataModel>()

                for (i in 0 until newsjsonarray.length()) {
                    val newsjsonobject = newsjsonarray.getJSONObject(i)
                    val news = DataModel(
                        newsjsonobject.getString("title"),
                        newsjsonobject.getString("author"),
                        newsjsonobject.getString("url"),
                        newsjsonobject.getString("urlToImage")
                    )
                    newsarray.add(news)
                }
                mAdapter.upadateNews(newsarray)
            }, { error ->
                Log.d("test", "couldnt fetch the data ")

            }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["User-Agent"] = "Mozilla/5.0"
                    return headers
                }
            }

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(requireContext()).addToRequestQueue(jsonObjectRequest)
    }

    override fun ClickedItem(item: DataModel) {

        CustomTabsIntent.Builder()
            .build()
            .launchUrl(requireContext(), Uri.parse(item.url))
    }
}
