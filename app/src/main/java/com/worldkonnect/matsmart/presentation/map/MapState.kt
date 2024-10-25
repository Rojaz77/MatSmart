package com.worldkonnect.matsmart.presentation.map

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties
import java.util.Properties

data class MapState(
    val properties: MapProperties = MapProperties(
    ),
    val isFalloutMap: Boolean = false,
)
