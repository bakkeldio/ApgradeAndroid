package io.flaterlab.tests

import android.app.Application
import io.flaterlab.tests.data.UserData

class Application : Application(){

    override fun onCreate() {
        super.onCreate()
        val data = UserData(this)
        data.removeToken()
    }
}