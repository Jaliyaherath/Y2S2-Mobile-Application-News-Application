package com.example.labtest3

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class NewsViewHolder(view: View):ViewHolder(view) {
    val cbnews:CheckBox=view.findViewById(R.id.cbnews)
    val ivdelete:ImageView=view.findViewById(R.id.ivdelete)



}