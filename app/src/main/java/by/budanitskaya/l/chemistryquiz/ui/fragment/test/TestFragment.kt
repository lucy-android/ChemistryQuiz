package by.budanitskaya.l.chemistryquiz.ui.fragment.test

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import by.budanitskaya.l.chemistryquiz.MainActivity
import by.budanitskaya.l.chemistryquiz.R
import by.budanitskaya.l.chemistryquiz.Screen
import by.budanitskaya.l.chemistryquiz.databinding.FragmentTestBinding
import by.budanitskaya.l.chemistryquiz.ui.adapter.test.TestAdapter
import by.budanitskaya.l.chemistryquiz.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.parcelize.Parcelize

class TestFragment : Fragment(R.layout.fragment_test) {

    private val binding by viewBinding(FragmentTestBinding::bind)

    companion object {
        fun newInstance(): TestFragment {
            return TestFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TestAdapter(
            listOf(
                Question(
                    "Which of these indicators is useless foe detecting acids?",
                    listOf("Cresolphthalein", "Congo red", "Methyl yellow", "Bromocresol green"),
                    "Acids and bases",
                    "All phthaleins in aqueous solutions are insensible towards acids, so the correct answer is cresolphthalein."
                ),
                Question(
                    "What measurement of the pH scale is considered a neutral liquid?",
                    listOf("7", "0", "3", "14"),
                    "Acids and bases",
                    "pH values below 7 are acidic, above 7 are basic and 7 is neutral."
                ), Question(
                    "Which of the following is a way that acids and bases are used in science and technology?",
                    listOf(
                        "All of the Above",
                        "Batteries",
                        "Household cleaning products",
                        "Fertilizer"
                    ),
                    "Acids and bases",
                    "All of these products contain alkaline or acidic substances and are used in technological applications."
                )
            ), this
        )
        with(binding) {
            pager.adapter = adapter
            TabLayoutMediator(tabLayout, pager) { _, _ -> }.attach()
            //pager.isUserInputEnabled = false
        }

        binding.clockView.lambda = {
            (activity as MainActivity).navigator.navigateTo(Screen.TEST_RESULT_SCREEN)
        }
    }
}


@Parcelize
data class Question(
    val text: String,
    val answers: List<String>,
    val topic: String,
    val explanation: String
) : Parcelable




