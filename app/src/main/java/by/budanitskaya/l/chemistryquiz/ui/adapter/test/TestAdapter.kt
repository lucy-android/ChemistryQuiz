package by.budanitskaya.l.chemistryquiz.ui.adapter.test

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import by.budanitskaya.l.chemistryquiz.ui.fragment.test.Question
import by.budanitskaya.l.chemistryquiz.ui.fragment.test.QuestionFragment

class TestAdapter(
    private val list: List<Question>,
    fragment: Fragment
) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return QuestionFragment.newInstance(list[position])
    }
}