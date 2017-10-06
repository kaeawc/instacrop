package com.yashoid.instacropper.sample

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import com.yashoid.instacropper.InstaCropperActivity
import com.yashoid.instacropper.InstaCropperView

/**
 * Created by jason on 10/6/17.
 */
object MediaBrowser {

    const val EXTRA_OUTPUT = MediaStore.EXTRA_OUTPUT

    const val EXTRA_PREFERRED_RATIO = "preferred_ratio"
    const val EXTRA_MINIMUM_RATIO = "minimum_ratio"
    const val EXTRA_MAXIMUM_RATIO = "maximum_ratio"

    const val EXTRA_WIDTH_SPEC = "width_spec"
    const val EXTRA_HEIGHT_SPEC = "height_spec"

    const val EXTRA_OUTPUT_QUALITY = "output_quality"

    const val DEFAULT_MINIMUM_RATIO = 4f / 5f
    const val DEFAULT_MAXIMUM_RATIO = 1.91f
    const val DEFAULT_RATIO = 1f

    fun getIntent(
            context: Context,
            src: Uri,
            dst: Uri,
            maxWidth: Int,
            outputQuality: Int): Intent {

        val widthSpec = View.MeasureSpec.makeMeasureSpec(maxWidth, View.MeasureSpec.AT_MOST)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        return getIntent(
                context,
                src,
                dst,
                DEFAULT_RATIO,
                DEFAULT_MINIMUM_RATIO,
                DEFAULT_MAXIMUM_RATIO,
                widthSpec,
                heightSpec,
                outputQuality
        )
    }

    private fun getIntent(context: Context, src: Uri, dst: Uri,
                          preferredRatio: Float, minimumRatio: Float, maximumRatio: Float,
                          widthSpec: Int, heightSpec: Int, outputQuality: Int): Intent {
        val intent = Intent(context, InstaCropperActivity::class.java)

        intent.data = src

        intent.putExtra(EXTRA_OUTPUT, dst)

        intent.putExtra(EXTRA_PREFERRED_RATIO, preferredRatio)
        intent.putExtra(EXTRA_MINIMUM_RATIO, minimumRatio)
        intent.putExtra(EXTRA_MAXIMUM_RATIO, maximumRatio)

        intent.putExtra(EXTRA_WIDTH_SPEC, widthSpec)
        intent.putExtra(EXTRA_HEIGHT_SPEC, heightSpec)
        intent.putExtra(EXTRA_OUTPUT_QUALITY, outputQuality)

        return intent
    }
}