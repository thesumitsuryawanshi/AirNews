package com.example.airnews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.airnews.Model.DataModel
import com.example.airnews.databinding.RecyclerViewItemBinding

class NewsRvAdapter(private val listener: ItemsCLicked) :
    RecyclerView.Adapter<NewsRvAdapter.ViewHolder>() {


    private val newsdata: ArrayList<DataModel> = ArrayList()

    class ViewHolder(binding: RecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val heading = binding.tvHeading
        val headlineImage = binding.ivHeadlineImage
        val author = binding.tvAuthor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewholder = ViewHolder(view)

        view.root.setOnClickListener { listener.ClickedItem(newsdata[viewholder.adapterPosition]) }
        return viewholder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNews = newsdata[position]

        holder.itemView.rootView.setOnClickListener {
            listener.ClickedItem(currentNews)
        }
        holder.heading.text = currentNews.title

        if (currentNews.author == "null") holder.author.text = "";  else holder.author.text = currentNews.author

        Glide.with(holder.itemView.context)
            .load(currentNews.Imgurl)
            .into(holder.headlineImage)

    }

    override fun getItemCount(): Int {
        return newsdata.size
    }

    fun upadateNews(pNewsData: ArrayList<DataModel>) {
        newsdata.clear()
        newsdata.addAll(pNewsData)

        notifyDataSetChanged()
    }
}

interface ItemsCLicked {
    fun ClickedItem(item: DataModel) {

    }
}