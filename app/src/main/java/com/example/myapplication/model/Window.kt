package com.example.myapplication.model

import java.lang.IndexOutOfBoundsException

class Window(id:Int){
    var windowId:Int = 0
    var isLeftWingOpen:Boolean = true
    var isRightWingOpen:Boolean = true

    init {
        this.windowId = id
    }
    fun getState(): String {
        return if(isLeftWingOpen){
            if(isRightWingOpen){
                STATE_OPEN
            } else{
                STATE_LEFT_SIDE_OPEN
            }
        }
        else{
            if(isRightWingOpen){
                STATE_RIGHT_SIDE_OPEN
            } else{
                STATE_CLOSE
            }
        }
    }

    companion object{
        const val STATE_OPEN:String = "A"
        const val STATE_CLOSE:String = "C"
        const val STATE_LEFT_SIDE_OPEN:String = "L"
        const val STATE_RIGHT_SIDE_OPEN:String = "D"
    }
}