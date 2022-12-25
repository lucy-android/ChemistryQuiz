package by.budanitskaya.l.chemistryquiz.home

import android.content.Context
import androidx.fragment.app.Fragment
import by.budanitskaya.l.chemistryquiz.ChemistryApp
import by.budanitskaya.l.chemistryquiz.R

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as ChemistryApp).appComponent.inject(this)
    }
}