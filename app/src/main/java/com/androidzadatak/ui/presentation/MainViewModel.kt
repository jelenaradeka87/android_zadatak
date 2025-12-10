package com.androidzadatak.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidzadatak.model.Competition
import com.androidzadatak.model.Match
import com.androidzadatak.model.Resource
import com.androidzadatak.model.Sport
import com.androidzadatak.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repo: MainRepository
) : ViewModel() {

    private val _sports = MutableStateFlow<List<Sport>>(emptyList())
    val sports = _sports.asStateFlow()

    private val _matches = MutableStateFlow<List<Match>>(emptyList())
    val matches = _matches.asStateFlow()

    private val _competitions = MutableStateFlow<List<Competition>>(emptyList())
    val competitions = _competitions.asStateFlow()

    private val _sportsLoading = MutableStateFlow(false)
    val sportsLoading = _sportsLoading.asStateFlow()

    private val _matchesLoading = MutableStateFlow(false)
    val matchesLoading = _matchesLoading.asStateFlow()

    private val _errors = MutableStateFlow<String?>(null)
    val errors = _errors.asStateFlow()

    init {
        loadSports()
        loadMatches()
        loadCompetitions()
    }

    fun loadSports() {
        viewModelScope.launch {
            repo.getSports().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _sportsLoading.value = true
                        resource.data?.let { _sports.value = it }
                    }
                    is Resource.Success -> {
                        _sports.value = resource.data
                        _sportsLoading.value = false
                    }
                    is Resource.Error -> {
                        _sportsLoading.value = false
                        resource.data?.let { _sports.value = it }
                        _errors.value = resource.message
                    }
                }
            }
        }
    }

    fun loadMatches() {
        viewModelScope.launch {
            repo.getMatches().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _matchesLoading.value = true
                        resource.data?.let { _matches.value = it }
                    }
                    is Resource.Success -> {
                        _matchesLoading.value = false
                        _matches.value = resource.data
                    }
                    is Resource.Error -> {
                        _matchesLoading.value = false
                        resource.data?.let { _matches.value = it }
                        _errors.value = resource.message
                    }
                }
            }
        }
    }

    fun loadCompetitions() {
        viewModelScope.launch {
            repo.getCompetitions().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        resource.data?.let { _competitions.value = it }
                    }
                    is Resource.Success -> {
                        _competitions.value = resource.data
                    }
                    is Resource.Error -> {
                        resource.data?.let { _competitions.value = it }
                        _errors.value = resource.message
                    }
                }
            }
        }
    }
}

