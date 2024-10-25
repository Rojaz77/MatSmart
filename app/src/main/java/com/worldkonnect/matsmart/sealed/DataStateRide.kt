package com.worldkonnect.matsmart.sealed

import com.worldkonnect.matsmart.models.City
import com.worldkonnect.matsmart.models.Ride

sealed class DataStateRide{
    class Success(val data:MutableList<Ride>):DataStateRide()
    class Failure(val message:String):DataStateRide()
    object Loading:DataStateRide()
    object Empty:DataStateRide()
}


