package com.yashoid.instacropper.sample

/**
 * Created by jason on 10/6/17.
 */

import android.content.SharedPreferences

open class Prefs(open val prefs: SharedPreferences) {

    inline fun SharedPreferences.edit(func: SharedPreferences.Editor.() -> Unit) {
        val editor = edit()
        editor.func()
        editor.apply()
    }

    open fun SharedPreferences.Editor.setString(key: String, value: String?) {
        putString(key, value)
    }

    open fun SharedPreferences.Editor.setList(key: String, value: Set<String>?) {
        putStringSet(key, value)
    }

    open fun SharedPreferences.Editor.setBoolean(key: String, value: Boolean) {
        putBoolean(key, value)
    }

    open fun SharedPreferences.Editor.setInt(key: String, value: Int) {
        putInt(key, value)
    }

    open fun SharedPreferences.Editor.setFloat(key: String, value: Float) {
        putFloat(key, value)
    }

    open fun SharedPreferences.Editor.setFloat(key: String, value: Float?) {
        putFloat(key, value ?: Float.MIN_VALUE)
    }

    open fun SharedPreferences.Editor.setInt(key: String, value: Int?) {
        putInt(key, value ?: Int.MIN_VALUE)
    }

    open fun SharedPreferences.Editor.unset(key: String) {
        remove(key)
    }

    open fun SharedPreferences.Editor.unsetKeys(keys: List<String>) {
        keys.forEach {
            remove(it)
        }
    }

    open fun get(key: String): String? {
        return prefs.getString(key, null)
    }

    open fun getList(key: String, default: Set<String>?): MutableSet<String>? {
        return prefs.getStringSet(key, default)
    }

    open fun get(key: String, default: String): String {
        return prefs.getString(key, default)
    }

    open fun getBool(key: String, default: Boolean): Boolean {
        return prefs.getBoolean(key, default)
    }

    open fun getInt(key: String, default: Int): Int {
        return prefs.getInt(key, default)
    }

    open fun getFloat(key: String, default: Float): Float {
        return prefs.getFloat(key, default)
    }

    open fun getFloat(key: String, default: Float? = null): Float {
        return prefs.getFloat(key, default ?: Float.MIN_VALUE)
    }

    open fun getInt(key: String, default: Int? = null): Int {
        return prefs.getInt(key, default ?: Int.MIN_VALUE)
    }

    open fun set(key: String, value: String?) {
        prefs.edit {
            setString(key, value)
        }
    }

    open fun set(key: String, value: Set<String>?) {
        prefs.edit {
            setList(key, value)
        }
    }

    open fun set(key: String, value: Boolean) {
        prefs.edit {
            setBoolean(key, value)
        }
    }

    open fun set(key: String, value: Int) {
        prefs.edit {
            setInt(key, value)
        }
    }

    open fun set(key: String, value: Float) {
        prefs.edit {
            setFloat(key, value)
        }
    }

    open fun set(key: String, value: Int?) {
        prefs.edit {
            setInt(key, value)
        }
    }

    open fun set(key: String, value: Float?) {
        prefs.edit {
            setFloat(key, value)
        }
    }

    open fun unset(key: String) {
        prefs.edit {
            unset(key)
        }
    }

    open fun unset(keys: List<String>) {
        prefs.edit {
            unsetKeys(keys)
        }
    }

    open fun clear() {
        prefs.edit {
            clear()
        }
    }
}
