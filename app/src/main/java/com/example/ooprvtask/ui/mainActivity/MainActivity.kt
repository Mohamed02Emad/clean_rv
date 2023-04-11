package com.example.ooprvtask.ui.mainActivity

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ooprvtask.data.models.User
import com.example.ooprvtask.databinding.ActivityMainBinding
import com.example.ooprvtask.ui.recyclerView.TaskAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: TaskAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            viewModel.setInitial()
            setObservers()
            setOnClickListeners()
            setUpRecyclerView()
        }

    }

    private  fun setObservers() {
        viewModel.userList.observe(this){
            try {
                myAdapter.notifyDataSetChanged()
            }catch (_:Exception){}
        }
    }

    private fun setOnClickListeners() {
        binding.apply {
            btnAdd.setOnClickListener {
                btnAddClicked()
            }
        }
    }

    private fun setUpRecyclerView() {
         myAdapter = TaskAdapter(viewModel.userList.value!!)
            val myLayoutManager = LinearLayoutManager(this@MainActivity)
            binding.rvHome.apply {
                adapter = myAdapter
                layoutManager = myLayoutManager
            }
    }



    private fun btnAddClicked() {
        showNameDialog()
    }

    private fun showNameDialog() {
        val dialogName: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        dialogName.setTitle("Enter user name")

        val editTxtName = EditText(this)
        dialogName.setView(editTxtName)

        dialogName.setPositiveButton("save") { dialogInterface, _ ->
                val name = editTxtName.text.toString()
                val isNameValid = viewModel.validateName(name)
                if (isNameValid) {
                    viewModel.saveName(User(name))
                    dialogInterface.cancel()
                } else {
                    showToast("name is not valid")
                }
            }

        dialogName.setNegativeButton("cancel"){ dialogInterface, _ ->
                dialogInterface.cancel()
        }

        dialogName.show()

    }

    private fun showToast(text: String) {
        lifecycleScope.launch(Dispatchers.Main) {
            Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
        }
    }

}