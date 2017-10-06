package com.yashoid.instacropper.sample

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import okhttp3.OkHttpClient
import java.io.InputStream

@GlideModule
class GlideProvider : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        // no-op
    }

    // Disable manifest parsing to avoid adding similar modules twice.
    override fun isManifestParsingEnabled(): Boolean = false

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setMemoryCache(LruResourceCache(5 * 1024 * 1024)).setDecodeFormat(
                DecodeFormat.PREFER_ARGB_8888)
    }
    companion object {

        fun registerComponents(context: Context, okHttpClient: OkHttpClient) {
            Glide.get(context).registry.append(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(okHttpClient))
        }
    }
}
