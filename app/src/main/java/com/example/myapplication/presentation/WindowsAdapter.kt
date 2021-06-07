package com.example.myapplication.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Window

class WindowsAdapter (var windows: List<Window>) : RecyclerView.Adapter<WindowsAdapter
.HistoryViewHolder>() {
    var onWindowClickListener: OnWindowClickListener? = null
    class HistoryViewHolder(view:CardView) : RecyclerView.ViewHolder(view) {
        var windowView: CardView = view
        var leftWingView: View
        var rightWingView: View
        var windowIdView: TextView

        init {
            leftWingView = windowView.findViewById(R.id.leftWingView)
            rightWingView = windowView.findViewById(R.id.rightWingView)
            windowIdView = windowView.findViewById(R.id.windowIdView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        var v : CardView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_window, parent, false) as CardView
        return HistoryViewHolder(v)
    }

    override fun getItemCount(): Int {
        return windows.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val window = windows[position]
        holder.windowIdView.text = window.windowId.toString()
        if(windows[position].isLeftWingOpen){
            holder.leftWingView.setBackgroundColor(Color.BLUE)
        }
        else{
            holder.leftWingView.setBackgroundColor(Color.DKGRAY)
        }
        holder.leftWingView.setOnClickListener(View.OnClickListener {

            if(windows[position].isLeftWingOpen){
                it.setBackgroundColor(Color.DKGRAY)
            }
            else{
                it.setBackgroundColor(Color.BLUE)
            }
            windows[position].isLeftWingOpen = !windows[position].isLeftWingOpen
            onWindowClickListener?.OnClickAction()
        })
        if(windows[position].isRightWingOpen){
            holder.rightWingView.setBackgroundColor(Color.BLUE)
        }
        else{
            holder.rightWingView.setBackgroundColor(Color.DKGRAY)
        }
        holder.rightWingView.setOnClickListener(View.OnClickListener {
            if(windows[position].isRightWingOpen){
                it.setBackgroundColor(Color.DKGRAY)
            }
            else{
                it.setBackgroundColor(Color.BLUE)
            }
            windows[position].isRightWingOpen = !windows[position].isRightWingOpen
            onWindowClickListener?.OnClickAction()

        })
    }
    interface OnWindowClickListener{
        fun OnClickAction()
    }


}


