package by.budanitskaya.l.chemistryquiz.ui.fragment.home

import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import by.budanitskaya.l.chemistryquiz.ChemistryApp
import by.budanitskaya.l.chemistryquiz.R
import by.budanitskaya.l.chemistryquiz.databinding.FragmentHomeBinding
import by.budanitskaya.l.chemistryquiz.ui.adapter.games.GamesAdapter
import by.budanitskaya.l.chemistryquiz.ui.model.games.GameItem
import by.budanitskaya.l.chemistryquiz.viewbindingdelegate.viewBinding


class HomeFragment : Fragment(R.layout.fragment_home) {

    companion object {
        const val RECYCLER_SPAN_COUNT = 2
        const val DEFAULT_RIGHT = 1080
    }

    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)

    private val adapter: GamesAdapter by lazy {
        GamesAdapter()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as ChemistryApp).appComponent.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }


    private fun setupRecyclerView() {
        binding.recyclerViewGames.adapter = adapter
        val layoutManager = GridLayoutManager(requireContext(), RECYCLER_SPAN_COUNT)
        layoutManager.spanSizeLookup = IntermittentSpan()
        binding.recyclerViewGames.layoutManager = layoutManager
        val bounds = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity?.windowManager?.currentWindowMetrics?.bounds
        } else {
            val view = requireActivity().window.decorView
            if (view.rootWindowInsets == null) Rect(0, 0, DEFAULT_RIGHT, 0)
            else {
                val insets =
                    WindowInsetsCompat.toWindowInsetsCompat(view.rootWindowInsets, view)
                        .getInsets(WindowInsetsCompat.Type.systemBars())
                Rect(insets.left, insets.top, insets.right, insets.bottom)
            }
        } ?: return
        val left = bounds.left
        val right = bounds.right

        binding.recyclerViewGames.addItemDecoration(SpacesItemDecoration((right - left) / 4))
        adapter.submitList(
            listOf(
                GameItem("FirstGame", R.drawable.chemical_bonds),
                GameItem("SecondGame", R.drawable.biochemistry),
                GameItem("Third Game", R.drawable.biochemistry)
            )
        )
    }
}