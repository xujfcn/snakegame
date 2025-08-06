package com.example.snakegame

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class GameDifficulty {
    EASY, MEDIUM, HARD
}

class GameSettings {
    var difficulty by mutableStateOf(GameDifficulty.MEDIUM)
    var soundEnabled by mutableStateOf(true)
    var vibrationEnabled by mutableStateOf(true)
    
    fun getGameSpeed(): Long {
        return when (difficulty) {
            GameDifficulty.EASY -> 300L
            GameDifficulty.MEDIUM -> 200L
            GameDifficulty.HARD -> 150L
        }
    }
    
    fun getScoreMultiplier(): Int {
        return when (difficulty) {
            GameDifficulty.EASY -> 5
            GameDifficulty.MEDIUM -> 10
            GameDifficulty.HARD -> 15
        }
    }
} 