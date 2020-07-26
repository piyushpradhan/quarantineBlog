package com.example.quarantineblog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.quarantineblog.fragments.HomeFragmentDirections
import com.example.quarantineblog.models.BlogModel
import kotlinx.android.synthetic.main.single_blog_item.view.*

class BlogAdapter: RecyclerView.Adapter<BlogAdapter.ViewHolder>() {

    private var blogsList = emptyList<BlogModel>()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val heading: TextView = itemView.findViewById(R.id.blog_heading)
        val desc: TextView = itemView.findViewById(R.id.blog_content)
        val date: TextView = itemView.findViewById(R.id.blog_date)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_blog_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return blogsList.size
    }

    override fun onBindViewHolder(holder: BlogAdapter.ViewHolder, position: Int) {
        holder.heading.text = blogsList[position].title
        holder.desc.text = blogsList[position].desc
        holder.date.text = blogsList[position].date
        holder.date.setBackgroundColor(blogsList[position].mood)

        holder.itemView.single_blog.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(blogsList[position])
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(blogs: List<BlogModel>) {
        this.blogsList = blogs
        notifyDataSetChanged()
    }

}