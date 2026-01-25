package me.dio.copa.catar

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.dio.copa.catar.extensions.hasNotificationPermission
import me.dio.copa.catar.ui.screens.main.MainScreen
import me.dio.copa.catar.ui.screens.main_countries.CountriesScreen
import me.dio.copa.catar.ui.screens.settings.SettingsScreen
import me.dio.copa.catar.ui.theme.Copa2022Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) { /* Notificação autorizada */ }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun askNotificationPermission() {
        if (!hasNotificationPermission()) {
            requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_NO
        )
        askNotificationPermission()

        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Copa2022Theme(
                darkTheme = false
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(navController, startDestination = "main") {
                        composable("main") {
                            MainScreen(
                                onSettingsClick = { navController.navigate("settings") },
                                onCountriesClick = { navController.navigate("main/countries") }
                            )
                        }
                        composable("main/countries") {
                            CountriesScreen()
                        }
                        composable("settings") {
                            SettingsScreen (
                                onBackClick = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultPreview() {
    Copa2022Theme {
        MainScreen(
            onSettingsClick = {},
            onCountriesClick = {}
        )
    }
}
