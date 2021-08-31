package com.example.myapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samapp.week3.MyListAdapter
import com.samapp.week3.MyListData
import com.samapp.week3.Registration

class MainActivity : AppCompatActivity() {
    var list: List<MyListData>? = null //to take values from mylistdata
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = ArrayList<MyListData>()                                                              //values assigned as list in list from mylistdata class
        val Button: View = findViewById(R.id.Button)                                                //taking button from main xml

     //registerforactivityresult is used for getting result from an activity and StartActivityForResult->to send info from one activity to another

        var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                var data = result.data
                val name = data?.getStringExtra("name").toString()
                val email = data?.getStringExtra("email").toString()
                val gender = data?.getStringExtra("gender").toString()
                val time = data?.getStringExtra("time").toString()

                (list as ArrayList<MyListData>).add(MyListData(R.drawable.face,email,name,gender,time))    //to add data to mylistdata from registration form
                val recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView              //as->casts object to other object with ref
                val adapter = MyListAdapter(list)                                                       //list into adapter
                recyclerView.setHasFixedSize(true)                                                      //to prevent collapsing toolbar layout 7& should not effect recycl wn it is mt
                recyclerView.layoutManager = LinearLayoutManager(this)                           //to assign in linear manner
                recyclerView.adapter = adapter
                val insertIndex = 1
                adapter.notifyItemInserted(insertIndex)
            }
        }

        Button.setOnClickListener { view ->

            val intent= Intent(this, Registration::class.java)
                   resultLauncher.launch(intent)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return when (id) {
            R.id.item1 -> {

                val builder1 = AlertDialog.Builder(this)
                builder1.setMessage("Are You sure to logout?")
                builder1.setCancelable(true)
                builder1.setPositiveButton(
                    "Yes"
                ) { dialog, id -> dialog.cancel()
                }
                builder1.setNegativeButton(
                    "No"
                ) { dialog, id -> dialog.cancel() }
                val alert11 = builder1.create()
                alert11.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }




}