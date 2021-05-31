package com.nasa.wmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.nasa.wmart.viewmodel.NasaViewModel
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private lateinit var nasaViewModel: NasaViewModel
    private lateinit var imageView: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var txtTitle: TextView
    private lateinit var txtDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageview)
        txtTitle = findViewById(R.id.txt_title)
        txtDescription = findViewById(R.id.txt_description)
        progressBar = findViewById(R.id.progressBar)
        nasaViewModel = ViewModelProvider
            .AndroidViewModelFactory(application).create(NasaViewModel::class.java)

        nasaViewModel.observeNasaData()?.observe(this, {
            progressBar.visibility = View.GONE
            txtTitle.text = it.title
            txtDescription.text = it.explanation
            Picasso.with(this).load(it.url).into(imageView)
        })

        Handler().postDelayed({ nasaViewModel.fetchNasaImageData() }, 5000)
    }
}