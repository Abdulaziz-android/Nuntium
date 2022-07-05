package com.abdulaziz.nuntium.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abdulaziz.nuntium.R
import com.abdulaziz.nuntium.databinding.ActivityWelcomeBinding
import com.abdulaziz.nuntium.ui.fregment.welcome.SplashFragment

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_welcome, SplashFragment())
            .commit()
    }
}