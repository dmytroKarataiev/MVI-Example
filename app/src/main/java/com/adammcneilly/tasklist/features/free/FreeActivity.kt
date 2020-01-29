package com.adammcneilly.tasklist.features.free

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.adammcneilly.tasklist.features.paid.PaidActivity
import com.adammcneilly.tasklist.features.tasks.adapters.TaskAdapter
import com.adammcneilly.tasklist.databinding.ActivityFreeBinding
import com.adammcneilly.tasklist.features.free.mvi.AdState
import com.adammcneilly.tasklist.features.free.viewmodels.AddsViewModel
import com.adammcneilly.tasklist.features.free.viewmodels.FreeStateViewModel
import com.adammcneilly.tasklist.features.free.viewmodels.FreeViewModelFactory
import com.adammcneilly.tasklist.features.tasks.mvi.TaskState
import com.adammcneilly.tasklist.features.tasks.viewmodels.TasksViewModel
import kotlinx.android.synthetic.main.activity_free.*
import kotlinx.android.synthetic.main.activity_paid.fab

class FreeActivity : AppCompatActivity() {

    private val tasksViewModel: TasksViewModel by viewModels { FreeViewModelFactory }
    private val adViewModel: AddsViewModel by viewModels { FreeViewModelFactory }
    private val viewModel: FreeStateViewModel by viewModels { FreeViewModelFactory }

    private val taskAdapter =
        TaskAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFreeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        setupViewModel(binding)
        initializeRecyclerView(binding)
    }

    private fun setupViewModel(binding: ActivityFreeBinding) {

        viewModel.init()
        adViewModel.init()
        tasksViewModel.init()

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        fab.setOnClickListener {
            startActivity(Intent(this, PaidActivity::class.java))
        }

        viewModel.state.observe(this, Observer { state ->
            when (state.ad) {
                is AdState.Loading -> {
                    Toast.makeText(this, "Loading Task", Toast.LENGTH_SHORT).show()
                }
                is AdState.Loaded -> {
                    ad.text = state.ad.ad.description
                }
                is AdState.Error -> {
                    Toast.makeText(this, "Error Task", Toast.LENGTH_SHORT).show()
                }
            }
            when (state.task) {
                is TaskState.Loading -> {
                    Toast.makeText(this, "Loading Ad", Toast.LENGTH_SHORT).show()
                }
                is TaskState.Loaded -> state.task.tasks.let(taskAdapter::tasks::set)
                is TaskState.Error -> {
                    Toast.makeText(this, "Error Ad", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initializeRecyclerView(binding: ActivityFreeBinding) {
        binding.taskList.adapter = taskAdapter
        binding.taskList.layoutManager = LinearLayoutManager(this)
    }

}