package com.kunal.movie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kunal.movie.data.models.MovieCreditsDataResponse
import com.kunal.movie.databinding.ItemCrewBinding
import com.kunal.movie.utils.loadImage

class CrewListAdapter(
    crewList: List<MovieCreditsDataResponse.Crew>,
) : RecyclerView.Adapter<CrewListAdapter.CrewItemViewHolder>() {

    private var crewList: MutableList<MovieCreditsDataResponse.Crew>? = null

    init {
        this.crewList = crewList.toMutableList()
    }

    class CrewItemViewHolder(
        private val binding: ItemCrewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun initCrewItem(crew: MovieCreditsDataResponse.Crew?) {
            crew?.let {
                binding.apply {
                    profileImage.context.loadImage(profileImage, crew.profilePath)
                    name.text = crew.name
                    job.text = crew.job
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewItemViewHolder {
        val binding =
            ItemCrewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CrewItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CrewItemViewHolder, position: Int) {
        holder.initCrewItem(crewList?.get(position))
    }

    override fun getItemCount() = crewList?.size ?: 0

    fun updateList(newList: List<MovieCreditsDataResponse.Crew>) {
        crewList?.let { crewList ->
            crewList.clear()
            crewList.addAll(newList)
            notifyDataSetChanged()
        }
    }
}