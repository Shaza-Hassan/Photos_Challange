package com.shaza.photoschallange

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.shaza.photoschallange.databinding.ActivityMainBinding
import com.shaza.photoschallange.photolist.ui.PhotosFragment


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        AppCenter.start(
            application,
            "2fd880fa-8028-4c5e-b2be-1f5abf9cf9d9",
            Analytics::class.java,
            Crashes::class.java
        )

        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_layout, PhotosFragment(), PhotosFragment().tag).commit()

        }
    }
}