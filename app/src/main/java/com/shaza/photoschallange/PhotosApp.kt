package com.shaza.photoschallange

import android.app.Application
import com.shaza.photoschallange.shared.koin.AppModule
import com.shaza.photoschallange.shared.koin.module
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

/**
 * Created by Shaza Hassan on 30/May/2023.
 */
class PhotosApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PhotosApp)
            modules(AppModule().module, module)
        }
    }
}