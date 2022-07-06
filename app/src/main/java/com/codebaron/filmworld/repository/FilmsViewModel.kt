package com.codebaron.filmworld.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codebaron.filmworld.models.filmcredits.FilmCasts
import com.codebaron.filmworld.models.filmdetails.FilmDetailsData
import com.codebaron.filmworld.models.filmsdata.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(private val filmsRepository: FilmsRepository): ViewModel() {

    private val _popularFilms = MutableLiveData<List<Result>>()
    private val _trendingFilms = MutableLiveData<List<Result>>()
    private val _topRatedFilms = MutableLiveData<List<Result>>()
    private val _filmDetails = MutableLiveData<FilmDetailsData>()
    private val _filmCasts = MutableLiveData<FilmCasts>()
    private val _similarMovies = MutableLiveData<List<Result>>()

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

    fun getFilmDetails(apiKey: String, language: String, movieId: String): LiveData<FilmDetailsData> {
        viewModelScope.launch {
            val filmDetails = filmsRepository.getFilmDetails(apiKey, language, movieId)
            _filmDetails.postValue(filmDetails)
        }
        return _filmDetails
    }

    fun getFilmCast(apiKey: String, language: String, movieId: String): LiveData<FilmCasts> {
        viewModelScope.launch {
            val filmCasts = filmsRepository.getFilmCredits(apiKey, language, movieId)
            _filmCasts.postValue(filmCasts)
        }
        return _filmCasts
    }

    fun getSimilarMovies(apiKey: String, language: String, movieId: String): LiveData<List<Result>> {
        viewModelScope.launch {
            val similarMovies = filmsRepository.getSimilarFilms(apiKey, language, movieId)
            _similarMovies.postValue(similarMovies)
        }
        return _similarMovies
    }
}