package com.example.snakegame

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.random.Random

data class Position(val x: Int, val y: Int)

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

enum class GameStatus {
    PLAYING, GAME_OVER, PAUSED
}

class GameState {
    private val gridSize = 20
    private val cellSize = 30f
    
    var snake by mutableStateOf(listOf(Position(10, 10)))
    var food by mutableStateOf(generateFood())
    var direction by mutableStateOf(Direction.RIGHT)
    var gameStatus by mutableStateOf(GameStatus.PLAYING)
    var score by mutableStateOf(0)
    var highScore by mutableStateOf(0)
    var settings by mutableStateOf(GameSettings())
    
    private fun generateFood(): Position {
        var newFood: Position
        do {
            newFood = Position(
                Random.nextInt(0, gridSize),
                Random.nextInt(0, gridSize)
            )
        } while (snake.contains(newFood))
        return newFood
    }
    
    fun moveSnake() {
        if (gameStatus != GameStatus.PLAYING) return
        
        val head = snake.first()
        val newHead = when (direction) {
            Direction.UP -> Position(head.x, (head.y - 1 + gridSize) % gridSize)
            Direction.DOWN -> Position(head.x, (head.y + 1) % gridSize)
            Direction.LEFT -> Position((head.x - 1 + gridSize) % gridSize, head.y)
            Direction.RIGHT -> Position((head.x + 1) % gridSize, head.y)
        }
        
        // Check collision with self
        if (snake.contains(newHead)) {
            gameStatus = GameStatus.GAME_OVER
            if (score > highScore) {
                highScore = score
            }
            return
        }
        
        val newSnake = mutableListOf<Position>()
        newSnake.add(newHead)
        newSnake.addAll(snake)
        
        // Check if food is eaten
        if (newHead == food) {
            score += settings.getScoreMultiplier()
            food = generateFood()
        } else {
            newSnake.removeAt(newSnake.size - 1)
        }
        
        snake = newSnake
    }
    
    fun changeDirection(newDirection: Direction) {
        // Prevent 180-degree turns
        val opposite = when (direction) {
            Direction.UP -> Direction.DOWN
            Direction.DOWN -> Direction.UP
            Direction.LEFT -> Direction.RIGHT
            Direction.RIGHT -> Direction.LEFT
        }
        
        if (newDirection != opposite) {
            direction = newDirection
        }
    }
    
    fun resetGame() {
        snake = listOf(Position(10, 10))
        food = generateFood()
        direction = Direction.RIGHT
        gameStatus = GameStatus.PLAYING
        score = 0
    }
    
    fun togglePause() {
        if (gameStatus == GameStatus.PLAYING) {
            gameStatus = GameStatus.PAUSED
        } else if (gameStatus == GameStatus.PAUSED) {
            gameStatus = GameStatus.PLAYING
        }
    }
    
    fun getGridSize() = gridSize
    fun getCellSize() = cellSize
} 