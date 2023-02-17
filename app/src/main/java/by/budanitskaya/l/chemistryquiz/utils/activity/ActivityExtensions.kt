package by.budanitskaya.l.chemistryquiz.utils.activity

import android.graphics.Rect
import android.os.Build
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentActivity
import by.budanitskaya.l.chemistryquiz.ui.fragment.home.HomeFragment

object ActivityExtensions {

    fun FragmentActivity.getBounds(): Rect? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            this.windowManager?.currentWindowMetrics?.bounds
        } else {
            val view = this.window?.decorView
            if (view?.rootWindowInsets == null) Rect(0, 0, HomeFragment.DEFAULT_RIGHT, 0)
            else {
                val insets =
                    WindowInsetsCompat.toWindowInsetsCompat(view.rootWindowInsets, view)
                        .getInsets(WindowInsetsCompat.Type.systemBars())
                Rect(insets.left, insets.top, insets.right, insets.bottom)
            }
        }
    }
}