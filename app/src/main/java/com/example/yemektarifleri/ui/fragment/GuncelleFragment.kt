package com.example.yemektarifleri.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yemektarifleri.R
import com.example.yemektarifleri.databinding.FragmentGuncelleBinding

class GuncelleFragment : Fragment() {

    private lateinit var binding: FragmentGuncelleBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = FragmentGuncelleBinding.inflate(inflater, container, false)




        return binding.root
    }

}
