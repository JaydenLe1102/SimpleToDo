package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    var listOfTasks = mutableListOf<String>()
    lateinit var adapter: TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongClickListener = object: TaskItemAdapter.OnLongClickListener{
            override fun onItemLongClicked(position: Int) {
                //1. Remove Item from the list
                listOfTasks.removeAt(position)
                //2. Notify the adapter sth has changed
                adapter.notifyDataSetChanged()

                saveItems()
            }

        }

        //1. Let's Detect when the user clicks on the add button
//        findViewById<Button>(R.id.button).setOnClickListener{
//            //code in here is going to be executed when the user clicks on a button
//            Log.i("Hello", "User clicked on Button")
//        }

//        listOfTasks.add("do homework")
//        listOfTasks.add("hello Tracy")

        loadItems()

        //look up the recycler view in layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // Create adapter passing in the sample user data
        adapter = TaskItemAdapter(listOfTasks, onLongClickListener)

        // Attach the adapter to the recyclerview to populate items
        recyclerView.adapter = adapter
        // Set layout manager to position the items
        recyclerView.layoutManager = LinearLayoutManager(this)


        val inputTextField = findViewById<EditText>(R.id.addTaskField)
        //Set up the button and input field, so that the user can enter a task

        //get a reference to the button
        //and then set an onclicklistener
        findViewById<Button>(R.id.button).setOnClickListener{

            //1. Grab the txt the user has inputted into @id/addTaskField


            val userInputtedTask = inputTextField.text.toString()

            //2. Add the string to our list of tasks: listOfTasks
            listOfTasks.add(userInputtedTask)

            //Notify the adapter that our data has been updated
            adapter.notifyItemInserted(listOfTasks.size - 1)

            //3. Rest text field
            inputTextField.setText("")

            saveItems()
        }
    }

    //Save the data that the user has inputted by save data and writing and reading from a file

    //create a method to get the file we need
    //Get the file we need
    fun getDataFile(): File {
        //Everyline is going to represent a specific task in our list of tasks
        return File(filesDir, "data.txt")
    }

    //load the items by reading every line in the data file
    fun loadItems(){
        try {
            listOfTasks =
                org.apache.commons.io.FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        } catch(ioException: IOException){
            ioException.printStackTrace()
        }
    }

    //save items by writing them into our data file

    fun saveItems(){
        try{
            org.apache.commons.io.FileUtils.writeLines(getDataFile(), listOfTasks)
        }catch (ioException: IOException){
            ioException.printStackTrace()
        }
    }
}