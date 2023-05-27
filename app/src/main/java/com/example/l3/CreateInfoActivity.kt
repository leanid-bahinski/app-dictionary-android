package com.example.l3

import android.content.Intent
import android.os.Bundle
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
            val intentSocial = Intent(this, SocialInfoActivity::class.java)

            val appName = intent.getStringExtra("AppName")
            val appDeveloper = intent.getStringExtra("AppDeveloper")
            val appType = intent.getStringExtra("AppType")
            val appCategory = intent.getStringExtra("AppCategory")
            var logo = intent.getStringExtra("LogoPath")
            var appCostType = intent.getStringExtra("AppCostType")
            var appCost = intent.getStringExtra("AppCost")
            val appReleaseDate = "${binding.datePickerRelease.dayOfMonth}-${binding.datePickerRelease.month + 1}-${binding.datePickerRelease.year}"
            val appOpenSource = binding.checkBoxOpenSource.isChecked

            intentSocial.putExtra("AppName", appName)
            intentSocial.putExtra("AppDeveloper", appDeveloper)
            intentSocial.putExtra("AppType", appType)
            intentSocial.putExtra("AppCategory", appCategory)
            intentSocial.putExtra("LogoPath", logo)
            intentSocial.putExtra("AppCostType", appCostType)
            intentSocial.putExtra("AppCost", appCost)
            intentSocial.putExtra("AppReleaseDate", appReleaseDate)
            intentSocial.putExtra("AppOpenSource", appOpenSource)
            startActivity(intentSocial)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
