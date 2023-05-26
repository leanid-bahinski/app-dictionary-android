package com.example.l3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.l3.databinding.ActivityMainInfoBinding

class MainInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val intentPrice = Intent(this, PriceInfoActivity::class.java)
            val appName = binding.editAppName.text.toString()
            val appDeveloper = binding.editAppDeveloper.text.toString()
            val appType = binding.spinnerAppType.selectedItem.toString()
            val appCategory = binding.spinnerAppCategory.selectedItem.toString()

            intentPrice.putExtra("AppName", appName)
            intentPrice.putExtra("AppDeveloper", appDeveloper)
            intentPrice.putExtra("AppType", appType)
            intentPrice.putExtra("AppCategory", appCategory)
            startActivity(intentPrice)
        }
    }
}

