package com.example.newzapp.screens.mainscreencomponents

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newzapp.blueprint.NewsAppStructure
import com.example.newzapp.dataOrException.DataOrException
import com.example.newzapp.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {
    val newzData: MutableState<DataOrException<NewsAppStructure, Boolean, Exception>> =
        mutableStateOf(
            DataOrException(null, true, Exception(""))
        )

    init {
        fetchNewz()
    }

    private fun fetchNewz() {
        viewModelScope.launch {
            newzData.value.loading = true
            newzData.value = repository.getAllNewz()
            if (newzData.value.toString().isNotEmpty()) newzData.value.loading = false
        }
    }
}