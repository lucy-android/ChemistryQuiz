package by.budanitskaya.l.chemistryquiz.ui.fragment.test

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.budanitskaya.l.chemistryquiz.R
import by.budanitskaya.l.chemistryquiz.databinding.FragmentQuestionBinding
import by.budanitskaya.l.chemistryquiz.viewbindingdelegate.viewBinding

private const val FIRST_ARG_KEY = "key"

class QuestionFragment : Fragment(R.layout.fragment_question) {

    private val binding by viewBinding(FragmentQuestionBinding::bind)

    companion object {
        @JvmStatic
        fun newInstance(questionObject: Question) = QuestionFragment().apply {
            arguments = Bundle().apply {
                putParcelable(FIRST_ARG_KEY, questionObject)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (arguments?.getParcelable(FIRST_ARG_KEY) as? Question)?.let { question ->
            binding.textViewNumber.text = question.text
            binding.radioButton1.text = question.answers[0]
            binding.radioButton2.text = question.answers[1]
            binding.radioButton3.text = question.answers[2]
            binding.radioButton4.text = question.answers[3]
        }
    }
}