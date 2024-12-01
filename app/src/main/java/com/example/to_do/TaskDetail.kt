package com.example.to_do

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class TaskProperty : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val taskName= intent.getStringExtra("Task_Name")
            Scaffold (
                topBar = {
                    TopAppBar(title = {
                        if (taskName != null) {
                            Text( taskName)
                        }
                    },
                        navigationIcon = { Icon(imageVector = Icons.Filled.Clear,
                            contentDescription = "Back",
                            modifier = Modifier
                                .clickable { finish() }
                                .padding(8.dp)
                        )
                                         },
                        actions = { Icon(imageVector =Icons.Filled.MoreVert ,
                            contentDescription = "More Options",
                            modifier= Modifier
                                .padding(8.dp)
                                .clickable { })})

                }
            ){innerPadding->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding)
                ) {
                    TaskDetail(Modifier)
                }
            }
        }
    }


}

@Composable
fun TaskDetail(modifier: Modifier){

    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)){

        TaskButton(icon = R.drawable.baseline_date_range_24,
            label = "Due Date", value ="29/11/2024" , onClick = {})
        TaskButton(icon = R.drawable.baseline_access_time_filled_24,
            label = "Time & Remainder", value ="No" , onClick = {})
        TaskButton(icon = R.drawable.baseline_speaker_notes_24,
            label = "Notes", value ="Edit" , onClick = {})
        TaskButton(icon = R.drawable.baseline_attach_file_24,
            label = "Attachment", value ="Add" , onClick = {})
    }


}

@Composable
fun TaskButton(icon:Int,label:String,value:String,onClick: () -> Unit){
    Card (

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick }
            , colors = cardColors(containerColor = Color.Transparent)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(16.dp))

                Text(text = label,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
            Text(text = value,
                fontSize = 16.sp,
                color = Color.Gray)
        }

    }
    HorizontalDivider()
}


