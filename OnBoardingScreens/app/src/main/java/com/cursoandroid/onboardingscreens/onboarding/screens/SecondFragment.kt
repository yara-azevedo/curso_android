package com.cursoandroid.onboardingscreens.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.cursoandroid.onboardingscreens.R
import androidx.viewpager2.widget.ViewPager2 // Certifique-se de usar ViewPager2



class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate o layout para este fragmento
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        // Encontre o ViewPager2 corretamente
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        // Encontre o botão "next"
        val nextButton = view.findViewById<View>(R.id.imageButton)

        // Configure o clique do botão
        nextButton.setOnClickListener {
            viewPager?.currentItem = 2 // Alterar para o próximo item
        }

        return view
    }
}