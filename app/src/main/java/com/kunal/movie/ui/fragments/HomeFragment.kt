package com.kunal.movie.ui.fragments

import android.annotation.SuppressLint
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.kunal.movie.data.models.MovieCreditsDataResponse
import com.kunal.movie.data.models.MovieInfo
import com.kunal.movie.databinding.FragmentHomeBinding
import com.kunal.movie.ui.adapters.CastListAdapter
import com.kunal.movie.ui.adapters.CrewListAdapter
import com.kunal.movie.ui.base.BaseFragment
import com.kunal.movie.ui.viewmodels.MainViewModel
import com.kunal.movie.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val mainViewModel: MainViewModel by activityViewModels()

    private val _crewListAdapter by lazy {
        CrewListAdapter(
            emptyList(),
        )
    }

    private var crewListAdapter: CrewListAdapter? = null
        get() {
            kotlin.runCatching {
                field = _crewListAdapter
            }.onFailure {
                Timber.e(it)
            }
            return field
        }

    private val _castListAdapter by lazy {
        CastListAdapter(
            emptyList(),
        )
    }

    private var castListAdapter: CastListAdapter? = null
        get() {
            kotlin.runCatching {
                field = _castListAdapter
            }.onFailure {
                Timber.e(it)
            }
            return field
        }

    override fun initializeViews() {
        getData()
        binding.apply {
            progressBar.visible()
            nsv.gone()
            bottomContainer.gone()
            retryButton.gone()
            retryButton.setOnClickListener {
                retryButton.gone()
                progressBar.visible()
                getData()
            }
        }
    }

    private fun getData() {
        mainViewModel.getMovieInfo("432527") { errorMessage ->
            lifecycleScope.launch(Dispatchers.Main) {
                binding.retryButton.visible()
                binding.progressBar.gone()
                requireContext().showToastShort(errorMessage)
            }
        }
    }

    override fun initializeObservers() {
        mainViewModel.movieInfoResponse.observe(viewLifecycleOwner) { movieInfo ->
            movieInfo?.let {
                initializeViews(movieInfo)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initializeViews(movieInfo: MovieInfo) {
        binding.apply {
            retryButton.gone()
            progressBar.gone()
            nsv.visible()
            bottomContainer.visible()
            castRv.adapter = castListAdapter
            crewRv.adapter = crewListAdapter
            title.text = movieInfo.movieDataResponse.title
            ratings.text = "${movieInfo.movieDataResponse.voteAverage}/10"
            ratingCount.text = "${movieInfo.movieDataResponse.voteCount} votes"
            val languageList = movieInfo.movieDataResponse.spokenLanguages
            val genreList = movieInfo.movieDataResponse.genres
            var languagesText = ""
            var generesText = ""
            for (i in languageList.indices) {
                languagesText += when (i) {
                    0 -> {
                        languageList[i].englishName
                    }
                    else -> {
                        ", ${languageList[i].englishName}"
                    }
                }
            }
            for (i in genreList.indices) {
                generesText += when (i) {
                    0 -> {
                        genreList[i].name
                    }
                    else -> {
                        ", ${genreList[i].name}"
                    }
                }
            }
            languages.text = languagesText
            generes.text = generesText
            releaseDate.text = movieInfo.movieDataResponse.releaseDate
            overview.text = movieInfo.movieDataResponse.overview
            moviePoster.context.loadImage(moviePoster, movieInfo.movieDataResponse.posterPath)
            val originalCastList = movieInfo.movieCreditsDataResponse.cast
            val originalCrewList = movieInfo.movieCreditsDataResponse.crew
            val crewList = mutableListOf<MovieCreditsDataResponse.Crew>()
            val castList = mutableListOf<MovieCreditsDataResponse.Cast>()
            originalCrewList.forEach { crew ->
                crew?.let { item ->
                    item.profilePath?.let {
                        crewList.add(crew)
                    }
                }
            }
            originalCastList.forEach { cast ->
                cast?.let { item ->
                    item.profilePath?.let {
                        castList.add(cast)
                    }
                }
            }
            castListAdapter?.updateList(castList)
            crewListAdapter?.updateList(crewList)
            backButton.setOnClickListener {
                requireActivity().onBackPressed()
            }
            bookTicketsButton.setOnClickListener {
                it.showSnackBarLong("You Have Decided To Book The Tickets")
            }
        }
    }

}