package com.yashoid.instacropper.sample

import java.lang.ref.WeakReference

open class MainPresenter(storage: Storage) : MainInteractor.MainViewModel {

    open val interactor = MainInteractor(storage)
    var weakView: WeakReference<View>? = null

    open fun setView(view: View) {
        weakView = WeakReference(view)
        interactor.setViewModel(this)
    }

    open fun requestPhoto() {
        interactor.requestPhoto()
    }

    open fun destroy() {
        interactor.destroy()
        weakView?.clear()
    }

    override fun onPhotoLoaded(photo: Photo) {
        val view = weakView?.get() ?: return
        view.showPhoto(photo)
    }

    interface View {
        fun showPhoto(photo: Photo)
    }
}
