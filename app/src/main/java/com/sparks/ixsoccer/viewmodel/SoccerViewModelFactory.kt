package com.sparks.ixsoccer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sparks.ixsoccer.data.repository.SoccerRepository

class SoccerViewModelFactory(private val soccerRepository: SoccerRepository) : ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SoccerViewModel(soccerRepository) as T
    }
}
