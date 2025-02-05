package com.example.to_do

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
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
//            "Menu" ->{
//                if (this !is MainActivity) {
//                    startActivity(Intent(this, MainActivity::class.java))
//                    finish()
//                }
//            }
            "Tasks" ->{
                if (this !is MainActivity) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
            "Calender" ->{
                if(this !is CalenderActivity){
                    startActivity(Intent(this,CalenderActivity::class.java))
                    finish()
                }
            }
            "Mine" ->{
                if(this !is MineActivity){
                    startActivity(Intent(this,MineActivity::class.java))
                    finish()
                }
            }
        }
    }

}
@Composable
fun NavigationDrawer(){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerContent = { ModalDrawerSheet {
            Column (modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())){

                Spacer(modifier = Modifier.padding(12.dp))
                NavigationDrawerItem(
                    label = { Text(text = "Themes")},
                    selected = false,
                    onClick = {}
                )
                NavigationDrawerItem(
                    label = { Text(text = "Widgets")},
                    selected = false,
                    onClick = {}
                )
                NavigationDrawerItem(
                    label = { Text(text = "Lists")},
                    selected = false,
                    onClick = {}
                )
                NavigationDrawerItem(
                    label = { Text(text = "Donete")},
                    selected = false,
                    onClick = {}
                )
                NavigationDrawerItem(
                    label = { Text(text = "Feedback")},
                    selected = false,
                    onClick = {}
                )
                NavigationDrawerItem(
                    label = { Text(text = "FAQ")},
                    selected = false,
                    onClick = {}
                )
                NavigationDrawerItem(
                    label = { Text(text = "Setting")},
                    selected = false,
                    onClick = {}
                )

            }
        }}) {

    }
}