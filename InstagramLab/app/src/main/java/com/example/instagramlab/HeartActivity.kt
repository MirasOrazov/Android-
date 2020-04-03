package com.example.instagramlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class HeartActivity : BaseActivity(3) {
    private val TAG="HeartActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heart)
        setupBottomNavigation()
        Log.d(TAG,"onCreate")
    }
}
