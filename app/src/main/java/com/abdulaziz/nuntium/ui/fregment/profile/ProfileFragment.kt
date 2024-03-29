package com.abdulaziz.nuntium.ui.fregment.profile

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdulaziz.nuntium.App
import com.abdulaziz.nuntium.R
import com.abdulaziz.nuntium.databinding.FragmentProfileBinding
import com.abdulaziz.nuntium.themes.LightTheme
import com.abdulaziz.nuntium.themes.MyAppTheme
import com.abdulaziz.nuntium.themes.NightTheme
import com.abdulaziz.nuntium.ui.activity.MainActivity
import com.abdulaziz.nuntium.ui.activity.MainView
import com.dolatkia.animatedThemeManager.AppTheme
import com.dolatkia.animatedThemeManager.ThemeFragment
import com.dolatkia.animatedThemeManager.ThemeManager
import javax.inject.Inject


class ProfileFragment : ThemeFragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sPref: SharedPreferences
    private var number: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("NUMBER")?.let {
            number = it
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        App.appComponent.inject(this)
        (activity as MainView?)?.showBottomBar()

        setOnClickListeners()

        if (sPref.getString("mode", "") == "night") {
            binding.switchButton.isChecked = true
        }


        return binding.root
    }

    override fun syncTheme(appTheme: AppTheme) {

        val myAppTheme = appTheme as MyAppTheme
        context?.let {
            // set background color
            binding.root.setBackgroundColor(myAppTheme.fragmentBackgroundColor(it))
            binding.lightModeCard.setCardBackgroundColor(
                myAppTheme.activityBottomNavViewBackColor(
                    it
                )
            )
            binding.languageCard.setCardBackgroundColor(myAppTheme.activityBottomNavViewBackColor(it))
            binding.titleTv.setTextColor(myAppTheme.fragmentLargeTextColor(it))
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    fun setOnClickListeners() {
        binding.apply {
            lightModeCard.setOnClickListener {
                (activity as MainActivity).disableBottomNav()
                switchButton.isChecked = !switchButton.isChecked
            }

            switchButton.setOnTouchListener { p0, p1 ->
                (activity as MainActivity).disableBottomNav()
                false
            }

            switchButton.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    sPref.edit().putString("mode", "night").apply()
                    binding.switchButton.let {
                        ThemeManager.instance.changeTheme(NightTheme(), it)
                    }
                } else {
                    sPref.edit().putString("mode", "day").apply()
                    binding.switchButton.let {
                        ThemeManager.instance.reverseChangeTheme(LightTheme(), it)

                    }
                }
            }

            languageCard.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, LanguageFragment())
                    .addToBackStack("language")
                    .commit()
            }
        }
    }


}