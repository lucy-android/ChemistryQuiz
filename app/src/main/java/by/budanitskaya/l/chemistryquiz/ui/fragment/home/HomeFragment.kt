package by.budanitskaya.l.chemistryquiz.ui.fragment.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.budanitskaya.l.chemistryquiz.ChemistryApp
import by.budanitskaya.l.chemistryquiz.R
import by.budanitskaya.l.chemistryquiz.databinding.FragmentHomeBinding
import by.budanitskaya.l.chemistryquiz.ui.adapter.games.GamesAdapter
import by.budanitskaya.l.chemistryquiz.ui.model.topic.Topic
import by.budanitskaya.l.chemistryquiz.ui.model.topic.TopicList
import by.budanitskaya.l.chemistryquiz.utils.activity.ActivityExtensions.getBounds
import by.budanitskaya.l.chemistryquiz.utils.file.FileUtils.getFromAssets
import by.budanitskaya.l.chemistryquiz.utils.json.JSONUtils.deSerialize
import by.budanitskaya.l.chemistryquiz.viewbindingdelegate.viewBinding


class HomeFragment : Fragment(R.layout.fragment_home) {

    companion object {
        const val RECYCLER_SPAN_COUNT = 2
        const val DEFAULT_RIGHT = 1080
    }

    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)

    private val adapter: GamesAdapter by lazy {
        GamesAdapter(viewLifecycleOwner.lifecycleScope)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as ChemistryApp).appComponent.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val string = getFromAssets(requireContext(), "quiz_list.json")

        val list = string.deSerialize<TopicList>() ?: return

        setupRecyclerView(list.topics)
    }


    private fun setupRecyclerView(list: List<Topic>) {
        binding.recyclerViewGames.adapter = adapter
        val layoutManager = GridLayoutManager(requireContext(), RECYCLER_SPAN_COUNT).apply {
            spanSizeLookup = IntermittentSpan()
        }

        binding.recyclerViewGames.layoutManager = layoutManager
        val bounds = activity?.getBounds() ?: return

        binding.recyclerViewGames.addItemDecoration(
            SpacesItemDecoration((bounds.right - bounds.left) / 4))
        adapter.submitList(list)
    }
}