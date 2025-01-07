package com.example.fetchevaluation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.activity.ComponentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val textView: TextView = findViewById(R.id.splash_textview)
        setTextViewAnimation(textView, this@SplashActivity)
        // Using Coroutine to delay execution
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun setTextViewAnimation(textView: TextView, context: Context) {
        val fadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        textView.visibility = TextView.VISIBLE
        textView.startAnimation(fadeInAnimation)
    }
}