package com.yashoid.instacropper.sample

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.yashoid.instacropper.InstaCropperActivity
import com.yashoid.instacropper.InstaCropperView
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity(), MainPresenter.View {

    var presenter: MainPresenter? = null
    lateinit var storage: Storage

    private val file: File
        get() = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "cache.jpg")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        storage = Storage(Prefs(baseContext.getSharedPreferences("app", Context.MODE_PRIVATE)))
        if (presenter == null) {
            presenter = MainPresenter(storage)
            presenter?.setView(this)
            presenter?.requestPhoto()
        }
    }

    override fun onPause() {
        super.onPause()
        presenter?.destroy()
    }

    override fun showPhoto(photo: Photo) {
        crop_view.setImageUri(Uri.parse(photo.url))
    }

    fun pickPhoto(v: View) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            1 -> {
                if (resultCode == RESULT_OK) {
                    val intent = InstaCropperActivity.getIntent(this, data.data, Uri.fromFile(File(externalCacheDir, "test.jpg")), 720, 50)
                    startActivityForResult(intent, 2)
                }
                return
            }
            2 -> {
                if (resultCode == RESULT_OK) {
                    crop_view.setImageUri(data.data)
                }
                return
            }
        }
    }

    fun rotate(v: View) {
        crop_view.rotation = crop_view.rotation + 90
    }

    fun crop(v: View) {
        crop_view.crop(
                View.MeasureSpec.makeMeasureSpec(720, View.MeasureSpec.AT_MOST),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), InstaCropperView.BitmapCallback { bitmap ->

            if (bitmap == null) {
                Toast.makeText(this@MainActivity, "Returned bitmap is null.", Toast.LENGTH_SHORT).show()
                return@BitmapCallback
            }

            val file = file

            try {
                val os = FileOutputStream(file)

                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, os)

                os.flush()
                os.close()

                crop_view.setImageUri(Uri.fromFile(file))

                Timber.i("Image updated.")
            } catch (e: IOException) {
                Timber.e("Failed to compress bitmap.", e)
            }
        })
    }

    fun createFolder() {

    }

    fun writeBitmapToDisk(bitmap: Bitmap) {
        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "cache.jpg")
    }

    fun deleteCachedImage() {

    }
}
