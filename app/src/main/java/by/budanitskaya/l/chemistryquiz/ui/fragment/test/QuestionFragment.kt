package by.budanitskaya.l.chemistryquiz.ui.fragment.test

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import by.budanitskaya.l.chemistryquiz.R
import by.budanitskaya.l.chemistryquiz.databinding.FragmentQuestionBinding
import by.budanitskaya.l.chemistryquiz.dialog.CustomDialog
import by.budanitskaya.l.chemistryquiz.viewbindingdelegate.viewBinding

private const val FIRST_ARG_KEY = "key"

class QuestionFragment : Fragment(R.layout.fragment_question) {

    private val binding by viewBinding(FragmentQuestionBinding::bind)

    private val dialogs: MutableList<AlertDialog> = mutableListOf()

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

            initRadioGroup(question)
        }

    }

    private fun initRadioGroup(question: Question) {
        binding.radioGroupOne.setOnCheckedChangeListener { _, checkedId ->
            if (getRadioButtonForCorrectAnswer(question.correctAnswer) == checkedId) {
                val dialog = CustomDialog.Builder(binding.root as ViewGroup)
                    .setCancelable(false)
                    .setTitle(R.string.test_correct_answer)
                    .setMessageText(question.explanation)
                    .setDarkButtonText(R.string.test_ok)
                    .setDarkButtonClickEvent { cancelAllDialogs() }
                    .build()

                dialogs.add(dialog)
                dialog.show()
            }
        }
    }

    private fun cancelAllDialogs() {
        for (dialog in dialogs) if (dialog.isShowing) dialog.dismiss()
    }
}

fun getRadioButtonForCorrectAnswer(correctAnswer: Int): Int {
    return when (correctAnswer) {
        0 -> R.id.radioButton1
        1 -> R.id.radioButton2
        2 -> R.id.radioButton3
        3 -> R.id.radioButton4
        else -> throw NoSuchElementException()
    }
}