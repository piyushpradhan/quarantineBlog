package com.example.quarantineblog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_add_blog.*
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import java.io.IOException

class AddBlogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_blog)

        save_blog_fab.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.anim.add_blog_transition_entry, R.anim.add_blog_transition_exit)
        }
    }

//    fun fetchJson() {
//        val url = "http://192.168.43.43/api/articlesarticles/?format=json"
//
//        val request = okhttp3.Request.Builder().url(url).build()
//
//        val client = OkHttpClient()
//        client.newCall(request).enqueue(object : Callback {
//            override fun onResponse(call: Call, response: okhttp3.Response) {
//                val body = response.body?.string()
//                println(body)
//            }
//
//            override fun onFailure(call: Call, e: IOException) {
//                println("Nope, that didn't work!")
//                println(e)
//            }
//        })
//
//    }

    override fun onStart() {
        super.onStart()
//        fetchJson()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.add_blog_transition_entry, R.anim.add_blog_transition_exit)
    }
}