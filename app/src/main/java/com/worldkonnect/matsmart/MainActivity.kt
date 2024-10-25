

package com.worldkonnect.matsmart

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.worldkonnect.matsmart.presentation.cities.CitiesViewmodel
import com.worldkonnect.matsmart.presentation.cities.SetCityData
import com.worldkonnect.matsmart.presentation.map.MapScreen
import com.worldkonnect.matsmart.presentation.signin.GoogleAuthUiClient
import com.worldkonnect.matsmart.presentation.signin.SignInScreen
import com.worldkonnect.matsmart.presentation.signin.SignInViewModel
import com.worldkonnect.matsmart.presentation.profile.ProfileScreen
import com.worldkonnect.matsmart.presentation.rides.RidesViewModel
import com.worldkonnect.matsmart.presentation.rides.SetAvailableRidesData
import com.worldkonnect.matsmart.presentation.routes.RoutesViewmodel
import com.worldkonnect.matsmart.presentation.routes.SetRouteData
import com.worldkonnect.matsmart.presentation.stage.StageViewModel
import com.worldkonnect.matsmart.presentation.stage.setStageData
import com.worldkonnect.matsmart.ui.theme.MatSmartTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy{
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = com.google.android.gms.auth.api.identity.Identity.getSignInClient(applicationContext)
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MatSmartTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "sign_in")
                    {
                        composable("sign_in"){
                            val viewModel = viewModel<SignInViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()

                            LaunchedEffect(key1 = Unit){
                                if(googleAuthUiClient.getSignedInUser()!= null){
                                    navController.navigate("profile")
                            }
                            }
                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = { result ->
                                    if(result.resultCode == RESULT_OK){
                                        lifecycleScope.launch {
                                            val signInResult = googleAuthUiClient.signInWithIntent(
                                                intent = result.data?: return@launch
                                            )
                                            viewModel.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            )
                            LaunchedEffect(key1 = state.isSignInSuccessful)
                            {
                                if (state.isSignInSuccessful){
                                    Toast.makeText(applicationContext,"Sign in successful",Toast.LENGTH_LONG).show()
                                    navController.navigate(
                                        "profile"
                                    )
                                    viewModel.resetState()
                                }
                            }

                            SignInScreen(
                                state = state,
                                onSignInClick = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = googleAuthUiClient.signIn()
                                        launcher.launch(IntentSenderRequest.Builder
                                            (signInIntentSender?: return@launch).build())
                                    }
                                }
                            )
                        }
                        composable("profile"){
                            ProfileScreen(
                                userData = googleAuthUiClient.getSignedInUser(),
                                onSignOut = {
                                    lifecycleScope.launch {
                                        googleAuthUiClient.signOut()
                                        Toast.makeText(
                                            applicationContext,"Signed out",Toast.LENGTH_LONG
                                        ).show()

                                        navController.popBackStack()
                                    }
                                },
                                onRoutesClick = {
                                    navController.navigate(
                                        "city_select"
                                    )
                                }
                            )
                        }
                        composable("map"){
                            MapScreen()
                        }
                        composable("city_select") {
                            viewModel<CitiesViewmodel>()
                            SetCityData(viewmodel = viewModel(),navController = navController)
                        }
                        composable("route_select"){
                            viewModel<RoutesViewmodel>()
                            SetRouteData(viewmodel = viewModel(),navController = navController)
                        }
                        composable("ride_select"){
                            viewModel<RidesViewModel>()
                            SetAvailableRidesData(viewmodel = viewModel(),navController = navController)

                        }
                        composable("ride_view"){
                        }
                        composable( "stage_select"){
                            viewModel<StageViewModel>()
                            setStageData(viewmodel = viewModel()
                            )
                        }
                    }
                    }
                }
            }
        }

}

