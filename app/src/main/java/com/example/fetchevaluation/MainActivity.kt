package com.example.fetchevaluation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.fetchevaluation.ui.theme.FetchEvaluationTheme
import com.example.fetchevaluation.ui.theme.ItemListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchEvaluationTheme {
                ItemListScreen()
            }
        }
    }
}