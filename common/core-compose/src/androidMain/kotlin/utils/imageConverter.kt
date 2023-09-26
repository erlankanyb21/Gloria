package utils

import android.content.Context
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.ByteArrayOutputStream

fun compressBitmap(context: Context, uri: Uri, format: CompressFormat, quality: Int): ByteArray? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        if (inputStream != null) {
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(format, quality, byteArrayOutputStream)
            byteArrayOutputStream.toByteArray()
        } else {
            null
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}