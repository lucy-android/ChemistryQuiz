package by.budanitskaya.l.chemistryquiz.viewbindingdelegate

import android.os.Looper
import androidx.annotation.MainThread

object ViewBindingPropertyDelegateObject {

    @set:MainThread
    var strictMode = true
        set(value) {
            check(Looper.getMainLooper() == Looper.myLooper())
            field = value
        }
}