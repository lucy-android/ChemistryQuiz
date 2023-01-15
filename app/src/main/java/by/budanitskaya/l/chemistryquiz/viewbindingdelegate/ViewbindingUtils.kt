package by.budanitskaya.l.chemistryquiz.viewbindingdelegate

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty

inline fun <reified T : ViewBinding> AppCompatActivity.viewBinding(noinline initializer: (LayoutInflater) -> T) =
    ViewBindingPropertyDelegate(this, initializer)


@JvmName("viewBindingFragment")
inline fun <F : Fragment, T : ViewBinding> viewBinding(
    crossinline vbFactory: (View) -> T,
    crossinline viewProvider: (F) -> View = Fragment::requireView,
): ReadOnlyProperty<F, T> {
    return FragmentViewBindingProperty(
        true,
        { fragment: F -> vbFactory(viewProvider(fragment)) },
        {})
}


const val TAG = "ViewBindingProperty"
const val ERROR_ACCESS_BEFORE_VIEW_READY =
    "Host view isn't ready to create a ViewBinding instance"
const val ERROR_ACCESS_AFTER_DESTROY =
    "Access to viewBinding after Lifecycle is destroyed or hasn't created yet. " +
            "The instance of viewBinding will be not cached."