package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_memo.view.*

class MyAdapter(var context: Context,var list: List<MemoEntity>,var onDeleteListener: OnDeleteListener) : RecyclerView.Adapter<MyAdapter.viewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var itemViewiew=LayoutInflater.from(context).inflate(R.layout.item_memo,parent,false)

        return  viewHolder(itemViewiew)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val memo=list[position]
        holder.memo.text=memo.memo
        holder.root.setOnLongClickListener(object :View.OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {
                onDeleteListener.OnDeleteListener(memo)
                return true
            }
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner  class viewHolder(itemview :View) : RecyclerView.ViewHolder(itemview){
        val memo=itemview.item_memo
        val root=itemview.root
    }
}