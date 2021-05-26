package com.nasa.wmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.nasa.wmart.viewmodel.NasaViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var nasaViewModel: NasaViewModel
    private lateinit var imageView: ImageView
    private lateinit var txtTitle: TextView
    private lateinit var txtDescription: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageview)
        txtTitle = findViewById(R.id.txt_title)
        txtDescription = findViewById(R.id.txt_description)
        nasaViewModel = ViewModelProvider
            .AndroidViewModelFactory(application).create(NasaViewModel::class.java)

        nasaViewModel.fetchNasaImageData()?.observe(this, {
            imageView.setImageResource(R.mipmap.ic_launcher_round)
            txtTitle.text = it.title
            txtDescription.text = it.description
        })
    }
}