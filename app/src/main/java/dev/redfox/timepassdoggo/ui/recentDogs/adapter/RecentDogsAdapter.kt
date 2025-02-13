package dev.redfox.timepassdoggo.ui.recentDogs.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.redfox.timepassdoggo.databinding.ItemRecentDogsBinding

class RecentDogsAdapter :
    ListAdapter<Bitmap, RecentDogsAdapter.RecentDogsViewHolder>(itemCallback) {

    inner class RecentDogsViewHolder(
        private val binding: ItemRecentDogsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bitmap: Bitmap) {
            binding.ivDogsRecent.setImageBitmap(bitmap)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentDogsViewHolder =
        RecentDogsViewHolder(
            ItemRecentDogsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: RecentDogsViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val itemCallback = object : ItemCallback<Bitmap>() {
            override fun areItemsTheSame(oldItem: Bitmap, newItem: Bitmap): Boolean =
                oldItem.sameAs(newItem)

            override fun areContentsTheSame(oldItem: Bitmap, newItem: Bitmap): Boolean =
                oldItem.sameAs(newItem)
        }
    }
}