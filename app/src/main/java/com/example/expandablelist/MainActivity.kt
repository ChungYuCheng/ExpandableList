package com.example.expandablelist

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Checkbox
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expandablelist.ui.theme.ExpandableListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpandableListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val list = listOf(
                        Department("3C部", false, listOf(
                            Department.SubClass("1課"),
                            Department.SubClass("2課"),
                            Department.SubClass("3課"),
                            Department.SubClass("4課"),
                            Department.SubClass("5課"),
                            Department.SubClass("6課"),
                        )),
                        Department("家電部", false, listOf(
                            Department.SubClass("1課"),
                            Department.SubClass("2課"),
                            Department.SubClass("3課"),
                            Department.SubClass("4課"),
                            Department.SubClass("5課"),
                            Department.SubClass("6課"),
                        )),
                        Department("電玩部", false, listOf(
                            Department.SubClass("1課"),
                            Department.SubClass("2課"),
                            Department.SubClass("3課"),
                            Department.SubClass("4課"),
                            Department.SubClass("5課"),
                            Department.SubClass("6課"),
                        )),
                        Department("行動研發部", false, listOf(
                            Department.SubClass("1課"),
                            Department.SubClass("2課"),
                            Department.SubClass("3課"),
                            Department.SubClass("4課"),
                            Department.SubClass("5課"),
                            Department.SubClass("6課"),
                        )),
                        Department("寵物部", false, listOf(
                            Department.SubClass("1課"),
                            Department.SubClass("2課"),
                            Department.SubClass("3課"),
                            Department.SubClass("4課"),
                            Department.SubClass("5課"),
                            Department.SubClass("6課"),
                        )),
                        Department("食品部", false, listOf(
                            Department.SubClass("1課"),
                            Department.SubClass("2課"),
                            Department.SubClass("3課"),
                            Department.SubClass("4課"),
                            Department.SubClass("5課"),
                            Department.SubClass("6課"),
                        )),
                        Department("飲料部", false, listOf(
                            Department.SubClass("1課"),
                            Department.SubClass("2課"),
                            Department.SubClass("3課"),
                            Department.SubClass("4課"),
                            Department.SubClass("5課"),
                            Department.SubClass("6課"),
                        )),
                    )

                    ExpandableList(list)
                }
            }
        }
    }
}

@Composable
fun ExpandableList(list: List<Department>) {

    LazyColumn {
        items(list) { item ->
            SubItemRow(item)
        }
    }
}

@Composable
fun SubItemRow(item: Department) {
//    val isExpand = remember { mutableStateOf(item.isExpand) } // * 會被關掉
    var isExpand by rememberSaveable { mutableStateOf(item.isExpand) } // * 會保留狀態在Bundle

    Row(
        modifier = Modifier
            .height(50.dp)
            .padding(vertical = 15.dp)
            .clickable {
                isExpand = !isExpand
            }
    ) {
        Text(
            text = item.department,
            modifier = Modifier
                .padding(start = 32.dp),
        )
    }

    item.subList?.let { subList ->
        AnimatedVisibility(visible = isExpand) {
            Column {
                subList.forEach { subClass ->
                    ItemRow(subClass.className)
                }
            }
        }
    }
}

@Composable
fun ItemRow(name: String) {
    Row(
        modifier = Modifier
            .height(50.dp)
            .padding(vertical = 15.dp)
    ) {
        Text(
            text = name,
            modifier = Modifier
                .padding(start = 32.dp),
        )
    }
}

data class Department(
    val department: String,
    var isExpand: Boolean = false,
    val subList: List<SubClass>? = null
) {
    data class SubClass(
        val className: String,
    )
}