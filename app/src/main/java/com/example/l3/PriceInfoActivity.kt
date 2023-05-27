package com.example.l3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.l3.databinding.ActivityPriceInfoBinding

class PriceInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPriceInfoBinding
    private lateinit var selectedCostType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPriceInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.radioGroupCostType.setOnCheckedChangeListener { group, checkedId ->
            val radioButton: RadioButton = group.findViewById(checkedId)
            selectedCostType = radioButton.text.toString()

            val isFreeSelected = radioButton == binding.radioBtnFree
            updateAppCostField(isFreeSelected)
        }

        binding.btnPriceNext.setOnClickListener {
            val intentCreate = Intent(this, CreateInfoActivity::class.java)

            val appName = intent.getStringExtra("AppName")
            val appDeveloper = intent.getStringExtra("AppDeveloper")
            val appType = intent.getStringExtra("AppType")
            val appCategory = intent.getStringExtra("AppCategory")
            var logo = intent.getStringExtra("LogoPath")
            var appCostType = selectedCostType
            var appCost = binding.editAppCost.text.toString()

            intentCreate.putExtra("AppName", appName)
            intentCreate.putExtra("AppDeveloper", appDeveloper)
            intentCreate.putExtra("AppType", appType)
            intentCreate.putExtra("AppCategory", appCategory)
            intentCreate.putExtra("LogoPath", logo)
            intentCreate.putExtra("AppCostType", appCostType)
            intentCreate.putExtra("AppCost", appCost)
            startActivity(intentCreate)
        }

        binding.radioGroupCostType.check(binding.radioBtnFree.id)
    }

    private fun updateAppCostField(isFreeSelected: Boolean) {
        if (isFreeSelected) {
            binding.editAppCost.setText("0")
            binding.editAppCost.isEnabled = false
        } else {
            binding.editAppCost.isEnabled = true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
