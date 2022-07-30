package com.example.task_6.presentation.di.components

import com.example.task_6.presentation.di.modules.ModuleViewModel
import com.example.task_6.presentation.di.modules.ReposModule
import com.example.task_6.presentation.ui.MapsActivity
import dagger.Component

@Component(modules = [ReposModule::class, ModuleViewModel::class])
interface AppComponent {
    fun inject(activity: MapsActivity)
}