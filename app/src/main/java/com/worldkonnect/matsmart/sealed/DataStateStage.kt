package com.worldkonnect.matsmart.sealed

import com.worldkonnect.matsmart.models.City
import com.worldkonnect.matsmart.models.Stage

sealed class DataStateStage{
    class Success(val data:MutableList<Stage>):DataStateStage()
    class Failure(val message:String):DataStateStage()
    object Loading:DataStateStage()
    object Empty:DataStateStage()
}
