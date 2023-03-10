package by.budanitskaya.l.chemistryquiz.ui.fragment.test

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.budanitskaya.l.chemistryquiz.R
import by.budanitskaya.l.chemistryquiz.databinding.FragmentQuestionBinding
import by.budanitskaya.l.chemistryquiz.viewbindingdelegate.viewBinding

private val FIRST_ARG_KEY = "key"

class QuestionFragment : Fragment(R.layout.fragment_question) {

    private val binding by viewBinding(FragmentQuestionBinding::bind)

    companion object {
        @JvmStatic
        fun newInstance(intObject: Int) = QuestionFragment().apply {
            arguments = Bundle().apply {
                putInt(FIRST_ARG_KEY, intObject)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(FIRST_ARG_KEY)?.let { number ->
            binding.textViewNumber.text = number.toString()
        }
    }
}