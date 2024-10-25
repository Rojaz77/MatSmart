package com.worldkonnect.matsmart.presentation.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun MapScreen(
    viewModel: MapsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
){
    val location = LatLng(-1.2857484401347772, 36.88097584710913)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location,30f)
    }
    val scaffoldState = rememberScaffoldState()
    val uiSettings = remember{
        MapUiSettings(zoomControlsEnabled = false)
    }
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(MapEvent.ToggleFalloutMap)
            }) {
                Icon(
                    imageVector = if (viewModel.state.isFalloutMap) {
                                    Icons.Default.ToggleOff
                    }else Icons.Default.ToggleOn, contentDescription = "Toggle Fallout Map"
                )
            }
        },
        content = {padding ->
            Column(modifier =  Modifier.padding(padding))
            {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    properties = viewModel.state.properties,
                    uiSettings = uiSettings,
                    onMapLongClick = {

                    },
                    cameraPositionState = cameraPositionState
                ){
                    Marker(
                        position = location,
                        title = "Buruburu"
                    )
                }
            }
        }
    )
    }
