package com.example.yemektarifleri.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.yemektarifleri.R
import com.example.yemektarifleri.data.Entity.Yemekler
import com.example.yemektarifleri.data.Sql.YemeklerDatabaseHelper
import com.example.yemektarifleri.databinding.FragmentEkleBinding

class EkleFragment : Fragment() {

    private lateinit var binding: FragmentEkleBinding
    private lateinit var db : YemeklerDatabaseHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = FragmentEkleBinding.inflate(inflater, container, false)


        db = YemeklerDatabaseHelper(requireContext())

        binding.kayit.setOnClickListener {
            val yemekAd = binding.yemekAdEditText.text.toString()
            val yemekicerik = binding.yemekicerikEditText.text.toString()
            val yemekyapilis = binding.yemekYapilisEditText.text.toString()

            val yemek = Yemekler(0, yemekAd, yemekicerik, yemekyapilis)

            db.insertyemek(yemek)

            Toast.makeText(requireContext(),"Yemek Kaydedildi",Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).navigate(R.id.kayitGecis)

        }

        return binding.root
    }

}