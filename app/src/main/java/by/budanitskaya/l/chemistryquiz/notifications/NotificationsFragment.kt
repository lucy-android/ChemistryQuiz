package by.budanitskaya.l.chemistryquiz.notifications

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.budanitskaya.l.chemistryquiz.ChemistryApp
import by.budanitskaya.l.chemistryquiz.LoginActivity
import by.budanitskaya.l.chemistryquiz.MainActivity
import by.budanitskaya.l.chemistryquiz.R
import by.budanitskaya.l.chemistryquiz.databinding.FragmentNotificationsBinding
import by.budanitskaya.l.chemistryquiz.utils.context.ContextExtensions.start
import by.kirich1409.viewbindingdelegate.viewBinding

class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    private val binding by viewBinding(FragmentNotificationsBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as ChemistryApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLogout.setOnClickListener {
            (activity as MainActivity).signOut()
            requireContext().start<LoginActivity> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.logged_out),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}