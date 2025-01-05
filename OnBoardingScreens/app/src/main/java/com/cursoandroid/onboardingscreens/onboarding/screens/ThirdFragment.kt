package com.cursoandroid.onboardingscreens.onboarding.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.cursoandroid.onboardingscreens.R
import androidx.viewpager2.widget.ViewPager2 // Certifique-se de usar ViewPager2


class ThirdFragment : Fragment() {
      override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
          // Inflate o layout para este fragmento
          val view = inflater.inflate(R.layout.fragment_third, container, false)

          // Encontre o botão "next"
          val nextButton = view.findViewById<View>(R.id.imageButton)

          nextButton.setOnClickListener {
              findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment)
              onBoardingFinished()
          }

          return view
      }

    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished",true)
        editor.apply()
    }
}