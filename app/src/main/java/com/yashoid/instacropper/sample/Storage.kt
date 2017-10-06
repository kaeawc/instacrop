package com.yashoid.instacropper.sample

open class Storage(val prefs: Prefs) {

    companion object {
        private const val X1 = "x1"
        private const val Y1 = "y1"
        private const val X2 = "x2"
        private const val Y2 = "y2"

        private const val URL = "url"
        private const val WIDTH = "width"
        private const val HEIGhT = "height"
    }

    open var x1: Float
        get() = prefs.getFloat(X1, 0f)
        set(value) = prefs.set(X1, value)
    open var y1: Float
        get() = prefs.getFloat(Y1, 0f)
        set(value) = prefs.set(Y1, value)

    open var x2: Float
        get() = prefs.getFloat(X2, 1f)
        set(value) = prefs.set(X2, value)
    open var y2: Float
        get() = prefs.getFloat(Y2, 1f)
        set(value) = prefs.set(Y2, value)

    open var url: String
        get() = prefs.get(URL, "http://hinge-res.cloudinary.com/image/upload/v1499743357/vddhqgeq8usdwxzqi5sv.jpg")
        set(value) = prefs.set(URL, value)
    open var width: Int
        get() = prefs.getInt(WIDTH, 640)
        set(value) = prefs.set(WIDTH, value)
    open var height: Int
        get() = prefs.getInt(HEIGhT, 640)
        set(value) = prefs.set(HEIGhT, value)

    open fun getPhoto(): Photo {
        return Photo(
                url = url,
                width = width,
                height = height,
                x1 = x1,
                y1 = y1,
                x2 = x2,
                y2 = y2
        )
    }
}
