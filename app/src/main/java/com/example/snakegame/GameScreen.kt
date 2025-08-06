package com.example.snakegame

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun GameScreen(
    gameState: GameState,
    onBackToStart: () -> Unit,
    onOpenSettings: () -> Unit
) {
    
    // Game loop
    LaunchedEffect(gameState.settings.difficulty) {
        while (true) {
            delay(gameState.settings.getGameSpeed())
            gameState.moveSnake()
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Score and controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Score display
            Column {
                Text(
                    text = "Score: ${gameState.score}",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "High Score: ${gameState.highScore}",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
            
            // Control buttons
            Row {
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = { onBackToStart() },
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = Color(0xFF333333),
                            shape = CircleShape
                        )
                ) {
                    Text(
                        text = "←",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
                
                IconButton(
                    onClick = { gameState.togglePause() },
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = Color(0xFF333333),
                            shape = CircleShape
                        )
                ) {
                    Text(
                        text = if (gameState.gameStatus == GameStatus.PLAYING) "⏸" else "▶",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
                
                IconButton(
                    onClick = { gameState.resetGame() },
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = Color(0xFF333333),
                            shape = CircleShape
                        )
                ) {
                    Text(
                        text = "🔄",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
                
                IconButton(
                    onClick = { onOpenSettings() },
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = Color(0xFF333333),
                            shape = CircleShape
                        )
                ) {
                    Text(
                        text = "⚙️",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Game board with touch controls
        GameBoard(
            gameState = gameState,
            modifier = Modifier.swipeToControl { direction ->
                gameState.changeDirection(direction)
            }
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Direction controls
        DirectionControls(gameState = gameState)
        
        // Game status overlay
        if (gameState.gameStatus == GameStatus.GAME_OVER) {
            GameOverOverlay(gameState = gameState)
        }
        

    }
}

@Composable
fun GameBoard(
    gameState: GameState,
    modifier: Modifier = Modifier
) {
    val gridSize = gameState.getGridSize()
    val cellSize = gameState.getCellSize()
    
    Canvas(
        modifier = modifier
            .size((gridSize * cellSize).dp)
            .border(2.dp, Color(0xFF444444), RoundedCornerShape(8.dp))
            .background(Color(0xFF2A2A2A), RoundedCornerShape(8.dp))
    ) {
        // Draw grid lines
        for (i in 0..gridSize) {
            drawLine(
                color = Color(0xFF333333),
                start = Offset(i * cellSize, 0f),
                end = Offset(i * cellSize, gridSize * cellSize),
                strokeWidth = 1f
            )
            drawLine(
                color = Color(0xFF333333),
                start = Offset(0f, i * cellSize),
                end = Offset(gridSize * cellSize, i * cellSize),
                strokeWidth = 1f
            )
        }
        
        // Draw snake
        gameState.snake.forEachIndexed { index, position ->
            val color = if (index == 0) Color(0xFF4CAF50) else Color(0xFF8BC34A)
            drawRect(
                color = color,
                topLeft = Offset(
                    position.x * cellSize + 1f,
                    position.y * cellSize + 1f
                ),
                size = androidx.compose.ui.geometry.Size(
                    cellSize - 2f,
                    cellSize - 2f
                )
            )
        }
        
        // Draw food
        drawCircle(
            color = Color(0xFFF44336),
            radius = cellSize / 3,
            center = Offset(
                gameState.food.x * cellSize + cellSize / 2,
                gameState.food.y * cellSize + cellSize / 2
            )
        )
    }
}

@Composable
fun DirectionControls(gameState: GameState) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Up button
        DirectionButton(
            onClick = { gameState.changeDirection(Direction.UP) },
            modifier = Modifier.size(60.dp)
        ) {
            Text("↑", fontSize = 24.sp, color = Color.White)
        }
        
        Row {
            // Left button
            DirectionButton(
                onClick = { gameState.changeDirection(Direction.LEFT) },
                modifier = Modifier.size(60.dp)
            ) {
                Text("←", fontSize = 24.sp, color = Color.White)
            }
            
            Spacer(modifier = Modifier.width(8.dp))
            
            // Right button
            DirectionButton(
                onClick = { gameState.changeDirection(Direction.RIGHT) },
                modifier = Modifier.size(60.dp)
            ) {
                Text("→", fontSize = 24.sp, color = Color.White)
            }
        }
        
        // Down button
        DirectionButton(
            onClick = { gameState.changeDirection(Direction.DOWN) },
            modifier = Modifier.size(60.dp)
        ) {
            Text("↓", fontSize = 24.sp, color = Color.White)
        }
    }
}

@Composable
fun DirectionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = Color(0xFF333333),
                shape = CircleShape
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun GameOverOverlay(gameState: GameState) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(32.dp)
                .background(Color(0x80000000)),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF2A2A2A)
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Game Over!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Final Score: ${gameState.score}",
                    fontSize = 18.sp,
                    color = Color.White
                )
                
                if (gameState.score > gameState.highScore) {
                    Text(
                        text = "New High Score!",
                        fontSize = 16.sp,
                        color = Color(0xFF4CAF50),
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Button(
                    onClick = { gameState.resetGame() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50)
                    )
                ) {
                    Text("Play Again", color = Color.White)
                }
            }
        }
    }
} 