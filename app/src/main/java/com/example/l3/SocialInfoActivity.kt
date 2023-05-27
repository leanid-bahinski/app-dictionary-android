package com.example.l3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.l3.databinding.ActivitySocialInfoBinding

class SocialInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySocialInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySocialInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnCreateNext.setOnClickListener {
            val intentSave = Intent(this, SaveActivity::class.java)

            val appName = intent.getStringExtra("AppName")
            val appDeveloper = intent.getStringExtra("AppDeveloper")
            val appType = intent.getStringExtra("AppType")
            var logo = intent.getStringExtra("LogoPath")
            val appCategory = intent.getStringExtra("AppCategory")
            var appCostType = intent.getStringExtra("AppCostType")
            var appCost = intent.getStringExtra("AppCost")
            var appReleaseDate = intent.getStringExtra("AppReleaseDate")
            var appOpenSource = intent.getStringExtra("AppOpenSource")
            var appPhone = binding.editTextPhone.text.toString()
            var appEmail = binding.editTextEmail.text.toString()
            var appSocial = binding.editTextSocial.text.toString()

            intentSave.putExtra("AppName", appName)
            intentSave.putExtra("AppDeveloper", appDeveloper)
            intentSave.putExtra("AppType", appType)
            intentSave.putExtra("AppCategory", appCategory)
            intentSave.putExtra("LogoPath", logo)
            intentSave.putExtra("AppCostType", appCostType)
            intentSave.putExtra("AppCost", appCost)
            intentSave.putExtra("AppReleaseDate", appReleaseDate)
            intentSave.putExtra("AppOpenSource", appOpenSource)
            intentSave.putExtra("AppPhone", appPhone)
            intentSave.putExtra("AppEmail", appEmail)
            intentSave.putExtra("AppSocial", appSocial)
            startActivity(intentSave)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
