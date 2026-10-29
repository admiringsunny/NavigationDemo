package com.learn.navigationdemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.learn.navigationdemo.ui.theme.NavigationDemoTheme
import kotlin.Int
import kotlin.String

data object Home : NavKey
data class Profile(val name: String) : NavKey
data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val category: String
) : NavKey
data object Settings : NavKey

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationScreen(modifier: Modifier = Modifier) {

    // Step 1. create backstack
    val backStack = remember {
        mutableStateListOf<NavKey>(Home)
    }

    // Step 2 - Display the current destination
    NavDisplay(
        backStack = backStack,
        // Step 3 - Handle navigation
        onBack = {
            if (backStack.size > 1)
                backStack.removeLastOrNull()
        },
        // Step 4 - Map Destination keys to Screens
        entryProvider = entryProvider {
            entry<Home> {
                HomeScreen(
                    onProfileClick = {
                        Log.d("TAG", "NavigationScreen: onProfileClick")
                        backStack.add(Profile("Sunny"))
                    },
                    onProductClick = {
                        backStack.add(
                            Product(
                                id = 101,
                                name = "Lenovo Laptop",
                                price = 40000.00,
                                category = "Electronics"
                            )
                        )
                    },
                    onSettingsClick = {
                        backStack.add(Settings)
                    }
                )
            }
            entry<Profile> {
                ProfileScreen(it.name)
            }
            entry<Product> {
                ProductScreen(it)
            }
            entry<Settings> {
                SettingsScreen()
            }
        }
    )
}

@Composable
fun HomeScreen(
    onProfileClick: () -> Unit,
    onProductClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Navigation 3 Demo",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(24.dp))

        Button(
            onClick = onProfileClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Open Profile")
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = onProductClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Open Product Details")
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = onSettingsClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Open Settings")
        }
    }
}

@Composable
fun ProfileScreen(
    name: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Profile Screen",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(24.dp))
        Text("Welcome $name!")
        Text("Name received from HomeScreen")

    }
}

@Composable
fun ProductScreen(
    product: Product
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Product Details",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(24.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(4.dp)) {
                Text("Product ID: ${product.id}")
                Text("Product Name: ${product.name}")
                Text("Product Price: ${product.price}")
                Text("Product Category: ${product.category}")
            }
        }
    }
}

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Settings Screen",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(24.dp))
        Text("Press Back to return to Home")

    }
}

