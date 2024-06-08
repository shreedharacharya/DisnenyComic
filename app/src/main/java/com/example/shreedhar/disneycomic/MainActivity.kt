package com.example.shreedhar.disneycomic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.shreedhar.disneycomic.app.theme.DisneyComicTheme
import com.example.shreedhar.disneycomic.presentation.view.ComicDetailsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DisneyComicTheme {
                ComicDetailsScreen(103948)
            }
        }
    }
}