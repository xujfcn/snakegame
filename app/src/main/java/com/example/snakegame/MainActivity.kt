package com.example.snakegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.snakegame.ui.theme.SnakegameTheme

enum class Screen {
    START, GAME, SETTINGS
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SnakegameTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppContent()
                }
            }
        }
    }
}

@Composable
fun AppContent() {
    var currentScreen by remember { mutableStateOf(Screen.START) }
    val gameState = remember { GameState() }
    
    when (currentScreen) {
        Screen.START -> {
            StartScreen(
                onStartGame = { currentScreen = Screen.GAME },
                onOpenSettings = { currentScreen = Screen.SETTINGS }
            )
        }
        Screen.GAME -> {
            GameScreen(
                gameState = gameState,
                onBackToStart = { currentScreen = Screen.START },
                onOpenSettings = { currentScreen = Screen.SETTINGS }
            )
        }
        Screen.SETTINGS -> {
            SettingsScreen(
                gameSettings = gameState.settings,
                onBackPressed = { currentScreen = Screen.START }
            )
        }
    }
}