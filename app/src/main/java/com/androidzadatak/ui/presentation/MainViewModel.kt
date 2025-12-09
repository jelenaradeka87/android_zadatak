package com.androidzadatak.ui.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidzadatak.model.Competition
import com.androidzadatak.model.Match
import com.androidzadatak.model.Sport
import com.androidzadatak.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepository = MainRepository()
) : ViewModel() {

    val sports = mutableStateOf<List<Sport>>(emptyList())
    val matches = mutableStateOf<List<Match>>(emptyList())
    val competitions = mutableStateOf<List<Competition>>(emptyList())
    val error = mutableStateOf<String?>(null)
    val isLoading = mutableStateOf(false)

    fun loadSports() {
        viewModelScope.launch {
            isLoading.value = true
            mainRepository.getAllSports()
                .onSuccess { list ->
                    sports.value = list
                    error.value = null
                }
                .onFailure { e ->
                    error.value = e.message
                }
            isLoading.value = false
        }
    }

    fun loadMatches() {
        viewModelScope.launch {
            isLoading.value = true
            mainRepository.getMatches()
                .onSuccess { list ->
                    matches.value = list
                    error.value = null
                }
                .onFailure { e ->
                    error.value = e.message
                }
            isLoading.value = false
        }
    }

    fun loadCompetitions() {
        viewModelScope.launch {
            isLoading.value = true
            mainRepository.getCompetitions()
                .onSuccess { list ->
                    competitions.value = list
                    error.value = null
                }
                .onFailure { e ->
                    error.value = e.message
                }
            isLoading.value = false
        }
    }
}