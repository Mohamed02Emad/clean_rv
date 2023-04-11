package com.example.ooprvtask.ui.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ooprvtask.data.models.User
import com.example.ooprvtask.databinding.UserLayoutBinding

class TaskAdapter(val userList : ArrayList<User>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(val binding: UserLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            UserLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val user = userList[position]
        holder.binding.apply {
            userName.text = user.username
        }
    }


}