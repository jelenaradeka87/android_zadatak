package com.androidzadatak.ui.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidzadatak.repository.FileRepositoryImpl
import com.androidzadatak.repository.MainRepository
import com.androidzadatak.repository.remote.ApiImpl

class MainViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val fileRepo = FileRepositoryImpl(context)
        val repository = MainRepository(
            api = ApiImpl(),
            fileRepo = fileRepo
        )
        return MainViewModel(repository) as T
    }
}