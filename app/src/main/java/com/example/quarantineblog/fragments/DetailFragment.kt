package com.example.quarantineblog.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quarantineblog.R
import com.example.quarantineblog.database.BlogViewModel
import com.example.quarantineblog.models.BlogModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private val args by navArgs<DetailFragmentArgs>()

    private var mood: Any? = null
    private lateinit var blogViewModel: BlogViewModel

    private lateinit var blog_heading_et: EditText
    private lateinit var blog_content_et: EditText
    private lateinit var blog_date_et: TextView
    private lateinit var mood_rating_1: FloatingActionButton
    private lateinit var mood_rating_2: FloatingActionButton
    private lateinit var mood_rating_3: FloatingActionButton
    private lateinit var mood_rating_4: FloatingActionButton
    private lateinit var mood_rating_5: FloatingActionButton
    private lateinit var save_blog_fab: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        blogViewModel = ViewModelProvider(this).get(BlogViewModel::class.java)

        blog_heading_et = view.findViewById(R.id.blog_heading_et)
        blog_content_et = view.findViewById(R.id.blog_content_et)
        blog_date_et = view.findViewById(R.id.blog_date_et)

        mood_rating_1 = view.findViewById(R.id.mood_rating_1)
        mood_rating_2 = view.findViewById(R.id.mood_rating_2)
        mood_rating_3 = view.findViewById(R.id.mood_rating_3)
        mood_rating_4 = view.findViewById(R.id.mood_rating_4)
        mood_rating_5 = view.findViewById(R.id.mood_rating_5)

        save_blog_fab = view.findViewById(R.id.save_blog_fab)

        blog_heading_et.setText(args.currentBlog.title)
        blog_content_et.setText(args.currentBlog.desc)
        blog_date_et.setText(args.currentBlog.date)

        save_blog_fab.setOnClickListener {
            updateBlog()
        }

        mood_rating_1.setOnClickListener {
            mood = Color.parseColor("#ff0000")
            ask_mood.setTextColor(Integer.parseInt(mood.toString()))
        }

        mood_rating_2.setOnClickListener {
            mood = Color.parseColor("#FF9529")
            ask_mood.setTextColor(Integer.parseInt(mood.toString()))
        }

        mood_rating_3.setOnClickListener {
            mood = Color.parseColor("#FDCC0D")
            ask_mood.setTextColor(Integer.parseInt(mood.toString()))
        }

        mood_rating_4.setOnClickListener {
            mood = Color.parseColor("#FFDF00")
            ask_mood.setTextColor(Integer.parseInt(mood.toString()))
        }

        mood_rating_5.setOnClickListener {
            mood = Color.parseColor("#0BDA51")
            ask_mood.setTextColor(Integer.parseInt(mood.toString()))
        }

        return view
    }

    private fun updateBlog() {
        val title = blog_heading_et.text.toString()
        val content = blog_content_et.text.toString()

        if(inputCheck(title, content)) {
            val updatedBlog = BlogModel(args.currentBlog.id, title.toString(), content.toString(), args.currentBlog.date, Integer.parseInt(mood.toString()))
            blogViewModel.updateBlog(blogModel = updatedBlog)

            Toast.makeText(requireContext(), "Blog Updated Successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "No field(s) must remain empty!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(title: String, content: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(content))
    }
}