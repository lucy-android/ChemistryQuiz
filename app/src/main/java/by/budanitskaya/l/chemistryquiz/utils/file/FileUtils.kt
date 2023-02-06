package by.budanitskaya.l.chemistryquiz.utils.file

import android.content.Context
import java.io.IOException
import java.io.InputStream

object FileUtils {
    fun getFromAssets(context: Context, fileName: String): String {
        val jsonString: String = try {
            val `is`: InputStream = context.assets.open(fileName)
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, java.nio.charset.StandardCharsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }
        return jsonString
    }
}