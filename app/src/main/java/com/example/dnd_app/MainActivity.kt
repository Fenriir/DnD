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
import com.example.dnd_app.viewmodels.EditCharViewModel
import com.example.dnd_app.viewmodels.NewCharViewModel
import com.example.dnd_app.viewmodels.RasesDetailViewModel
import com.example.dnd_app.viewmodels.RasesViewModel

class MainActivity : ComponentActivity() {
    private lateinit var charactersViewModel: CharactersViewModel
    private lateinit var charDetailViewModel: CharDetailViewModel
    private lateinit var editCharViewModel: EditCharViewModel
    private lateinit var newCharViewModel: NewCharViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        charactersViewModel = ViewModelProvider(this)[CharactersViewModel::class.java]
        charDetailViewModel = ViewModelProvider(this)[CharDetailViewModel::class.java]
        editCharViewModel = ViewModelProvider(this)[EditCharViewModel::class.java]
        newCharViewModel = ViewModelProvider(this)[NewCharViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            MaterialTheme{
                val navController = rememberNavController()
                AppNavGraph(navController, charactersViewModel, charDetailViewModel,editCharViewModel,newCharViewModel)
            }
        }
    }
}
