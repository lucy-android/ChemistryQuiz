package by.budanitskaya.l.chemistryquiz.utils.view

import android.view.View

object ViewExtensions {
    infix fun View.click(click: () -> Unit) {
        setSafeOnClickListener { click() }
    }

    private fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
        val safeClickListener = SafeClickListener {
            onSafeClick(it)
        }
        setOnClickListener(safeClickListener)
    }

    fun View.toVisible() {
        visibility = View.VISIBLE
    }
}


class SafeClickListener(private val onSafeCLick: (View) -> Unit) : View.OnClickListener {
    private var lastClickTime = 0L
    private val interval = 700L
    override fun onClick(v: View) {
        if (System.currentTimeMillis() - lastClickTime < interval)
            return
        lastClickTime = System.currentTimeMillis()
        onSafeCLick(v)
    }
}