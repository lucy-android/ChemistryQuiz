package by.budanitskaya.l.chemistryquiz.ui.fragment.home

import androidx.recyclerview.widget.GridLayoutManager

class IntermittentSpan : GridLayoutManager.SpanSizeLookup() {
    override fun getSpanSize(position: Int): Int {
        return when (position % 3) {
            0 -> 2
            1, 2 -> 1
            else -> throw Exception()
        }
    }
}
