package com.adenikinju.myfirebaseapplication.ui.Screens.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adenikinju.myfirebaseapplication.Repository.MovieRepository.MovieRepository
import com.adenikinju.myfirebaseapplication.data.models.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableStateFlow<List<MovieModel>>(emptyList())
    val movies = _movies

    init{
        getMovies()
    }

    fun getMovies(){
        viewModelScope.launch {
            val result = repository.getMovies()
            _movies.emit(result)
        }
    }

}