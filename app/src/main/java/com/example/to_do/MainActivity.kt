package com.example.to_do

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController

import com.example.to_do.Database.Tasks
import com.example.to_do.Database.UserViewModel
import com.example.to_do.Pages.AllTasks
import com.example.to_do.Pages.BirthdayPage
import com.example.to_do.Pages.ListPage
import com.example.to_do.Pages.PersonalPage
import com.example.to_do.Pages.WishlistPage
import com.example.to_do.Pages.WorkPage

import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
open class MainActivity :BaseActivity() {

    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            MainScreen(viewModel)
        }
    }

    @Composable
    fun MainScreen(viewModel: UserViewModel) {
        val navController = rememberNavController() // object of nav controller
        val pages = listOf("All", "Work", "Personal", "Wishlist", "Birthday", "List") // list of pages
        var selectedIndex by remember { mutableStateOf(0) } // selected index value
        var showDialog by remember { mutableStateOf(false) }

        Scaffold(
            topBar = {
                AppTopBar(
                    pages = pages,
                    selectedIndex = selectedIndex,
                    onChipClick = { index ->
                        selectedIndex = index
                        navController.navigate("page/$index")
                    }
                )
            },
            bottomBar = { BottomNavigationBar(currentActivity = "Tasks") },
            floatingActionButton = {
                FAB(
                    onAddClick = { showDialog = true }
                )
            },
            content = { innerPadding ->

                AppNavHost(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )

                if (showDialog) {

                    AddTaskDialog(
                        currentCategory = pages[selectedIndex],
                        onDismiss = { showDialog = false },
                        onTaskAdded = { taskName ,category->;
                            if (taskName.isNotBlank()) {
                                val currentDate = LocalDate.now()
                                val newTask = Tasks(task = taskName, date = currentDate.toString(), category =category, status = "Active" )
                                viewModel.insertTask(newTask)
                            }
                            showDialog = false
                        }
                    )
                }
            }
        )
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AppTopBar(
        pages: List<String>,
        selectedIndex: Int,
        onChipClick: (Int) -> Unit
    ) {
        TopAppBar(
            title = {
                LazyRow {
                    itemsIndexed(pages) { index, page ->
                        FilterChip(
                            modifier = Modifier.padding(8.dp),
                            selected = selectedIndex == index,
                            onClick = { onChipClick(index) },
                            label = { Text(text = page) }
                        )
                    }
                }
            },
            actions = {
                IconButton(onClick = { /* Handle menu click */ }) {
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Menu")
                }
            }
        )
    }

    @Composable
    fun FAB(onAddClick: () -> Unit) {
        FloatingActionButton(
            onClick = onAddClick,
            shape = CircleShape
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Task")
        }
    }

    @Composable
    fun AddTaskDialog(
        currentCategory: String,
        onDismiss: () -> Unit,
        onTaskAdded: (String,String) -> Unit
    ) {
        var taskName by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = "Add Task") },
            text = {
                OutlinedTextField(
                    value = taskName,
                    onValueChange = { taskName = it },
                    label = { Text(text = "Task Name") }
                )
            },
            confirmButton = {
                TextButton(onClick = { onTaskAdded(taskName,currentCategory) }) {
                    Text(text = "Add")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = "Cancel")
                }
            }
        )
    }

    @Composable
    fun AppNavHost(
        navController: NavHostController,
        modifier: Modifier = Modifier
    ) {
        NavHost(
            navController = navController,
            startDestination = "page/0",
            modifier = Modifier
        ) {
            composable("page/{pageId}") { backStackEntry ->
                val pageId = backStackEntry.arguments?.getString("pageId")
                when (pageId) {
                    "0" -> AllTasks("All", navController,viewModel)
                    "1" -> WorkPage("Work",navController,viewModel)
                    "2" -> PersonalPage("Personal",navController,viewModel)
                    "3" -> WishlistPage("Wishlist",navController,viewModel)
                    "4" -> BirthdayPage("Birthday",navController,viewModel)
                    "5" -> ListPage("List",navController,viewModel)
                    else -> Text("Page not found")
                }

            }
        }
    }
}
