package com.example.to_do

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.to_do.BottomNavActivities.BottomNavItems
import com.example.to_do.BottomNavActivities.CalenderActivity
import com.example.to_do.BottomNavActivities.MineActivity

open class BaseActivity : ComponentActivity() {

    @Composable
    protected fun BottomNavigationBar(currentActivity: String){
        val items= listOf(
            BottomNavItems("Menu","menu", Icons.Filled.Menu),
            BottomNavItems("Tasks","tasks", Icons.Filled.List),
            BottomNavItems("Calender","calender", Icons.Filled.DateRange),
            BottomNavItems("Mine","mine", Icons.Filled.Person)
        )

        NavigationBar {
            items.forEach {  item ->

                NavigationBarItem(selected = currentActivity == item.title,
                    onClick = { navigateToActivity(item.title) },
                    icon = { Icon(item.icon, contentDescription =item.title ) },
                    label = { Text(text = item.title)}
                )
            }
        }
    }

    private fun navigateToActivity(destination: String){
        when(destination){
            "Tasks" ->{
                if (this !is MainActivity) startActivity(Intent(this, MainActivity::class.java))
            }
            "Calender" ->{
                if(this !is CalenderActivity) startActivity(Intent(this,CalenderActivity::class.java))
            }
            "Mine" ->{
                if(this !is MineActivity) startActivity(Intent(this,MineActivity::class.java))
            }
        }
    }

}