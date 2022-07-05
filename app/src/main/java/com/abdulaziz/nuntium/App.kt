package com.abdulaziz.nuntium

import android.app.Application
import android.content.Context
import com.abdulaziz.nuntium.di.component.AppComponent
import com.abdulaziz.nuntium.di.component.DaggerAppComponent
import com.abdulaziz.nuntium.di.module.DatabaseModule
import com.abdulaziz.nuntium.di.module.NetworkModule
import com.abdulaziz.nuntium.utils.LocaleHelper

class App : Application() {

    companion object{
        const val API_KEY = "H1J12hztuqaZiKQgJMUvDyCfyIX1VvMuDfbYIdnE"
        lateinit var appComponent: AppComponent
    }


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .networkModule(NetworkModule(this))
            .build()
    }

}