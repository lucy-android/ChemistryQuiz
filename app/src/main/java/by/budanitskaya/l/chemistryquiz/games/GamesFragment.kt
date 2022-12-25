package by.budanitskaya.l.chemistryquiz.games

import android.content.Context
import androidx.fragment.app.Fragment
import by.budanitskaya.l.chemistryquiz.ChemistryApp
import by.budanitskaya.l.chemistryquiz.R

class GamesFragment : Fragment(R.layout.fragment_games) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as ChemistryApp).appComponent.inject(this)
    }
}