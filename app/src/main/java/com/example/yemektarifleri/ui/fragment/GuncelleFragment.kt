package com.example.yemektarifleri.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.yemektarifleri.R
import com.example.yemektarifleri.data.Entity.Yemekler
import com.example.yemektarifleri.data.Sql.YemeklerDatabaseHelper
import com.example.yemektarifleri.databinding.FragmentGuncelleBinding

class GuncelleFragment : Fragment() {

    private lateinit var binding: FragmentGuncelleBinding
    private lateinit var db: YemeklerDatabaseHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = FragmentGuncelleBinding.inflate(inflater, container, false)
        db = YemeklerDatabaseHelper(requireContext())

        val bundle:GuncelleFragmentArgs by navArgs()


        var gelenYemek = bundle.yemek

        binding.yemekGuncelleAdEditText.setText(gelenYemek.yemekad)
        binding.yemekicerikGuncelleEditText.setText(gelenYemek.yemekicerik)
        binding.yemekYapilisGuncelleEditText.setText(gelenYemek.yemekyapilis)



        binding.guncelle.setOnClickListener {
            val newYemekAd = binding.yemekGuncelleAdEditText.text.toString()
            val newYemekicerik = binding.yemekicerikGuncelleEditText.text.toString()
            val newYemekyapilis = binding.yemekYapilisGuncelleEditText.text.toString()

            val guncelYemek = Yemekler(gelenYemek.id,newYemekAd,newYemekicerik,newYemekyapilis)
            db.yemekleriGuncelle(guncelYemek)

            Navigation.findNavController(it).navigate(R.id.listeGecis)
            Toast.makeText(requireContext(),"Guncellendi",Toast.LENGTH_LONG).show()
        }

        return binding.root
    }

}
