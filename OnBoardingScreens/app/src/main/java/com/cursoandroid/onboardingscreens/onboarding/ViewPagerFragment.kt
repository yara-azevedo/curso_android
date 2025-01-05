package com.cursoandroid.onboardingscreens.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cursoandroid.onboardingscreens.databinding.FragmentViewPagerBinding
import com.cursoandroid.onboardingscreens.onboarding.screens.FirstFragment
import com.cursoandroid.onboardingscreens.onboarding.screens.SecondFragment
import com.cursoandroid.onboardingscreens.onboarding.screens.ThirdFragment

class ViewPagerFragment : Fragment() {

    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using View Binding
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val view = binding.root

        val fragmentList = arrayListOf(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()
        )

        val adapter = ViewPagerAdapter(
            requireActivity(), // Passando o FragmentActivity corretamente
            fragmentList       // Lista de fragments como segundo argumento
        )

        binding.viewPager.adapter = adapter

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}