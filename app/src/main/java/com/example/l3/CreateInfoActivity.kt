package com.example.l3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.l3.databinding.ActivityCreateInfoBinding

class CreateInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnCreateNext.setOnClickListener {
            val intentSave = Intent(this, SaveActivity::class.java)
            val appName = intent.getStringExtra("AppName")
            val appDeveloper = intent.getStringExtra("AppDeveloper")
            val appType = intent.getStringExtra("AppType")
            val appCategory = intent.getStringExtra("AppCategory")
            var appCostType = intent.getStringExtra("AppCostType")
            var appCost = intent.getStringExtra("AppCost")
            val appReleaseDate = "${binding.datePickerRelease.dayOfMonth}-${binding.datePickerRelease.month + 1}-${binding.datePickerRelease.year}"
            val appOpenSource = binding.checkBoxOpenSource.isChecked

            intentSave.putExtra("AppName", appName)
            intentSave.putExtra("AppDeveloper", appDeveloper)
            intentSave.putExtra("AppType", appType)
            intentSave.putExtra("AppCategory", appCategory)
            intentSave.putExtra("AppCostType", appCostType)
            intentSave.putExtra("AppCost", appCost)
            intentSave.putExtra("AppReleaseDate", appReleaseDate)
            intentSave.putExtra("AppOpenSource", appOpenSource)
            startActivity(intentSave)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
