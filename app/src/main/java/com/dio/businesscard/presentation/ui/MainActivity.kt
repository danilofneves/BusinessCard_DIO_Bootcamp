package com.dio.businesscard.presentation.ui

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.dio.businesscard.R
import com.dio.businesscard.core.ViewModelFactory
import com.dio.businesscard.domain.exception.CardException
import com.dio.businesscard.presentation.extensions.observe
import com.dio.businesscard.presentation.extensions.toast
import com.dio.businesscard.presentation.viewmodel.CardViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpPermissions()
    }

    private fun setUpPermissions() {
        // write permission to access the storage
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            1
        )
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            1
        )
    }
}