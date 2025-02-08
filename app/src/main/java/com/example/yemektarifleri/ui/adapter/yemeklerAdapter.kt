package com.example.yemektarifleri.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yemektarifleri.data.Entity.Yemekler
import com.example.yemektarifleri.data.Sql.YemeklerDatabaseHelper
import com.example.yemektarifleri.databinding.YemekItemBinding

class yemeklerAdapter(var mConntext: Context, var yemekler:List<Yemekler>): RecyclerView.Adapter<yemeklerAdapter.yemekViewHolder>() {

    private val db :YemeklerDatabaseHelper = YemeklerDatabaseHelper(mConntext)

    inner class yemekViewHolder(var yemek_item: YemekItemBinding):RecyclerView.ViewHolder(yemek_item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): yemekViewHolder {
        val tasarim =YemekItemBinding.inflate(LayoutInflater.from(mConntext),parent,false)
        return yemekViewHolder(tasarim)
    }


    override fun onBindViewHolder(holder: yemekViewHolder, position: Int) {
        val yemek = yemekler.get(position)
        val t =holder.yemek_item


        t.baslikTextView.text = yemek.yemekad
        t.yemekicerik.text = yemek.yemekicerik
        t.yemekYapilis.text = yemek.yemekyapilis

    }

    fun yenileData(yeniYemekler: List<Yemekler>){
        yemekler = yeniYemekler
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return yemekler.size
    }
}