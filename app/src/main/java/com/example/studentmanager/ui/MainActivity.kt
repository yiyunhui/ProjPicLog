package com.example.studentmanager.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studentmanager.ui.theme.StudentManagerTheme
import com.example.studentmanager.ui.screens.HomeScreen
import com.example.studentmanager.ui.screens.StudentSearchScreen
import com.example.studentmanager.ui.screens.ImportScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentManagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf("home") }

    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    selected = currentScreen == "home",
                    onClick = { currentScreen = "home" },
                    icon = { /* 添加图标 */ },
                    label = { Text("首页") }
                )
                BottomNavigationItem(
                    selected = currentScreen == "search",
                    onClick = { currentScreen = "search" },
                    icon = { /* 添加图标 */ },
                    label = { Text("查询") }
                )
                BottomNavigationItem(
                    selected = currentScreen == "import",
                    onClick = { currentScreen = "import" },
                    icon = { /* 添加图标 */ },
                    label = { Text("导入") }
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (currentScreen) {
                "home" -> HomeScreen()
                "search" -> StudentSearchScreen()
                "import" -> ImportScreen()
            }
        }
    }
} 