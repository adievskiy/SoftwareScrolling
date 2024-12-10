package com.example.softwarescrolling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.softwarescrolling.ui.theme.SoftwareScrollingTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val persons = listOf(
                Person("Первый", "Первов", "Инженер"),
                Person("Второй", "Второв", "Механик"),
                Person("Третий", "Третьеров", "Бухгалтер"),
                Person("Четвертый", "Четверов", "Бухгалтер"),
                Person("Пятый", "Пятов", "Механик"),
                Person("Шестой", "Шестов", "Инженер"),
                Person("Седьмой", "Седьмов", "Инженер"),
                Person("Восьмой", "Восьмов", "Механик"),
                Person("Девятый", "Девятов", "Инженер"),
                Person("Десятый", "Десятов", "Механик"),
                Person("Одиннадцатый", "Одиннадцатов", "Инженер"),
                Person("Двенадцатый", "Двенадцатов", "Бухгалтер"),
                Person("Первый", "Первов", "Инженер"),
                Person("Второй", "Второв", "Механик"),
                Person("Третий", "Третьеров", "Бухгалтер"),
                Person("Четвертый", "Четверов", "Бухгалтер"),
                Person("Пятый", "Пятов", "Механик"),
                Person("Шестой", "Шестов", "Инженер"),
                Person("Седьмой", "Седьмов", "Инженер"),
                Person("Восьмой", "Восьмов", "Механик"),
                Person("Девятый", "Девятов", "Инженер"),
                Person("Десятый", "Десятов", "Механик"),
                Person("Одиннадцатый", "Одиннадцатов", "Инженер"),
                Person("Двенадцатый", "Двенадцатов", "Бухгалтер"),
            )
            val listState = rememberLazyListState()
            val coroutineScope = rememberCoroutineScope()
            val groupPersons = persons.groupBy { it.position }

            LazyColumn(state = listState, modifier = Modifier.padding(top = 45.dp)) {
                item {
                    OnBottom(coroutineScope, listState, persons)
                }
                groupPersons.forEach { (position, persons) ->
                    stickyHeader {
                        Text(
                            text = position,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .background(Color.DarkGray)
                                .padding(6.dp)
                                .fillParentMaxWidth()
                        )
                    }
                    items(persons) { person ->
                        Text(
                            text = "${person.name} ${person.secondName}",
                            modifier = Modifier.padding(6.dp),
                            fontSize = 28.sp
                        )
                    }
                }
                item {
                    OnTop(coroutineScope, listState)
                }
            }
        }
    }

    @Composable
    private fun OnTop(
        coroutineScope: CoroutineScope,
        listState: LazyListState,
    ) {
        Text(
            text = "В Начало",
            fontSize = 22.sp,
            color = Color.White,
            modifier = Modifier
                .padding(8.dp)
                .background(Color.DarkGray)
                .padding(6.dp)
                .clickable {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                }
        )
    }

    @Composable
    private fun OnBottom(
        coroutineScope: CoroutineScope,
        listState: LazyListState,
        persons: List<Person>,
    ) {
        Text(
            text = "В конец",
            fontSize = 22.sp,
            color = Color.White,
            modifier = Modifier
                .padding(8.dp)
                .background(Color.DarkGray)
                .padding(6.dp)
                .clickable {
                    coroutineScope.launch {
                        listState.animateScrollToItem(persons.size - 1)
                    }
                }
        )
    }
}

data class Person(val name: String, val secondName: String, val position: String)