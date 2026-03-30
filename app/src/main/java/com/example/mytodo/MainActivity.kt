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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.mytodo.ui.theme.MyTodoTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            MyTodoTheme {

                val lesSo = remember { mutableStateListOf(
                    Less(1,"Задача 134","Хондани китоб"),
                    Less(2,"Задача 2","Ҳалли машқҳо"),
                    Less(3,"Задача 3","Кори мустақилона"),
                    Less(4,"Задача 4","Такрори дарсҳои кӯҳна"),
                ) }
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = TodoScreen,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<TodoScreen> {
                            TodoScreen(navController = navController, items = lesSo)
                        }
                        composable<ClickTODO> {backStackEntry ->
                            val route = backStackEntry.toRoute<ClickTODO>()
                            ClickTODOScreen(
                                navController = navController,
                                items = lesSo,
                                id = route.id,
                                textTODO = route.textTODO
                            )
                        }
                    }
                }
            }
        }
    }
    @Composable
    fun TodoScreen(modifier: Modifier = Modifier, navController: NavController,items: MutableList<Less>) {
        Scaffold(
            modifier = modifier,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "TODO",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
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
    fun TodoItem(navController: NavController, items: MutableList<Less>,) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF28324F))
        ) {
            items(items.size) { index ->
                val id = items[index].id   //items[index] ро ба id  баробар кардам(аниқтараш index-ро ба id), то инки навигатсия донад кадом индексро интихоб кард
                var checkboxPush by remember { mutableStateOf(false) }
                val itemsColor = if (checkboxPush) Color.Red else Color.White
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clickable {
                            navController.navigate(ClickTODO(id, textTODO = items[index].textTODO))   //Навигатсия хамрох бо id меравад
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Delete, contentDescription = null)
                    Text(text = items[index].lesSo, color = itemsColor)
                    Checkbox(
                        checked = checkboxPush,
                        onCheckedChange = {
                            checkboxPush = it
                        }
                    )
                }
            }
        }
    }
    data class Less(
        var id: Int,//эълони id
        var lesSo: String,
        var textTODO: String //Эълони тексти TODO
    )
}