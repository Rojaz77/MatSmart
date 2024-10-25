package com.worldkonnect.matsmart.sealed

import com.worldkonnect.matsmart.models.Route

sealed class DataStateRoute {
    class Success(val dataRoute:MutableList<Route>):DataStateRoute()
    class Failure(val messageRoute:String):DataStateRoute()
    class  RouteSelect(val routCLick:String):DataStateRoute()
    object  Loading:DataStateRoute()
    object Empty:DataStateRoute()
}