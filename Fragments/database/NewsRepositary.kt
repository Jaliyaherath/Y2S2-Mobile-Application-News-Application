package com.example.labtest3.database

class NewsRepositary(private val db:NewsDatabase) {
    suspend fun insert(news: News)=db.getNewsDao().insert(news)
    suspend fun delete(news: News)=db.getNewsDao().delete(news)

    fun getAllnews():List<News> = db.getNewsDao().getAllNews()

}