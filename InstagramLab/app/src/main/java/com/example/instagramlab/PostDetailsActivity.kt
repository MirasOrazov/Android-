package com.example.instagramlab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramlab.model.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class PostDetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        val intent:Intent = getIntent()
        val mName = intent.getStringExtra("name")
        val mUsername = intent.getStringExtra("username")
        val mText = intent.getStringExtra("text")
        val mProsmotri = intent.getStringExtra("prosmotri")
        val mAvatar = intent.getStringExtra("avatar")
        val mPhoto = intent.getStringExtra("photo")
        val mTime = intent.getStringExtra("time")

        val username:TextView = findViewById(R.id.username)
        val text:TextView = findViewById(R.id.text)
        val prosmotri:TextView = findViewById(R.id.prosmotri)
        val name:TextView = findViewById(R.id.name)
        val avatar:ImageView = findViewById(R.id.avatar)
        val photo:ImageView = findViewById(R.id.photo)
        val time:TextView = findViewById(R.id.time)

        username.setText(mUsername)
        text.setText(mText)
        prosmotri.setText(mProsmotri)
        name.setText(mName)
        time.setText(mTime)
        Picasso.get().load(mAvatar).into(avatar)
        Picasso.get().load(mPhoto).into(photo)



    }
}



