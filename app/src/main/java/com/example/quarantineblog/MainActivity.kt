package com.example.quarantineblog

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.quarantineblog.database.BlogViewModel
import com.google.android.material.internal.ContextUtils.getActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mBlogViewModel: BlogViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.fragment))

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.add_blog_transition_entry, R.anim.add_blog_transition_exit)
    }
}