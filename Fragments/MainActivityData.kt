package com.example.labtest3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.labtest3.database.News

class MainActivityData:ViewModel() {

    private val _data=MutableLiveData<List<News>>()
    val data:LiveData<List<News>> =_data
            fun setData(data:List<News>){
                _data.value=data
            }

}