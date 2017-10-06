package com.yashoid.instacropper.sample

/**
 * Created by jason on 10/6/17.
 */

import android.net.Uri
import android.os.ParcelFileDescriptor
import android.support.v4.content.FileProvider
import android.text.TextUtils
import android.webkit.MimeTypeMap

import com.bumptech.glide.load.ImageHeaderParser
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser
import timber.log.Timber

import java.io.FileInputStream

class ImageTypeFileProvider : FileProvider() {

    private val mImageHeaderParser = DefaultImageHeaderParser()

    override fun getType(uri: Uri): String? {

        var type = super.getType(uri)
        if (!TextUtils.equals(type, "application/octet-stream")) {
            return type
        }

        try {
            val parcel = openFile(uri, "r") ?: return type
            parcel.use {
                val fileInputStream = FileInputStream(it.fileDescriptor)
                fileInputStream.use {
                    val imageType = mImageHeaderParser.getType(it)
                    type = getTypeFromImageType(imageType, type)
                }
            }
        } catch (e: Exception) {
            Timber.e(e, "Failed to read file")
        }

        return type
    }

    private fun getTypeFromImageType(
            imageType: ImageHeaderParser.ImageType,
            defaultType: String?): String? {

        val extension: String = when (imageType) {
            ImageHeaderParser.ImageType.GIF -> "gif"
            ImageHeaderParser.ImageType.JPEG -> "jpg"
            ImageHeaderParser.ImageType.PNG_A, ImageHeaderParser.ImageType.PNG -> "png"
            ImageHeaderParser.ImageType.WEBP_A, ImageHeaderParser.ImageType.WEBP -> "webp"
            else -> return defaultType
        }
        // See FileProvider.getType(Uri)
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
    }
}
