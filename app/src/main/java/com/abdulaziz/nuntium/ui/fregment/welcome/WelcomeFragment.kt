package com.abdulaziz.nuntium.ui.fregment.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdulaziz.nuntium.R
import com.abdulaziz.nuntium.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(layoutInflater, container, false)
        binding.materialButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_welcome, TopicFragment())
                .addToBackStack("topic")
                .commit()
        }
        return binding.root
    }

}