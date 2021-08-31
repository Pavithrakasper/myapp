package com.samapp.week3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.MainActivity
import kotlinx.android.synthetic.main.activity_profile.*
class ProfileActivity : AppCompatActivity() {

    private lateinit var homebtn: Button
    private lateinit var del: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val bundle: Bundle? = intent.extras
        val name = bundle!!.getString("name")
        val img = bundle!!.getInt("img")
        val email = bundle!!.getString("email")
        UserName.text = "Name: " + name
        UserEmail.text = "Email: " + email
        val gender = bundle!!.getString("gender")
        val time = bundle!!.getString("time")
        TimeEntered.text= "time: " + time
        UserGender.text = "Gender: " +gender
        imageView.setImageResource(img)

        homebtn = findViewById(R.id.button1)

        del = findViewById(R.id.delete)

        homebtn.setOnClickListener {
            val backIntent = Intent(this, MainActivity::class.java)
//            startActivity(backIntent)
            finish()
        }

        del.setOnClickListener {


            val builder1 = AlertDialog.Builder(this)
            builder1.setMessage("Are You sure to delete?")
            builder1.setCancelable(true)
            builder1.setPositiveButton(

                "Yes"
            ) { dialog, id -> dialog.cancel() }
            builder1.setNegativeButton(
                "No"
            ) { dialog, id -> dialog.cancel() }
            val alert11 = builder1.create()
            alert11.show()
            true
        }





    }
}
