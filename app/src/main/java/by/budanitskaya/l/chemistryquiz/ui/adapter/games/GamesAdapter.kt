package by.budanitskaya.l.chemistryquiz.ui.adapter.games

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.budanitskaya.l.chemistryquiz.databinding.GameItemBinding
import by.budanitskaya.l.chemistryquiz.ui.fragment.home.Topic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GamesAdapter(private val coroutineScope: CoroutineScope) :
    ListAdapter<Topic, GamesAdapter.ViewHolder>(DifferenceCallback) {
    inner class ViewHolder(private val binding: GameItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gameItem: Topic) {
            binding.topicName.text = gameItem.name
            binding.progressBar.visibility = View.VISIBLE
            binding.topicName.visibility = View.INVISIBLE
            coroutineScope.launch {
                val d = readFromFile(binding.root, gameItem)
                binding.imageViewIcon.setImageDrawable(d)
                binding.progressBar.visibility = View.GONE
                binding.topicName.visibility = View.VISIBLE
            }

        }
    }

    private suspend fun readFromFile(root: View, gameItem: Topic): Drawable? {
        delay(3000)
        return Drawable.createFromStream(
            root.resources.assets.open(gameItem.drawable),
            null
        )

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


object DifferenceCallback : DiffUtil.ItemCallback<Topic>() {

    override fun areItemsTheSame(
        oldItem: Topic,
        newItem: Topic
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: Topic,
        newItem: Topic
    ): Boolean {
        return oldItem == newItem
    }
}