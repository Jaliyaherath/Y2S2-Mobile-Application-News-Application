package com.example.labtest3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.labtest3.database.News
import com.example.labtest3.database.NewsDatabase
import com.example.labtest3.database.NewsRepositary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val homeFragment=HomeFragment()
    private val settingsFragment=SettingsFragment()
    private val newsFragment=NewsFragment()
    private lateinit var adapter:NewsAdapter
    private lateinit var viewModel:MainActivityData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnhome:Button=findViewById(R.id.btnhome)
        val btnSettings:Button=findViewById(R.id.btnSettings)

        val btnViews:Button=findViewById(R.id.btnViews)

        val btnnsubmit:Button=findViewById(R.id.btnnsubmit)

        //btnnsubmit.setOnClickListener{
            //displayLeart(repositary = )
        //}



        val repositary=NewsRepositary(NewsDatabase.getInstance(this))
        val recyclerView:RecyclerView=findViewById(R.id.rvnewslist)
        val viewModel=ViewModelProvider(this)[MainActivityData::class.java]


        viewModel.data.observe(this){
             adapter=NewsAdapter(it,repositary,viewModel)
            recyclerView.adapter=adapter
            recyclerView.layoutManager=LinearLayoutManager(this)
        }

        CoroutineScope(Dispatchers.IO).launch {
            val data = repositary.getAllnews()
            runOnUiThread {
                viewModel.setData(data)
            }
        }












        btnhome.setOnClickListener {
            loadHome()

        }
        btnSettings.setOnClickListener{
            loadSettings()

        }
        btnViews.setOnClickListener{
            loadNews()
        }


    }



    private fun displayLeart(repositary:NewsRepositary){
        val builder=AlertDialog.Builder(this)

        builder.setTitle(getText(R.string.alert))
        builder.setMessage(getText(R.string.Msg))

        val input= EditText(this)
        input.inputType=InputType.TYPE_CLASS_TEXT

        builder.setView(input)
        builder.setPositiveButton("OK") { dialog, which ->
            val item = input.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                repositary.insert(News(item))
                val data = repositary.getAllnews()
                runOnUiThread {
                    viewModel.setData(data)
                }
            }
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }

        val alertDialog = builder.create()
        alertDialog.show()


    }

    private fun loadHome(){
        val fragment=supportFragmentManager.findFragmentById(R.id.fragmentContainer)

        if(fragment== null){
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,homeFragment).commit()

        }else{
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,homeFragment).commit()
       }

    }

    private fun loadSettings(){
        val fragment=supportFragmentManager.findFragmentById(R.id.fragmentContainer)

        if(fragment== null){
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,settingsFragment).commit()

        }else{
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,settingsFragment).commit()
        }

    }
    private fun loadNews(){
        val fragment=supportFragmentManager.findFragmentById(R.id.fragmentContainer)

        if(fragment==null){
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,newsFragment).commit()

        }else{
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,newsFragment).commit()
        }
    }
}