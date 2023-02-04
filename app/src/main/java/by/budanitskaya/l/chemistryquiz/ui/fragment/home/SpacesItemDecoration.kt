package by.budanitskaya.l.chemistryquiz.ui.fragment.home

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        when (parent.getChildLayoutPosition(view) % 3) {
            0 -> {
                outRect.left = space
                outRect.right = space
            }
        }
    }
}
