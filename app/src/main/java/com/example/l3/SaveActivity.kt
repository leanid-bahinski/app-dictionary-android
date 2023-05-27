package com.example.l3

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.l3.databinding.ActivitySaveBinding
import com.google.gson.Gson
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

class SaveActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySaveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val appName = intent.getStringExtra("AppName")
        val appDeveloper = intent.getStringExtra("AppDeveloper")
        val appType = intent.getStringExtra("AppType")
        val appCategory = intent.getStringExtra("AppCategory")
        var logo = intent.getStringExtra("LogoPath")
        var appCostType = intent.getStringExtra("AppCostType")
        var appCost = intent.getStringExtra("AppCost")
        var appReleaseDate = intent.getStringExtra("AppReleaseDate")
        var appOpenSource = intent.getBooleanExtra("AppOpenSource", false)
        var appPhone = intent.getStringExtra("AppPhone")
        var appEmail = intent.getStringExtra("AppEmail")
        var appSocial = intent.getStringExtra("AppSocial")

        val appData = AppData(
            appName = appName,
            appDeveloper = appDeveloper,
            appType = appType,
            appCategory = appCategory,
            appCostType = appCostType,
            appCost = appCost,
            appReleaseDate = appReleaseDate,
            appOpenSource = appOpenSource,
            appPhone = appPhone,
            appEmail = appEmail,
            appSocial = appSocial,
            logo = logo
        )

        binding.textViewAppData.text = appData.toString()

        binding.btnRegister.setOnClickListener {

            registerAppData(appData)

            val intent = Intent(this, ListActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }
    }

    private fun registerAppData(appData: AppData) {
        try {
            val file = File(this.filesDir, "app_data.json")
            val fileOutputStream = FileOutputStream(file, true) // Set the second parameter to true to enable append mode
            val writer = OutputStreamWriter(fileOutputStream)
            val gson = Gson()
            val jsonData = gson.toJson(appData)

            writer.append(jsonData)
            writer.append("\n") // Add a newline to separate entries
            writer.close()
            fileOutputStream.close()

            Toast.makeText(this, "Файл \"app_data.json\" успешно обновлен", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}