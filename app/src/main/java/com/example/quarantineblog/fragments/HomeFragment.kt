package com.example.quarantineblog.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quarantineblog.BlogAdapter
import com.example.quarantineblog.R
import com.example.quarantineblog.database.BlogViewModel
import com.example.quarantineblog.models.BlogModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var mBlogViewModel: BlogViewModel

    private var mood: Any? = null
    private lateinit var main_title_et: EditText
    private lateinit var main_content_et: EditText
    private lateinit var add_blog_background: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val blogs_recycler_view = view.findViewById<RecyclerView>(R.id.blogs_recycler_view)
        val main_save_blog_fab = view.findViewById<FloatingActionButton>(R.id.main_save_blog_fab)

        main_title_et = view.findViewById(R.id.main_title_et)
        main_content_et = view.findViewById(R.id.main_content_et)

        val mood_rating_1 = view.findViewById<FloatingActionButton>(R.id.mood_rating_1)
        val mood_rating_2 = view.findViewById<FloatingActionButton>(R.id.mood_rating_2)
        val mood_rating_3 = view.findViewById<FloatingActionButton>(R.id.mood_rating_3)
        val mood_rating_4 = view.findViewById<FloatingActionButton>(R.id.mood_rating_4)
        val mood_rating_5 = view.findViewById<FloatingActionButton>(R.id.mood_rating_5)

        val nav_to_stats_fab = view.findViewById<FloatingActionButton>(R.id.stats_fragment_fab)

        add_blog_background = view.findViewById<CardView>(R.id.cardView)

        val adapter = BlogAdapter()
        blogs_recycler_view.adapter = adapter
        blogs_recycler_view.layoutManager = LinearLayoutManager(requireContext())

        mBlogViewModel = ViewModelProvider(this).get(BlogViewModel::class.java)
        mBlogViewModel.readAllData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { blog ->
            adapter.setData(blog)
        })

        mood_rating_1.setOnClickListener {
            mood = Color.parseColor("#ff0000")
            add_blog_background.setCardBackgroundColor(Color.parseColor("#ff0000"))
            main_title_et.setTextColor(Color.parseColor("#FFFFFF"))
            main_content_et.setTextColor(Color.parseColor("#FFFFFF"))
        }

        mood_rating_2.setOnClickListener {
            mood = Color.parseColor("#FF9529")
            add_blog_background.setCardBackgroundColor(Color.parseColor("#FF9529"))
            main_title_et.setTextColor(Color.parseColor("#FFFFFF"))
            main_content_et.setTextColor(Color.parseColor("#FFFFFF"))
        }

        mood_rating_3.setOnClickListener {
            mood = Color.parseColor("#FDCC0D")
            add_blog_background.setCardBackgroundColor(Color.parseColor("#FDCC0D"))
            main_title_et.setTextColor(Color.parseColor("#FFFFFF"))
            main_content_et.setTextColor(Color.parseColor("#FFFFFF"))
        }

        mood_rating_4.setOnClickListener {
            mood = Color.parseColor("#FFDF00")
            add_blog_background.setCardBackgroundColor(Color.parseColor("#FFDF00"))
            main_title_et.setTextColor(Color.parseColor("#FFFFFF"))
            main_content_et.setTextColor(Color.parseColor("#ffffff"))
        }

        mood_rating_5.setOnClickListener {
            mood = Color.parseColor("#0BDA51")
            add_blog_background.setCardBackgroundColor(Color.parseColor("#0BDA51"))
            main_title_et.setTextColor(Color.parseColor("#FFFFFF"))
            main_content_et.setTextColor(Color.parseColor("#ffffff"))
        }

        main_save_blog_fab.setOnClickListener {
            addBlog()
        }

        nav_to_stats_fab.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_statsFragment)
        }

        return view
    }

    private fun inputCheck(title: String, content: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(content))
    }

    private fun addBlog() {
        val dateformat = SimpleDateFormat("dd\nMMM")
        val date = dateformat.format(Date())

        if(mood == null) {
            mood = Color.parseColor("#000000")
        }

        val blog = BlogModel(
            0,
            main_title_et.text.toString(),
            main_content_et.text.toString(),
            date,
            Integer.parseInt(mood.toString())
        )

        if(main_title_et.text.toString() != "" && main_content_et.text.toString() != "") {
            mBlogViewModel.addBlog(blog)
            main_title_et.text.clear()
            main_content_et.text.clear()

            if(mood == Color.parseColor("#ff0000") || mood == Color.parseColor("#FF9529")) {
                val action = HomeFragmentDirections.actionHomeFragmentToStatsFragment(blog)
                findNavController().navigate(action)
            }

            Toast.makeText(requireContext(), "Blog added successfully", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(requireContext(), "Please fill out all the fields", Toast.LENGTH_SHORT).show()
        }
        mood = Color.parseColor("#000000")
        add_blog_background.setCardBackgroundColor(Color.parseColor("#ffffff"))
        main_title_et.setTextColor(Color.parseColor("#000000"))
        main_content_et.setTextColor(Color.parseColor("#000000"))
    }
}