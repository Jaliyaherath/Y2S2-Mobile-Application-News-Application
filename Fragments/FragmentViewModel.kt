package com.example.labtest3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentViewModel:ViewModel() {

    private val backgroundColor = MutableLiveData<Int>()
    private val newsText=MutableLiveData<String>()
    fun getBackgroundColor(): LiveData<Int> {
        return backgroundColor
    }
    fun setBackground(color:Int){
        backgroundColor.value = color
    }
    fun getNewsText():LiveData<String>{
        return newsText
    }
    fun setNews(news:String){
        newsText.value =news
    }
}