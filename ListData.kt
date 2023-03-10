package com.example.nolimits

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.ArrayList
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView
import android.widget.AdapterView.OnItemLongClickListener
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class ListData : AppCompatActivity() {
    var mDatabaseHelper: DatabaseHelper? = null
    private var mListView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_layout) // Edw exoume to layout
        mListView = findViewById(R.id.listView)
        mDatabaseHelper = DatabaseHelper(this)
        mDatabaseHelper!!.initialize()
        populateListView()

    }

    private fun populateListView() {

        val data = mDatabaseHelper!!.data
        val listData = ArrayList<String>()
        while (data.moveToNext()) {
            listData.add(data.getString(1))
        }

        val adapter: ListAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1 , listData)
        mListView!!.adapter = adapter
        
        mListView!!.setOnItemClickListener{ parent: AdapterView<*>, view: View, position:Int, id:Long ->
            val actualID = position+1

            val clickedName = mDatabaseHelper!!.getNameFromID(actualID)
            val clickedAddress = mDatabaseHelper!!.getAddressFromID(actualID)
            val clickedAccess = mDatabaseHelper!!.getAccessFromID(actualID)
            val clickedToilets = mDatabaseHelper!!.getToiletsFromID(actualID)

            if (position>=0){
                val intent = Intent(this, ShowPlace::class.java)
                intent.putExtra("name", clickedName)
                intent.putExtra("address", clickedAddress)
                intent.putExtra("access", clickedAccess)
                intent.putExtra("toilets", clickedToilets)
                toastMessage("Transferring you to $clickedName")
                startActivity(intent)
            }
        }
        mListView!!.onItemLongClickListener =
            OnItemLongClickListener { _, _, pos, _ ->
                val actualID = pos+1
                val clickedName = mDatabaseHelper!!.getNameFromID(actualID)
                val intent = Intent(this, ReviewForm::class.java)
                intent.putExtra("name", clickedName)
                startActivity(intent)
                true
            }


    }

    private fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "ListData"
    }
}