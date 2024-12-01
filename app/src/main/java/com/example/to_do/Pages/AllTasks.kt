package com.example.to_do.Pages

import android.content.Intent
import android.icu.text.CaseMap.Title
import androidx.compose.foundation.Image
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.to_do.Database.Tasks
import com.example.to_do.Database.UserViewModel
import com.example.to_do.R
import com.example.to_do.TaskProperty


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

            if (tasks.isEmpty()) {
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Image(
                        painter = painterResource(id = R.drawable.rb_963),
                        contentDescription = "no data image", modifier = Modifier
                            .size(250.dp)
                    )
                    Text(
                        text = "",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.CenterHorizontally)

                    )
                }
            } else {

                Column {
                    TasksList(tasks = tasks,viewModel)
// add condition if completed task list is not empty then it should be visible

                    if (deactivatedTasks.isNotEmpty()){
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Deleted Tasks", fontSize = 20.sp, modifier = Modifier.padding(8.dp))

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
fun TaskItem(task:Tasks,viewModel: UserViewModel){

    var selected by remember { mutableStateOf(false) }
    val dismissState = rememberSwipeToDismissBoxState()
    val context = LocalContext.current

    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp),
        shape = RoundedCornerShape(6.dp),
        onClick = {val intent= Intent(context,TaskProperty::class.java).apply {
            putExtra("Task_Name",task.task)}
            context.startActivity(intent)
        }
    ){
        Row (modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically){
            RadioButton(selected = selected, onClick = { viewModel.updateTask(task.task)})
            Text(text = task.task, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

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
            RadioButton(selected = selectedButton, onClick = { /*TODO*/ })
            Text(text = task.task, style = MaterialTheme.typography.bodyLarge)
        }
    }
}