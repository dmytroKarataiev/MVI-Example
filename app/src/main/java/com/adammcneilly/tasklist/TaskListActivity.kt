package com.adammcneilly.tasklist

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.adammcneilly.tasklist.adapters.TaskAdapter
import com.adammcneilly.tasklist.databinding.ActivityTaskListBinding
import com.adammcneilly.tasklist.mvi.TaskListAdState
import com.adammcneilly.tasklist.mvi.TaskListItemState
import com.adammcneilly.tasklist.viewmodels.AddsViewModel
import com.adammcneilly.tasklist.viewmodels.StateViewModel
import com.adammcneilly.tasklist.viewmodels.TaskListViewModel
import com.adammcneilly.tasklist.viewmodels.TaskListViewModelFactory
import kotlinx.android.synthetic.main.activity_task_list.*

class TaskListActivity : AppCompatActivity() {

    private val tasksViewModel: TaskListViewModel by viewModels { TaskListViewModelFactory }
    private val adViewModel: AddsViewModel by viewModels { TaskListViewModelFactory }
    private val viewModel: StateViewModel by viewModels { TaskListViewModelFactory }

    private val taskAdapter = TaskAdapter()

    private lateinit var binding: ActivityTaskListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        setupViewModel()
        initializeRecyclerView()
    }

    private fun setupViewModel() {

        viewModel.init()
        adViewModel.init()
        tasksViewModel.init()

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        fab.setOnClickListener {
            tasksViewModel.addButtonClicked()
        }

        viewModel.state.observe(this, Observer { state ->
            when (state.ad) {
                is TaskListAdState.Loading -> {
                    Toast.makeText(this, "Loading Task", Toast.LENGTH_SHORT).show()
                }
                is TaskListAdState.Loaded -> {
                    ad.text = state.ad.ad.description
                }
                is TaskListAdState.Error -> {
                    Toast.makeText(this, "Error Task", Toast.LENGTH_SHORT).show()
                }
            }
            when (state.task) {
                is TaskListItemState.Loading -> {
                    Toast.makeText(this, "Loading Ad", Toast.LENGTH_SHORT).show()
                }
                is TaskListItemState.Loaded -> state.task.tasks.let(taskAdapter::tasks::set)
                is TaskListItemState.Error -> {
                    Toast.makeText(this, "Error Ad", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initializeRecyclerView() {
        binding.taskList.adapter = taskAdapter
        binding.taskList.layoutManager = LinearLayoutManager(this)
    }

}