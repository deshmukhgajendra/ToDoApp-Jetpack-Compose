package com.example.to_do.BottomNavActivities

import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.to_do.BaseActivity
import com.example.to_do.BottomNavActivities.ui.theme.TODoTheme
import com.example.to_do.Database.Tasks
import com.example.to_do.Database.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalenderActivity : BaseActivity() {

    private val viewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            TODoTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                bottomBar = {BottomNavigationBar(currentActivity = "Calender")}) { innerPadding ->

                    CalenderTextView(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun CalenderTextView(viewModel: UserViewModel){

    val context = LocalContext.current

    var selectedDate by remember{ mutableStateOf("") }

    val taskForSelectedDate by viewModel.getTaskForDate(selectedDate).observeAsState(emptyList())

    Scaffold { paddingValues->
        Column (modifier= Modifier
            .fillMaxSize()
            .padding(paddingValues)) {

            AndroidView(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                factory ={
                    CalendarView(context).apply {
                        date = System.currentTimeMillis()

                        setOnDateChangeListener{_,year,month,dayOfMonth->
                            selectedDate = String.format("%02d-%02d-%04d", dayOfMonth, month + 1, year)
                            Toast.makeText(context,selectedDate,Toast.LENGTH_SHORT).show()
                        }
                    }
                } )
            Spacer(modifier = Modifier.height(16.dp))

            if (taskForSelectedDate.isEmpty()) {
                Text(
                    text = "No tasks for selected date.",
                    modifier=Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.bodyMedium
                )
            }else{
                LazyColumn(
                    modifier= Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    items(taskForSelectedDate){ task ->
                        CalenderTaskItems(task = task)

                    }
                }
            }
        }
    }
}

@Composable
fun CalenderTaskItems(task: Tasks){
    Card (modifier= Modifier
        .fillMaxWidth()
        .padding(4.dp),
        shape = RoundedCornerShape(6.dp)
    ) {
        Row (modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = task.task,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f))
        }
    }
}
