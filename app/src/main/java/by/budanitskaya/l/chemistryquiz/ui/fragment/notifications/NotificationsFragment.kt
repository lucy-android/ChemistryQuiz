package by.budanitskaya.l.chemistryquiz.ui.fragment.notifications

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.budanitskaya.l.chemistryquiz.ChemistryApp
import by.budanitskaya.l.chemistryquiz.LoginActivity
import by.budanitskaya.l.chemistryquiz.MainActivity
import by.budanitskaya.l.chemistryquiz.R
import by.budanitskaya.l.chemistryquiz.databinding.FragmentNotificationsBinding
import by.budanitskaya.l.chemistryquiz.utils.context.ContextExtensions.showToast
import by.budanitskaya.l.chemistryquiz.utils.context.ContextExtensions.start
import by.budanitskaya.l.chemistryquiz.utils.view.ViewExtensions.click
import by.budanitskaya.l.chemistryquiz.viewbindingdelegate.viewBinding


class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    private val binding by viewBinding(FragmentNotificationsBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as ChemistryApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLogout click {
            (activity as MainActivity).signOut()
            requireContext()
                .apply {
                    start<LoginActivity> { showToast(getString(R.string.logged_out)) }
                }
        }
    }
}