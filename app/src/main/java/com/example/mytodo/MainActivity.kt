@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mytodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mytodo.ui.theme.MyTodoTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            MyTodoTheme {
                val lesSo = remember { mutableStateListOf(Less("Задача 1")) }
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "TodoScreen",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("TodoScreen") {
                            TodoScreen(navController = navController, items = lesSo)
                        }
                        composable("ClickTODO") {
                            ClickTODO(navController = navController, items = lesSo)
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun TodoScreen(modifier: Modifier = Modifier, navController: NavController,items: MutableList<Less>) {
        Scaffold(
            modifier = modifier,
            topBar = {
                TopAppBar(
                    title = {
                        Text("TODO")
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Кнопка добавить"
                    )
                }
            }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                TodoItem(navController, items = items)
            }
        }
    }

    @Composable
    private fun TodoItem(navController: NavController, items: MutableList<Less>,) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF28324F))
        ) {
            items(items.size) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clickable {
                            navController.navigate("ClickTODO")
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Icon(Icons.Default.Delete, contentDescription = null)
                    Text(text = items[index].lesSo, color = Color.White)
                    Checkbox(
                        checked = false,
                        onCheckedChange = {

                        }
                    )
                }
            }
        }
    }

    @Preview
    @Composable
    private fun TodoScreenPreview() = MyTodoTheme {
        TodoScreen(
            modifier = Modifier.fillMaxSize(),
            navController = NavController(MainActivity()),
            items = remember { mutableStateListOf(Less("Задача 1")) }
        )
    }

    data class Less(
        var lesSo: String,
    )
}