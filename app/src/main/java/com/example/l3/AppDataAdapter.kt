package com.example.l3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppDataAdapter(
    private val dataList: List<AppData>,
    private val onItemClick: (AppData) -> Unit
) : RecyclerView.Adapter<AppDataAdapter.AppDataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_app_data, parent, false)
        return AppDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppDataViewHolder, position: Int) {
        val appData = dataList[position]
        holder.bind(appData)

        holder.itemView.setOnClickListener {
            onItemClick(appData)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class AppDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewAppName: TextView = itemView.findViewById(R.id.textViewAppName)
        private val textViewAppType: TextView = itemView.findViewById(R.id.textViewAppType)
        private val textViewDeveloper: TextView = itemView.findViewById(R.id.textViewDeveloper)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val appData = dataList[position]
                    onItemClick(appData)
                }
            }
        }

        fun bind(appData: AppData) {
            textViewAppName.text = appData.appName
            textViewDeveloper.text = "Developer: ${appData.appDeveloper}"
            textViewAppType.text = "Type: ${appData.appType}"
        }
    }
}
