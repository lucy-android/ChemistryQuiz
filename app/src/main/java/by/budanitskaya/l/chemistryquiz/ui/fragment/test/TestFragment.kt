package by.budanitskaya.l.chemistryquiz.ui.fragment.test

import androidx.fragment.app.Fragment
import by.budanitskaya.l.chemistryquiz.MainActivity
import by.budanitskaya.l.chemistryquiz.R
import by.budanitskaya.l.chemistryquiz.Screen
import by.budanitskaya.l.chemistryquiz.databinding.FragmentTestBinding
import by.budanitskaya.l.chemistryquiz.viewbindingdelegate.viewBinding

class TestFragment : Fragment(R.layout.fragment_test) {

    private val binding by viewBinding(FragmentTestBinding::bind)

    companion object {
        fun newInstance(): TestFragment {
            return TestFragment()
        }
    }

    override fun onStart() {
        super.onStart()
        binding.clockView.lambda = {
            (activity as MainActivity).navigator.navigateTo(Screen.TEST_RESULT_SCREEN)
        }
    }

}