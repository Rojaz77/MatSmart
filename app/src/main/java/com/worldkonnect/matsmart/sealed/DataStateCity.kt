package com.worldkonnect.matsmart.sealed

import com.worldkonnect.matsmart.models.City

sealed class DataStateCity {
    class Success(val data:MutableList<City>):DataStateCity()
    class Failure(val message:String):DataStateCity()
    object Loading:DataStateCity()
    object Empty:DataStateCity()
}
