package com.example.to_do.Pages

import android.content.Intent
import android.content.res.Resources.Theme
import android.icu.text.CaseMap.Title
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.to_do.ActionIcon
import com.example.to_do.Database.Tasks
import com.example.to_do.Database.UserViewModel
import com.example.to_do.R
import com.example.to_do.SwipableItemsWithActions
import com.example.to_do.TaskProperty
import com.example.to_do.ui.theme.BottomBarcolor
import com.example.to_do.ui.theme.CompletedTaskStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllTasks(title: String, navController: NavController, viewModel: UserViewModel) {
    val tasks by viewModel.tasks.observeAsState(emptyList())
    val deactivatedTasks by viewModel.deactivatedTask.observeAsState(emptyList())

    Scaffold{ paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                // Adjust padding for the Top App Bar
                .padding(top = 110.dp)
        ) {

            if (tasks.isEmpty() && deactivatedTasks.isEmpty()) {
                // Show empty state when both lists are empty
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Image(
                        painter = painterResource(id = R.drawable.rb_963),
                        contentDescription = "no data image",
                        modifier = Modifier.size(250.dp)
                    )
                    Text(
                        text = "No tasks available",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            } else {
                // Display tasks and deleted tasks if available
                Column {
                    if (tasks.isNotEmpty()) {
                        TasksList(tasks = tasks, viewModel)
                    }

                    if (deactivatedTasks.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Completed Tasks",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                        DeletedTaskList(tasks = deactivatedTasks, viewModel = viewModel)
                    }


                }

            }
        }
    }
}


@Composable
fun TasksList(tasks: List<Tasks>,viewModel: UserViewModel){
    LazyColumn {
        items(tasks){task->

            TaskItem(task=task, viewModel = viewModel)
        }
    }
}

@Composable
fun DeletedTaskList(tasks: List<Tasks>,viewModel: UserViewModel){
    LazyColumn {
        items(tasks){task->
            DeletedTaskItem(task = task, viewModel =viewModel )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(task: Tasks, viewModel: UserViewModel) {

    val context = LocalContext.current
    val swipeDismissState = rememberSwipeToDismissBoxState()
   var isOptionRevealed by remember {
       mutableStateOf(false)
   }



    SwipableItemsWithActions(isRevealed = isOptionRevealed, actions ={

        ActionIcon(onClick = { /*TODO*/ },
            backgrooundColor =Color.Red
            , icon =Icons.Filled.Delete )
        ActionIcon(onClick = { /*TODO*/ },
            backgrooundColor =Color.Blue ,
            icon = Icons.Filled.DateRange )
        ActionIcon(onClick = { /*TODO*/ },
            backgrooundColor = BottomBarcolor ,
            icon = Icons.Filled.Star)
    } ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            shape = RoundedCornerShape(6.dp),
            onClick = {
                val intent = Intent(context, TaskProperty::class.java).apply {
                    putExtra("Task_Name", task.task)
                }
                context.startActivity(intent)
            }
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = false, // Use task.isCompleted if available
                    onClick = {
                        // Call ViewModel method to update task completion (replace with actual function)
                        viewModel.updateTask(task.task)
                    }
                )
                Text(text = task.task, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeletedTaskItem( task: Tasks,viewModel: UserViewModel){

    val selectedButton by remember { mutableStateOf(true) }

        Card (modifier= Modifier
            .fillMaxWidth()
            .padding(4.dp)
            , shape = RoundedCornerShape(6.dp),
            onClick = {}
        ){
            Row (modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically){
                RadioButton(selected = selectedButton, onClick = {
                    viewModel.updateTaskToActive(task.task) })
                Text(text = task.task, style = CompletedTaskStyle)
            }
        }



}