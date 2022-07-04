package com.codebaron.filmworld.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codebaron.filmworld.models.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(private val filmsRepository: FilmsRepository): ViewModel() {

    private val _popularFilms = MutableLiveData<List<Result>>()
    private val _trendingFilms = MutableLiveData<List<Result>>()
    private val _topRatedFilms = MutableLiveData<List<Result>>()

    fun getPopularFilms(apiKey: String, language: String, page: String): LiveData<List<Result>> {
        viewModelScope.launch(Dispatchers.IO) {
            val films = filmsRepository.getPopularFilms(apiKey, language, page)
            _popularFilms.postValue(films)
        }
        return _popularFilms
    }

    fun getTrendingFilms(apiKey: String): LiveData<List<Result>> {
        viewModelScope.launch(Dispatchers.IO) {
            val films = filmsRepository.getTrendingFilms(apiKey)
            _trendingFilms.postValue(films)
        }
        return _trendingFilms
    }

    fun getTopRatedFilms(apiKey: String, language: String, page: String): LiveData<List<Result>> {
        viewModelScope.launch(Dispatchers.IO) {
            val films = filmsRepository.getTopRatedFilms(apiKey, language, page)
            _topRatedFilms.postValue(films)
        }
        return _topRatedFilms
    }
}