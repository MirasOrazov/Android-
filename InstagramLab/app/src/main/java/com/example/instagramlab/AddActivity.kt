package com.example.instagramlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class AddActivity : BaseActivity(2) {
    private val TAG="AddActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setupBottomNavigation()
        Log.d(TAG,"onCreate")
    }
}
