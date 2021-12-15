package com.example.airnews.Fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.airnews.ItemsCLicked
import com.example.airnews.Model.DataModel
import com.example.airnews.NewsRvAdapter
import com.example.airnews.databinding.FragmentBaseBinding
import com.example.airnews.repository.repository
import com.example.airnews.viewmodel.mainViewModel
import com.example.airnews.viewmodel.viewModelFactory
import com.example.trial.Model.DataModel.Article
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class F_Technology : Fragment(), ItemsCLicked {

    @Inject
    lateinit var repository: repository

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

        mAdapter = NewsRvAdapter(this)
        binding.rvNews.adapter = mAdapter


        val category = "Technology"
        val mainViewModel = ViewModelProvider(
            this,
            viewModelFactory(repository)
        ).get(mainViewModel::class.java)


        mainViewModel.getTechnologyData(category)

        mainViewModel.TNews.observe(viewLifecycleOwner) {
            Log.d("T-News status:", it.status)

            val Articles: List<Article> = it.articles

            fetchData(Articles)

        }

    }

    private fun fetchData(Articles: List<Article>) {

        val newsarray = ArrayList<DataModel>()
        for (i in 0..Articles.size - 1) {
            if (Articles[i].author == null || Articles[i].urlToImage == null) {
                Articles[i].author = "Not available"
                Articles[i].urlToImage = "Not available"
                Articles[i].url = "Not available"
                Articles[i].title = "Not available"

            }

            val news = DataModel(
                Articles[i].title,
                Articles[i].author,
                Articles[i].url,
                Articles[i].urlToImage
            )

            newsarray.add(news)
            mAdapter.upadateNews(newsarray)
        }
    }

    override fun ClickedItem(item: DataModel) {

        CustomTabsIntent.Builder()
            .build()
            .launchUrl(requireContext(), Uri.parse(item.url))
    }

}
