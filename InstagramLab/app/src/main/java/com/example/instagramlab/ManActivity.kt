package com.example.instagramlab

import android.os.Bundle
import android.util.Log

class ManActivity : BaseActivity(4) {
    private val TAG="ManActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_man)
        setupBottomNavigation()
        Log.d(TAG,"onCreate")
    }
}
