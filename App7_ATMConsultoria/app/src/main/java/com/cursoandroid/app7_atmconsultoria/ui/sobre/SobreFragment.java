package com.cursoandroid.app7_atmconsultoria.ui.sobre;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cursoandroid.app7_atmconsultoria.R;
import com.cursoandroid.app7_atmconsultoria.databinding.FragmentSobreBinding;

import mehdi.sakout.aboutpage.AboutPage;

public class SobreFragment extends Fragment {

    private FragmentSobreBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return new  AboutPage(getActivity())
                .setImage(R.drawable.logo)
                .addGroup("Connect with us")
                .addEmail("atm@consultoria.com")
                .addWebsite("https://atm_consultoria.com/")
                .addFacebook("the.medy")
                .addTwitter("medyo80")
                .addYoutube("UCdPQtdWIsg7_pi4mrRu46vA")
                .addPlayStore("com.ideashower.readitlater.pro")
                .addGitHub("medyo")
                .addInstagram("medyo80")
                .create();

        //return inflater.inflate(R.layout.fragment_sobre, container,false);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}