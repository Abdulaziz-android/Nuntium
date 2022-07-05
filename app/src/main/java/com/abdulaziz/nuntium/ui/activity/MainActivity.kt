package com.abdulaziz.nuntium.ui.activity

import android.animation.Animator
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import com.abdulaziz.nuntium.App
import com.abdulaziz.nuntium.R
import com.abdulaziz.nuntium.databinding.ActivityMainBinding
import com.abdulaziz.nuntium.themes.LightTheme
import com.abdulaziz.nuntium.themes.MyAppTheme
import com.abdulaziz.nuntium.themes.NightTheme
import com.abdulaziz.nuntium.ui.fregment.bookmark.BookmarkFragment
import com.abdulaziz.nuntium.ui.fregment.category.CategoryFragment
import com.abdulaziz.nuntium.ui.fregment.home.HomeFragment
import com.abdulaziz.nuntium.ui.fregment.profile.ProfileFragment
import com.abdulaziz.nuntium.utils.LocaleHelper
import com.dolatkia.animatedThemeManager.AppTheme
import com.dolatkia.animatedThemeManager.ThemeActivity
import com.dolatkia.animatedThemeManager.ThemeAnimationListener

class MainActivity : ThemeActivity(), MainView, ThemeAnimationListener {

    private lateinit var binding: ActivityMainBinding
    lateinit var sPref:SharedPreferences

    override fun getStartTheme(): AppTheme {
        sPref = applicationContext.getSharedPreferences("shared", MODE_PRIVATE)
        return if (sPref.getString("mode", "") == "night") {
            NightTheme()
        } else LightTheme()
    }

    override fun attachBaseContext(newBase: Context?) {
        LocaleHelper.onAttach(newBase!!)
        super.attachBaseContext(newBase)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setThemeAnimationListener(this)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .commit()
        setUpBottomNav()

    }

    override fun syncTheme(appTheme: AppTheme) {
        val myAppTheme = appTheme as MyAppTheme
        this.let {
            // set background color
            binding.bottomNavView.setBackgroundColor(myAppTheme.activityBottomNavViewBackColor(it))
        }
    }


    private fun setUpBottomNav() {
        binding.bottomNavView.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
            R.id.home -> {
                HomeFragment()
            }
            R.id.category -> {
                CategoryFragment()
            }
            R.id.bookmark -> {
                BookmarkFragment()
            }
            R.id.profile -> {
                ProfileFragment()
            }
            else -> HomeFragment()
        }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
            true
        }
    }

    override fun hideBottomBar() {
        binding.bottomNavView.visibility = View.GONE
    }

    override fun showBottomBar() {
        binding.bottomNavView.visibility = View.VISIBLE
    }

    override fun backPressed() {
        onBackPressed()
    }

    override fun restart() {
        recreate()
    }

    override fun onAnimationCancel(animation: Animator) {}

    override fun onAnimationEnd(animation: Animator) {
        binding.view.visibility = View.GONE
    }

    override fun onAnimationRepeat(animation: Animator) {}

    override fun onAnimationStart(animation: Animator) {}

    fun disableBottomNav(){
        binding.view.visibility = View.VISIBLE
    }

}