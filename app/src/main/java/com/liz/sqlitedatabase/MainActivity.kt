package com.liz.sqlitedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    class MainActivity : AppCompatActivity() {
        lateinit var enterName:EditText
        lateinit var enterAge:EditText
        lateinit var addName:Button
        lateinit var printName:Button
        lateinit var Name:TextView
        lateinit var Age:TextView
        @SuppressLint("Range")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            enterName=findViewById(R.id.enterName)
            enterAge=findViewById(R.id.enterAge)
            addName=findViewById(R.id.addName)
            printName=findViewById(R.id.printName)
            Name=findViewById(R.id.Name)
            Age=findViewById(R.id.Age)

            addName.setOnClickListener{


                val db = DBHelper(this, null)
                val name = enterName.text.toString()
                val age = enterAge.text.toString()

                db.addName(name, age)

                Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()

                enterName.text.clear()
                enterAge.text.clear()
            }

            printName.setOnClickListener{

                val db = DBHelper(this, null)
                val cursor = db.getName()

                cursor!!.moveToFirst()
                Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
                Age.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")

                // moving our cursor to next
                // position and appending values
                while(cursor.moveToNext()){
                    Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
                    Age.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
                }

                // at last we close our cursor
                cursor.close()
            }
        }
    }