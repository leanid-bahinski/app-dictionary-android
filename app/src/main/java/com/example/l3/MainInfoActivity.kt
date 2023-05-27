package com.example.l3

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.l3.databinding.ActivityMainInfoBinding
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainInfoBinding
    private var selectedPhotoPath: String? = null

    companion object {
        private const val REQUEST_IMAGE_PICKER = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSelectPhoto.setOnClickListener {
            openImagePicker()
        }

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
            intentPrice.putExtra("LogoPath", selectedPhotoPath)
            startActivity(intentPrice)
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_PICKER)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICKER && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri = data.data
            selectedImageUri?.let {
                try {
                    val inputStream = contentResolver.openInputStream(selectedImageUri)
                    val selectedPhotoBitmap = BitmapFactory.decodeStream(inputStream)
                    inputStream?.close()

                    selectedPhotoPath = saveBitmapToFile(selectedPhotoBitmap)
                    binding.imageViewPhoto.setImageBitmap(selectedPhotoBitmap)
                    binding.imageViewPhoto.isVisible = true

                    Toast.makeText(this, "Photo selected", Toast.LENGTH_SHORT).show()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun saveBitmapToFile(bitmap: Bitmap): String {
        val file = File(cacheDir, "${System.currentTimeMillis()}.png")
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
        return file.absolutePath
    }
}