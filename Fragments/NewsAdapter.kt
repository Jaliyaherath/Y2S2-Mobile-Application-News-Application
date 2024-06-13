package com.example.labtest3

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.labtest3.database.News
import com.example.labtest3.database.NewsRepositary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NewsAdapter(items: List<News>,repositary: NewsRepositary, viewModel: MainActivityData): RecyclerView.Adapter<NewsViewHolder>()  {

     var context: Context? = null
    val items=items
    val repositary=repositary
    val viewModel=viewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_news,parent,false)
        context=parent.context
        return  NewsViewHolder(view)
    }

    override fun getItemCount(): Int {

        return items.size

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        holder.cbnews.text=items.get(position).item
        holder.ivdelete.setOnClickListener{
            val isChecked=holder.cbnews.isChecked
            if(isChecked){
                CoroutineScope(Dispatchers.IO).launch {
                    repositary.delete(items.get(position))
                    val data=repositary.getAllnews()
                    withContext(Dispatchers.Main){
                        viewModel.setData(data)
                    }
                }
                Toast.makeText(context,"Item deleted",Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(context,"Select delete news",Toast.LENGTH_LONG).show()
            }

        }


    }
}