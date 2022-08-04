package com.gdgnantes.devfest.android

import android.content.Intent
import android.location.Location
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gdgnantes.devfest.android.ui.screens.Home
import com.gdgnantes.devfest.android.ui.screens.Screen
import com.gdgnantes.devfest.android.ui.screens.session.SessionDetails
import com.gdgnantes.devfest.android.ui.screens.session.SessionViewModel
import com.gdgnantes.devfest.android.ui.theme.DevFest_NantesTheme
import com.gdgnantes.devfest.android.utils.assistedViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun sessionViewModelFactory(): SessionViewModel.SessionViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevFest_NantesTheme {
                val mainNavController = rememberNavController()

                NavHost(
                    navController = mainNavController,
                    startDestination = Screen.Home.route
                ) {
                    composable(route = Screen.Home.route) {
                        Home(
                            onSessionClick = { session ->
                                mainNavController.navigate("${Screen.Session.route}/${session.id}")
                            },
                            onNavigationClick = this@MainActivity::onNavigationClick
                        )
                    }

                    composable(
                        route = "${Screen.Session.route}/{sessionId}"
                    ) { backStackEntry ->
                        val sessionId = backStackEntry.arguments!!.getString("sessionId")!!
                        SessionDetails(
                            viewModel = assistedViewModel {
                                SessionViewModel.provideFactory(
                                    sessionViewModelFactory(),
                                    sessionId
                                )
                            },
                            onBackClick = { mainNavController.popBackStack() }
                        )
                    }
                }
            }
        }
    }

    private fun onNavigationClick(location: Location) {
        val uri = "geo: latitude,longtitude ?q= ${location.latitude},${location.longitude}"
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
    }
}