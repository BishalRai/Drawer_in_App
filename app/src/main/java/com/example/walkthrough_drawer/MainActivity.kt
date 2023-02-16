package com.example.walkthrough_drawer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.walkthrough_drawer.ui.theme.Walkthrough_DrawerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Walkthrough_DrawerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DrawerApp()
                }
            }
        }
    }
}

@Composable
fun DrawerApp() {
    val scState = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val coroutineScope = rememberCoroutineScope()
    val items = listOf("Page1", "Page2")
    Scaffold(
        scaffoldState = scState,
        topBar = { MyTopBar { coroutineScope.launch { scState.drawerState.open() } } },
        content = { Text("Home screen") },
        drawerContent = { MyDrawer(items) }
    )
}

@Composable
fun MyTopBar(onMenuIconClick: () -> Unit) {
    TopAppBar(
        title = { Text("My app") },
        navigationIcon = {
            IconButton(
                onClick = { onMenuIconClick }
            ) {
                Icon(Icons.Filled.Menu, contentDescription = null)
            }
        }
    )
}

@Composable
fun MyDrawer(items: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_baseline_ac_unit_24),
            contentDescription = null
        )
        Text("Drawer")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Walkthrough_DrawerTheme {
        DrawerApp()
    }
}