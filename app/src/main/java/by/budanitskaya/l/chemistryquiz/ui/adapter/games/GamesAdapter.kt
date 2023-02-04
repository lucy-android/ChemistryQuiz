package by.budanitskaya.l.chemistryquiz.ui.adapter.games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.budanitskaya.l.chemistryquiz.databinding.GameItemBinding
import by.budanitskaya.l.chemistryquiz.ui.model.games.GameItem

class GamesAdapter : ListAdapter<GameItem, GamesAdapter.ViewHolder>(DifferenceCallback) {
    inner class ViewHolder(private val binding: GameItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gameItem: GameItem) {
            binding.topicName.text = gameItem.name
            binding.imageViewIcon.setImageResource(gameItem.imageRes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            GameItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}


object DifferenceCallback : DiffUtil.ItemCallback<GameItem>() {

    override fun areItemsTheSame(
        oldItem: GameItem,
        newItem: GameItem
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: GameItem,
        newItem: GameItem
    ): Boolean {
        return oldItem == newItem
    }
}