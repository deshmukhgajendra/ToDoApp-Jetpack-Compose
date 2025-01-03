package com.example.to_do.Pages

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.to_do.ActionIcon
import com.example.to_do.Database.Tasks
import com.example.to_do.Database.UserViewModel
import com.example.to_do.R
import com.example.to_do.SwipableItemsWithActions
import com.example.to_do.TaskProperty
import com.example.to_do.ui.theme.BottomBarcolor
import com.example.to_do.ui.theme.CompletedTaskStyle


@Composable
fun WishlistPage(title:String,navController: NavController,viewModel: UserViewModel) {

    val tasks by viewModel.WishListTasks.observeAsState(emptyList())
    val completedWishListTasks by viewModel.completedWishlistTask.observeAsState(emptyList())

    Scaffold{ paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                // Adjust padding for the Top App Bar
                .padding(top = 110.dp)
        ) {
            if (tasks.isEmpty() && completedWishListTasks.isEmpty()) {
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Image(
                        painter = painterResource(id = R.drawable.rb_8204),
                        contentDescription = "no data image", modifier = Modifier
                            .size(250.dp)
                    )
                    Text(
                        text ="No tasks in this category" +
                                "Click + to create your task",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.CenterHorizontally)

                    )
                }
            } else {

                if (tasks.isNotEmpty()){
                    WishList(tasks = tasks)
                }
                if (completedWishListTasks.isNotEmpty()){
                    Spacer(modifier = Modifier.padding(16.dp))

                    Text(text = "Completed Tasks",
                        fontSize = 20.sp,
                        modifier = Modifier.padding( 8.dp))

                    DeletedWishList(completedWishListTasks,viewModel)
                }


            }
        }
    }
}

@Composable
fun WishList(tasks: List<Tasks>){
    LazyColumn {
        items(tasks){task->

            WishlistItem(task = task)
        }
    }
}

@Composable
fun DeletedWishList(tasks : List<Tasks>,viewModel: UserViewModel){
    LazyColumn {
        items(tasks) {
            task->
            DeletedWishlistItem(task = task, viewModel = viewModel)
        }
    }
}
@Composable
fun WishlistItem(task: Tasks) {

    var selected by remember { mutableStateOf(false) }
    val context = LocalContext.current

    var isOptionRevealed by remember {
        mutableStateOf(false)
    }



    SwipableItemsWithActions(isRevealed = isOptionRevealed, actions = {

        ActionIcon(
            onClick = { /*TODO*/ },
            backgrooundColor = Color.Red, icon = Icons.Filled.Delete
        )
        ActionIcon(
            onClick = { /*TODO*/ },
            backgrooundColor = Color.Blue,
            icon = Icons.Filled.DateRange
        )
        ActionIcon(
            onClick = { /*TODO*/ },
            backgrooundColor = BottomBarcolor,
            icon = Icons.Filled.Star
        )
    }) {
        Card(modifier = Modifier
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
                RadioButton(selected = selected, onClick = { selected = !selected })
                Text(text = task.task, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Composable
fun DeletedWishlistItem(task: Tasks,viewModel: UserViewModel){

    var selected by remember{ mutableStateOf(true) }

    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp),
        shape = RoundedCornerShape(6.dp)
    ) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)){
            RadioButton(selected =selected , onClick = {
                viewModel.updateTaskToActive(task.task)
            })
            Text(text = task.task, style = CompletedTaskStyle)
        }
    }
}