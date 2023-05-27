package com.example.l3

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.l3.databinding.ActivityViewBinding
import java.io.File

class ViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val appData = intent.getSerializableExtra("appData") as? AppData
        if (appData != null) {
            binding.textViewAppName.text = appData.appName
            binding.textViewDeveloper.text = "Developer: ${appData.appDeveloper}"
            binding.textViewAppType.text = "Type: ${appData.appType}"
            binding.textViewAppCategory.text = "Category: ${appData.appCategory}"
            binding.textViewAppCostType.text = "Cost Type: ${appData.appCostType}"
            binding.textViewAppCost.text = "Cost: ${appData.appCost}"
            binding.textViewAppRelease.text = "Release: ${appData.appReleaseDate}"
            binding.textViewOpenSource.text = "Is open source: ${appData.appOpenSource}"
            binding.textViewPhone.text = "Phone: ${appData.appPhone}"
            binding.textViewEmail.text = "Email: ${appData.appEmail}"
            binding.textViewSocial.text = "Social Link: ${appData.appSocial}"

            val imageUri = Uri.fromFile(File(appData.logo))
            binding.imageViewPhoto.setImageURI(imageUri)

            binding.textViewPhone.setOnClickListener {
                dialPhoneNumber(appData.appPhone)
            }

            binding.textViewEmail.setOnClickListener {
                sendEmail(appData.appEmail)
            }

            binding.textViewSocial.setOnClickListener {
                openSocialLink(appData.appSocial)
            }
        } else {

        }
    }

    private fun dialPhoneNumber(phoneNumber: String?) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun sendEmail(email: String?) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun openSocialLink(socialLink: String?) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(socialLink)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
