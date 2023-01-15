package by.budanitskaya.l.chemistryquiz.viewbindingdelegate

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import java.lang.ref.Reference
import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

class FragmentViewBindingProperty<in F : Fragment, out T : ViewBinding>(
    private val viewNeedInitialization: Boolean,
    viewBinder: (F) -> T,
    onViewDestroyed: (T) -> Unit,
) : LifecycleViewBindingProperty<F, T>(viewBinder, onViewDestroyed) {

    private var fragmentLifecycleCallbacks: FragmentManager.FragmentLifecycleCallbacks? = null
    private var fragmentManager: Reference<FragmentManager>? = null

    override fun getValue(thisRef: F, property: KProperty<*>): T {
        val viewBinding = super.getValue(thisRef, property)
        registerFragmentLifecycleCallbacksIfNeeded(thisRef)
        return viewBinding
    }

    private fun registerFragmentLifecycleCallbacksIfNeeded(fragment: Fragment) {
        if (fragmentLifecycleCallbacks != null) return

        val fragmentManager = fragment.parentFragmentManager.also { fm ->
            this.fragmentManager = WeakReference(fm)
        }
        fragmentLifecycleCallbacks = ClearOnDestroy(fragment).also { callbacks ->
            fragmentManager.registerFragmentLifecycleCallbacks(callbacks, false)
        }
    }

    override fun isViewInitialized(thisRef: F): Boolean {
        if (!viewNeedInitialization) return true

        return if (thisRef !is DialogFragment) {
            thisRef.view != null
        } else {
            super.isViewInitialized(thisRef)
        }
    }

    override fun clear() {
        super.clear()
        fragmentManager?.get()?.let { fragmentManager ->
            fragmentLifecycleCallbacks?.let(fragmentManager::unregisterFragmentLifecycleCallbacks)
        }

        fragmentManager = null
        fragmentLifecycleCallbacks = null
    }

    override fun getLifecycleOwner(thisRef: F): LifecycleOwner {
        try {
            return thisRef.viewLifecycleOwner
        } catch (ignored: IllegalStateException) {
            error("Fragment doesn't have view associated with it or the view has been destroyed")
        }
    }

    private inner class ClearOnDestroy(
        fragment: Fragment
    ) : FragmentManager.FragmentLifecycleCallbacks() {

        private var fragment: Reference<Fragment> = WeakReference(fragment)

        override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
            if (fragment.get() === f) {
                postClear()
            }
        }
    }
}