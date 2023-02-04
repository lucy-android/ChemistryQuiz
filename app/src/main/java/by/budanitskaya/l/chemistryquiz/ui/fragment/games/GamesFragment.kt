package by.budanitskaya.l.chemistryquiz.ui.fragment.games

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.budanitskaya.l.chemistryquiz.ChemistryApp
import by.budanitskaya.l.chemistryquiz.R
import by.budanitskaya.l.chemistryquiz.databinding.FragmentGamesBinding
import by.budanitskaya.l.chemistryquiz.di.games.GamesComponent
import by.budanitskaya.l.chemistryquiz.viewbindingdelegate.viewBinding
import javax.inject.Inject

class GamesFragment : Fragment(R.layout.fragment_games) {

    private val binding: FragmentGamesBinding by viewBinding(FragmentGamesBinding::bind)

    @Inject
    lateinit var gamesBuilder: GamesComponent.GamesBuilder

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as ChemistryApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewGames.text =
            gamesBuilder.addContext(requireContext())
                .gamesComponent()
                .getPrinter()
                .getText()
    }
}