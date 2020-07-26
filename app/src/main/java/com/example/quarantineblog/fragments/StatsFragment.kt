package com.example.quarantineblog.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quarantineblog.R
import com.example.quarantineblog.database.BlogViewModel
import com.example.quarantineblog.models.BlogModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_detail.*

class StatsFragment : Fragment() {

    private var mood: Int? = null
    private val args by navArgs<StatsFragmentArgs>()

    private lateinit var blogViewModel: BlogViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stats, container, false)

        blogViewModel = ViewModelProvider(this).get(BlogViewModel::class.java)

        val go_to_home_fab = view.findViewById<FloatingActionButton>(R.id.go_home)
        val mood_rating_1 = view.findViewById<FloatingActionButton>(R.id.mood_rating_1)
        val mood_rating_2 = view.findViewById<FloatingActionButton>(R.id.mood_rating_2)
        val mood_rating_3 = view.findViewById<FloatingActionButton>(R.id.mood_rating_3)
        val mood_rating_4 = view.findViewById<FloatingActionButton>(R.id.mood_rating_4)
        val mood_rating_5 = view.findViewById<FloatingActionButton>(R.id.mood_rating_5)

        go_to_home_fab.setOnClickListener {
            updateMood()
        }

        mood_rating_1.setOnClickListener {
            mood = Color.parseColor("#ff0000")
        }

        mood_rating_2.setOnClickListener {
            mood = Color.parseColor("#FF9529")
        }

        mood_rating_3.setOnClickListener {
            mood = Color.parseColor("#FDCC0D")
        }

        mood_rating_4.setOnClickListener {
            mood = Color.parseColor("#FFDF00")
        }

        mood_rating_5.setOnClickListener {
            mood = Color.parseColor("#0BDA51")
        }

        return view;
    }

    private fun updateMood() {
        if(mood == null) {
            findNavController().navigate(R.id.action_statsFragment_to_homeFragment)
        } else {
            val blogModel = BlogModel(
                id=0,
                title = args.newBlog.title.toString(),
                desc = args.newBlog.desc.toString(),
                date = args.newBlog.date.toString(),
                mood = Integer.parseInt(mood.toString())
            )

            blogViewModel.updateBlog(blogModel)
            Toast.makeText(requireContext(), "Have a good day :)", Toast.LENGTH_SHORT)


            findNavController().navigate(R.id.action_statsFragment_to_homeFragment)
        }
    }

}