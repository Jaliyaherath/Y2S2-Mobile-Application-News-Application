package com.example.labtest3.database
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class News(
    var item:String?
){
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}

