package com.example.myapplication

import com.example.myapplication.model.Player
import com.example.myapplication.model.Window

class CastleManager(){
    val windows:MutableList<Window> = mutableListOf()
    val players:MutableList<Player> = mutableListOf()

    fun resetWindows(){
        for(window:Window in windows){
            window.isRightWingOpen = true
            window.isLeftWingOpen = true
        }
    }
    fun prepareSimulation(numberOfWindows:Int){
        for(i in 0 until numberOfWindows){
            windows.add(i, Window(i+1))
            players.add(i, Player(i+1))
        }
    }

    fun startSimulation(){
        for(player:Player in players){
            for(window:Window in windows){
                if (window.windowId % player.playerId == 0){
                    if(player.playerId % 2 == 0){
                        if(player.playerId == players.count()){
                            window.isRightWingOpen = !window.isRightWingOpen
                        }
                        else{
                            if(player.playerId != 2){
                                window.isLeftWingOpen = false
                            }
                            window.isRightWingOpen = true
                        }
                    }else{
                        if(player.playerId == players.count()){
                            window.isLeftWingOpen = !window.isLeftWingOpen
                        }
                        else{
                            if(player.playerId != 1){
                                window.isRightWingOpen = false
                            }
                            window.isLeftWingOpen = true
                        }
                    }
                }
            }
        }
    }

    fun getWinnerHardMode(): IntArray{
        var winners:MutableList<Int> = mutableListOf()
        for(i in 0 until windows.count()){
            if(windows[i].getState() == Window.STATE_OPEN){
                try {
                    if(windows[(i-1)].getState() == Window.STATE_CLOSE
                            && windows[i+1].getState() == Window.STATE_CLOSE){
                        winners.add(windows[i].windowId)
                    }
                }
                catch (e:IndexOutOfBoundsException){
                    //This player can't win because his window isn't between other windows
                }
            }
        }
        return winners.toIntArray()
    }

    fun getWinnerEasyMode(): IntArray{
        var winners:MutableList<Int> = mutableListOf()
        for(window in windows){
            if(window.getState() == Window.STATE_OPEN)
                winners.add(window.windowId)
        }
        return winners.toIntArray()
    }

    fun getWindowsState():kotlin.Array<String>{
        var states:MutableList<String> = mutableListOf()
        for(window:Window in windows){
            states.add(window.getState())
        }
        return states.toTypedArray()
    }

    fun getWindowsStateCount():IntArray{

        var openWindowsCount: Int= 0
        var closedWindowsCount: Int = 0
        var leftOpenWindowsCount: Int = 0
        var rightOpenWindowsCount: Int = 0
        var states = getWindowsState()
        for(state in states){
            if(state==Window.STATE_OPEN){
                openWindowsCount++
            }
            else if(state==Window.STATE_CLOSE){
                closedWindowsCount++
            }
            else if(state==Window.STATE_LEFT_SIDE_OPEN){
                leftOpenWindowsCount++
            }
            else if(state==Window.STATE_RIGHT_SIDE_OPEN){
                rightOpenWindowsCount++
            }
        }
        return intArrayOf(openWindowsCount, closedWindowsCount, leftOpenWindowsCount, rightOpenWindowsCount)

    }
}