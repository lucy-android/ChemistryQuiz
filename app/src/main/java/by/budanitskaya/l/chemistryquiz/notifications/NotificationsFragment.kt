package by.budanitskaya.l.chemistryquiz.notifications

import android.content.Context
import androidx.fragment.app.Fragment
import by.budanitskaya.l.chemistryquiz.ChemistryApp
import by.budanitskaya.l.chemistryquiz.R

class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as ChemistryApp).appComponent.inject(this)
    }

}