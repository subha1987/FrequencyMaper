package org.subha.frequencymaper.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.subha.frequencymaper.R
import org.subha.frequencymaper.view_model.Directory


class DirectoryAdapter :
    RecyclerView.Adapter<DirectoryAdapter.DirectoryAdapterViewHolder>() {

    private var directoryList: MutableList<Directory> = ArrayList()

    fun setData(directoryList: MutableList<Directory>) {
        this.directoryList.clear()
        this.directoryList.addAll(directoryList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectoryAdapterViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.directory_adapter_view, parent, false)
        return DirectoryAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return directoryList.size
    }

    override fun onBindViewHolder(holder: DirectoryAdapterViewHolder, position: Int) {
        val directory = directoryList[position]
        holder.apply {
            tvWord.text = "Word: " + directory.word
            tvFrequency.text = "Frequency: " + directory.frequency.toString()
            rlParent.setBackgroundResource(directory.color)
        }
    }

    class DirectoryAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvWord = view.findViewById<TextView>(R.id.tvWord)
        val tvFrequency = view.findViewById<TextView>(R.id.tvFrequency)
        val rlParent = view.findViewById<LinearLayout>(R.id.rlParent)
    }

}


