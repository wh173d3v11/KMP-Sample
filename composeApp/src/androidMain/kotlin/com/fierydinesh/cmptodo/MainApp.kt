package com.fierydinesh.kmppractice

import android.app.Application

class MainApp : Application() {

    // Companion object to hold the application instance
    companion object {
        private lateinit var instance: MainApp

        fun getInstance(): MainApp = instance

        val applicationContext
            get() = getInstance().applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}