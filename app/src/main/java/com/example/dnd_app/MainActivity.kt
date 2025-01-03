package com.example.dnd_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.dnd_app.ui.theme.DnD_appTheme
import com.example.dnd_app.viewmodels.CharDetailViewModel
import com.example.dnd_app.viewmodels.CharactersViewModel

class MainActivity : ComponentActivity() {
    private lateinit var charactersViewModel: CharactersViewModel
    private lateinit var charDetailViewModel: CharDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        charactersViewModel = ViewModelProvider(this)[CharactersViewModel::class.java]
        charactersViewModel = ViewModelProvider(this)[CharactersViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            MaterialTheme{
                val navController = rememberNavController()
                AppNavGraph(navController, charactersViewModel, charDetailViewModel)
            }
        }
    }
}
