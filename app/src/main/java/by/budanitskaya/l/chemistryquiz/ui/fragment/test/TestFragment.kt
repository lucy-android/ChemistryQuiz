package by.budanitskaya.l.chemistryquiz.ui.fragment.test

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.budanitskaya.l.chemistryquiz.MainActivity
import by.budanitskaya.l.chemistryquiz.R
import by.budanitskaya.l.chemistryquiz.Screen
import by.budanitskaya.l.chemistryquiz.databinding.FragmentTestBinding
import by.budanitskaya.l.chemistryquiz.ui.adapter.test.TestAdapter
import by.budanitskaya.l.chemistryquiz.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator

class TestFragment : Fragment(R.layout.fragment_test) {

    private val binding by viewBinding(FragmentTestBinding::bind)

    companion object {
        fun newInstance(): TestFragment {
            return TestFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TestAdapter(listOf(1, 2, 3), this)
        with(binding) {
            pager.adapter = adapter
            TabLayoutMediator(tabLayout, pager) { _, _ -> }.attach()
            pager.isUserInputEnabled = false
        }
    }


    override fun onStart() {
        super.onStart()
        binding.clockView.lambda = {
            (activity as MainActivity).navigator.navigateTo(Screen.TEST_RESULT_SCREEN)
        }
    }
}