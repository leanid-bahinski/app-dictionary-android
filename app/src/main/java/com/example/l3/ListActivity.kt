package com.example.l3

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.l3.databinding.ActivityListBinding
import com.google.gson.Gson
import java.io.File

class ListActivity : AppCompatActivity() {
    private lateinit var adapter: AppDataAdapter
    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewAppData.layoutManager = LinearLayoutManager(this)
        val jsonDataList = getDataListFromJsonFile()

        adapter = AppDataAdapter(jsonDataList) { appData ->
            val viewIntent = Intent(this, ViewActivity::class.java)
            viewIntent.putExtra("appData", appData)
            startActivity(viewIntent)
        }
        binding.recyclerViewAppData.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                val intent = Intent(this, MainInfoActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        recreate()
    }

    private fun getDataListFromJsonFile(): List<AppData> {
        val dataList = mutableListOf<AppData>()

        try {
            val file = File(this.filesDir, "app_data.json")
            val gson = Gson()
            val jsonData = file.readText()

            jsonData.lines().forEach { line ->
                if (line.isNotBlank()) {
                    try {
                        val appData = gson.fromJson(line, AppData::class.java)
                        dataList.add(appData)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return dataList
    }
}
