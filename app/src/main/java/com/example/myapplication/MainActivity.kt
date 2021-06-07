package com.example.myapplication

import android.content.res.TypedArray
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.presentation.WindowsAdapter

class MainActivity : AppCompatActivity() {

    val castleManager: CastleManager = CastleManager()
    val windowsAdapter: WindowsAdapter = WindowsAdapter(castleManager.windows)
    lateinit var recyclerView: RecyclerView
    lateinit var resetWindowsButton :Button
    lateinit var getWinnersEasyModeButton: Button
    lateinit var getWinnersHardModeButton: Button
    lateinit var startSimulationButton: Button
    lateinit var getWindowsStateButton: Button
    lateinit var layoutManager: LinearLayoutManager
    lateinit var labelOpenWindows: TextView
    lateinit var labelClosedWindows: TextView
    lateinit var labelLeftOpenWindows: TextView
    lateinit var labelRightOpenWindows: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        castleManager.prepareSimulation(64)

        recyclerView = findViewById(R.id.windowList)

        resetWindowsButton = findViewById(R.id.resetWindowsButton)
        getWinnersEasyModeButton = findViewById(R.id.winnersEasyModeButton)
        getWinnersHardModeButton = findViewById(R.id.winnersHardModeButton)
        getWindowsStateButton = findViewById(R.id.getWindowsStateButton)
        startSimulationButton = findViewById(R.id.startSimulationButton)

        resetWindowsButton.setOnClickListener(View.OnClickListener {
            castleManager.resetWindows()
            windowsAdapter.notifyDataSetChanged()
            updateWindowsInfoLabels()
        })
        getWinnersEasyModeButton.setOnClickListener(View.OnClickListener {
            showWinners(castleManager.getWinnerEasyMode())
        })
        getWinnersHardModeButton.setOnClickListener(View.OnClickListener {
            showWinners(castleManager.getWinnerHardMode())
        })
        getWindowsStateButton.setOnClickListener(View.OnClickListener {
            showStates(castleManager.getWindowsState())
        })
        startSimulationButton.setOnClickListener(View.OnClickListener {
            castleManager.startSimulation()
            windowsAdapter.notifyDataSetChanged()
            updateWindowsInfoLabels()
        })

        labelOpenWindows = findViewById(R.id.labelOpenWindows)
        labelClosedWindows = findViewById(R.id.labelClosedWindows)
        labelLeftOpenWindows = findViewById(R.id.labelLeftOpenWindows)
        labelRightOpenWindows = findViewById(R.id.labelRightOpenWindows)

        updateWindowsInfoLabels()

        layoutManager = LinearLayoutManager(this);
        recyclerView.layoutManager = layoutManager;
        recyclerView.adapter = windowsAdapter;
        windowsAdapter.onWindowClickListener =object: WindowsAdapter.OnWindowClickListener{
            override fun OnClickAction() {
                updateWindowsInfoLabels()
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    fun showWinners(winners:IntArray){
        var alertDialogBuilder=AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Winners")
        if(winners.isEmpty()){
            alertDialogBuilder.setMessage("No Winners")
        }
        else {
            alertDialogBuilder.setMessage(winners.joinToString())
        }
        alertDialogBuilder.show()
    }
    fun showStates(states:Array<String>){
        var alertDialogBuilder=AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("States")
        if(states.isEmpty()){
            alertDialogBuilder.setMessage("No Winners")
        }
        else {
            alertDialogBuilder.setMessage(states.joinToString())
        }
        alertDialogBuilder.show()
    }

    fun updateWindowsInfoLabels(){
        var windowsStateCounts = castleManager.getWindowsStateCount()
        labelOpenWindows.text = windowsStateCounts[0].toString()
        labelClosedWindows.text = windowsStateCounts[1].toString()
        labelLeftOpenWindows.text = windowsStateCounts[2].toString()
        labelRightOpenWindows.text = windowsStateCounts[3].toString()
    }
}