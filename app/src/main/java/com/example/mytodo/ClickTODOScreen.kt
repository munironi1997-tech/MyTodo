package com.example.mytodo


import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ClickTODOScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    items: MutableList<MainActivity.Less>,
    id: Int,
    textTODO: String//Эълони тексти TODO
) {
    val item = items.find { it.id == id }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = item?.lesSo ?: "", //items-и интхобшудаамон ба текст баробар мешавад
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {

            Text(                 //текст дар TODO
                text = "$textTODO",
                fontSize = 20.sp
            )
            Row(       //Ҷойгиршавии тугмаҳо аз чап ба рост
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        items.removeIf { it.id == id }  // тоза кардан бо ёрии id
                        Toast.makeText(
                            navController.context,
                            "${item?.lesSo} тоза шуд",
                            Toast.LENGTH_SHORT
                        ).show() //Toast барои хабари тоза кардашуда
                        navController.popBackStack()
                    },
                ) {
                    Text("Удалить")
                }
                Spacer(modifier = Modifier.weight(1f))  //Ҷои холи байни тугамаҳо
                Button(     //Тугмаи ТАҲРИР КАРДАН
                    onClick = {
                        //холи
                    },
                ) {
                    Text("Таҳрир кардан")
                }
            }
        }
    }
}