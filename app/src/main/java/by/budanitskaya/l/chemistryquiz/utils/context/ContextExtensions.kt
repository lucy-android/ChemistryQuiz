package by.budanitskaya.l.chemistryquiz.utils.context

import android.app.Activity
import android.content.Context
import android.content.Intent

object ContextExtensions {

    inline fun <reified T : Activity> Context.start(configIntent: Intent.() -> Unit = {}) {
        startActivity(Intent(this, T::class.java).apply(configIntent))
    }

}