package com.thetechnocafe.gurleensethi.liteutilities

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.thetechnocafe.gurleensethi.liteutils.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        val list = listOf("Test", "1", "2", "3", "This is a test", "123")
        recyclerView.layoutManager = LinearLayoutManager(this)
        val recyclerAdapter = RecyclerAdapterUtil<String>(this, list, R.layout.item_recycler_view)
        recyclerAdapter.addOnDataBindListener { itemView, item, position ->
            val textView = itemView.findViewById<TextView>(R.id.textView)
            textView.text = item
        }



        RecyclerAdapterUtil.Builder(this, list, R.layout.item_recycler_view)
                .bindView { itemView, item, position ->
                    val textView = itemView.findViewById<TextView>(R.id.textView)
                    textView.text = item
                }
                .addClickListener { item, position ->
                    coloredShortToast(item, android.R.color.darker_gray, android.R.color.black)
                    //Take action when item is pressed
                }
                .addLongClickListener { item, position ->
                    //Take action when item is long pressed
                }
                .into(recyclerView)

        shortToast("This is a short toast")
        longToast("This is a long toast")

        sharedPreferences("SP", Context.MODE_PRIVATE) {
            putString("string", "Some Value 123")
            putInt("integer", 1)
        }

        defaultSharedPreferences {
            putString("string", "in default sp")
        }

        getFromSharedPreferences<String>("SP", "string", "default")
        getFromDefaultSharedPreferences<String>("string", "default")
    }
}
