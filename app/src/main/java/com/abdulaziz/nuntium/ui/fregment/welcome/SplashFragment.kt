package com.abdulaziz.nuntium.ui.fregment.welcome

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdulaziz.nuntium.App
import com.abdulaziz.nuntium.R
import com.abdulaziz.nuntium.databinding.FragmentSplashBinding
import com.abdulaziz.nuntium.ui.activity.MainActivity
import com.abdulaziz.nuntium.ui.activity.WelcomeActivity
import com.dolatkia.animatedThemeManager.AppTheme
import com.dolatkia.animatedThemeManager.ThemeFragment
import javax.inject.Inject

class SplashFragment : ThemeFragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var sPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        App.appComponent.inject(this)


        val user = sPref.getBoolean("isUserRegistered", false)
        val handler = Handler(Looper.getMainLooper())
        val runnable = Runnable {
            if (!user) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_welcome, OnBoardingFragment())
                    .commit()
            } else {
                (activity as WelcomeActivity?)?.finish()
                val intent =
                    Intent(binding.root.context.applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
        }

        handler.postDelayed(runnable, 1500)
        return binding.root
    }

    override fun syncTheme(appTheme: AppTheme) {

    }
}