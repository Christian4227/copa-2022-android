package me.dio.copa.catar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.dio.copa.catar.ui.components.Header
import me.dio.copa.catar.ui.theme.Copa2022Theme
import me.dio.copa.catar.ui.screens.settings.Settings

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Copa2022Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(navController, startDestination = "main") {
                        composable("main") {
                            Home(
                                onSettingsClick = { navController.navigate("settings") }
                            )
                        }
                        composable("settings") { Settings() }
                    }
                }
            }
        }
    }
}

@Composable
fun Home(onSettingsClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(0.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
    ) {
        // O clique no Header agora aciona a rota definida no NavHost
        Header(onSettingsClick = onSettingsClick)
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultPreview() {
    Copa2022Theme {
        Home(onSettingsClick = {})
    }
}

