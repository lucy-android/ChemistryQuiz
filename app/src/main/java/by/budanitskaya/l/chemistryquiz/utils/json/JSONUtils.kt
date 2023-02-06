package by.budanitskaya.l.chemistryquiz.utils.json

import com.google.gson.GsonBuilder
import java.io.StringReader

object JSONUtils {

    inline fun <reified T> String.deSerialize(): T? {
        return try {
            val gson = GsonBuilder().create()
            val stringReader = StringReader(this)
            gson.fromJson(stringReader, T::class.java)
        } catch (e: Exception) {
            null
        }

    }
}