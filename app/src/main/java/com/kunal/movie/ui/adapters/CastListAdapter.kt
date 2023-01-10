package com.kunal.movie.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kunal.movie.data.models.MovieCreditsDataResponse
import com.kunal.movie.databinding.ItemCastBinding
import com.kunal.movie.utils.loadImage

class CastListAdapter(
    castList: List<MovieCreditsDataResponse.Cast>,
) : RecyclerView.Adapter<CastListAdapter.CastItemViewHolder>() {


    private var castList: MutableList<MovieCreditsDataResponse.Cast>? = null

    init {
        this.castList = castList.toMutableList()
    }

    class CastItemViewHolder(
        private val binding: ItemCastBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun initCastItem(cast: MovieCreditsDataResponse.Cast?) {
            cast?.let {
                binding.apply {
                    profileImage.context.loadImage(profileImage, cast.profilePath)
                    name.text = cast.name
                    role.text = "as ${cast.character}"
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastItemViewHolder {
        val binding =
            ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastItemViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.initCastItem(castList?.get(position))
    }

    override fun getItemCount() = castList?.size ?: 0

    fun updateList(newList: List<MovieCreditsDataResponse.Cast>) {
        castList?.let { castList ->
            castList.clear()
            castList.addAll(newList)
            notifyDataSetChanged()
        }
    }
}