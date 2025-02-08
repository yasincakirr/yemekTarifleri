package com.example.yemektarifleri.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.yemektarifleri.R
import com.example.yemektarifleri.data.Sql.YemeklerDatabaseHelper
import com.example.yemektarifleri.databinding.FragmentListeleBinding
import com.example.yemektarifleri.ui.adapter.yemeklerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yemektarifleri.data.Entity.Yemekler

class ListeleFragment : Fragment() {

    private lateinit var binding: FragmentListeleBinding
    private lateinit var db:YemeklerDatabaseHelper
    private lateinit var yemeklerAdapter: yemeklerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = FragmentListeleBinding.inflate(inflater, container, false)



        binding.addButton.setOnClickListener {

        Navigation.findNavController(it).navigate(R.id.ekleGecis)

        }


        db = YemeklerDatabaseHelper(requireContext())
        yemeklerAdapter = yemeklerAdapter(requireContext(),db.yemekleriListele())


        binding.yemekListesi.layoutManager = LinearLayoutManager(requireContext())

        binding.yemekListesi.adapter = yemeklerAdapter

        return binding.root
    }





}