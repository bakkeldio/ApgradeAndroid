package io.flaterlab.tests.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.flaterlab.tests.R
import io.flaterlab.tests.data.UserData
import io.flaterlab.tests.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_greeting.*

class GreetingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greeting)
        val data = UserData(this)
        Thread {
            Thread.sleep(1000)
           if (data.getToken().isNullOrEmpty()) {
               nextActivity.visibility = View.VISIBLE
               //startActivity(Intent(this, ApgradeMainActivity::class.java))
               //Thread.sleep(1000)
               //finish()
           }else{
               startActivity(Intent(this, ApgradeMainActivity::class.java))
           }
        }.start()
        nextActivity.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
/*
    private fun finishWithDelay(){
        Thread{
            Thread.sleep(1000)
            finish()
        }.start()
    }

 */
}