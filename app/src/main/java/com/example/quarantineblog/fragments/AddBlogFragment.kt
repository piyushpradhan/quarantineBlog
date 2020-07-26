package com.example.quarantineblog.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quarantineblog.R
import com.example.quarantineblog.database.BlogViewModel
import com.example.quarantineblog.models.BlogModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_add_blog.*
import java.text.SimpleDateFormat
import java.util.*


class AddBlogFragment : Fragment() {

    private lateinit var mBlogViewModel: BlogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_add_blog, container, false)

        mBlogViewModel = ViewModelProvider(this).get(BlogViewModel::class.java)

        val save_blog = view.findViewById<FloatingActionButton>(R.id.save_blog_fab)

        save_blog.setOnClickListener { addBlog() }
        return view
    }

    private fun addBlog() {
        val dateformat = SimpleDateFormat("dd\nMMM")
        val date = dateformat.format(Date())

        val blog = BlogModel(
            0,
            blog_heading_et.text.toString(),
            blog_content_et.text.toString(),
            date,
            mood = Color.parseColor("#000000")
        )
        if(blog_heading_et.text.toString().trim() != "" && blog_content_et.text.toString().trim() != "") {
            mBlogViewModel.addBlog(blogModel = blog)

            blog_heading_et.text = null
            blog_content_et.text = null

            Toast.makeText(requireContext(), "Blog added successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addBlogFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all the fields", Toast.LENGTH_SHORT).show()
        }

    }
}