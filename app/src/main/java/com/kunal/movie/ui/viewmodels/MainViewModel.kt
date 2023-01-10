package com.kunal.movie.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kunal.movie.data.models.MovieDataResponse
import com.kunal.movie.data.models.MovieInfo
import com.kunal.movie.data.repository.MoviesRepository
import com.kunal.movie.utils.ApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private var _movieInfoResponse: MutableLiveData<MovieInfo> = MutableLiveData<MovieInfo>()
    val movieInfoResponse: LiveData<MovieInfo> = _movieInfoResponse

    fun getMovieInfo(
        movieId: String,
        onError: ((errorMessage: String?) -> Unit)? = null
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.getMovieData(movieId).collectLatest { resource ->
                when (resource.status) {
                    ApiStatus.SUCCESS -> {
                        Timber.d("--> API Call Success")
                        resource.data?.let { data ->
                            getMovieCredits(movieId, data, onError)
                        }
                    }
                    ApiStatus.LOADING -> {

                    }
                    ApiStatus.ERROR -> {
                        onError?.invoke(resource.message)
                    }
                }
            }
        }
    }

    private fun getMovieCredits(
        movieId: String,
        movieDataResponse: MovieDataResponse,
        onError: ((errorMessage: String?) -> Unit)? = null
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.getMovieCreditsData(movieId).collectLatest { resource ->
                when (resource.status) {
                    ApiStatus.SUCCESS -> {
                        Timber.d("--> API Call Success")
                        resource.data?.let { data ->
                            val movieInfo = MovieInfo(
                                movieDataResponse = movieDataResponse,
                                movieCreditsDataResponse = data
                            )
                            _movieInfoResponse.postValue(movieInfo)
                        }
                    }
                    ApiStatus.LOADING -> {

                    }
                    ApiStatus.ERROR -> {
                        onError?.invoke(resource.message)
                    }
                }
            }
        }
    }
}