package com.andymariscal.walmartproducts.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.andymariscal.walmartproducts.R
import com.andymariscal.walmartproducts.ui.MainActivity

/**
 * Demo purpose only
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        Handler().postDelayed({
            runOnUiThread {
                startActivity(with(Intent(this, MainActivity::class.java)) {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    this
                })
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
        }, 2000)

    }
}