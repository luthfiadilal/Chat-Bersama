package com.example.chatfirebase

import android.content.Context
import android.content.SharedPreferences
import android.media.metrics.LogSessionId
import android.util.Log

class UserPreference (context: Context){
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "user_preference"
        private const val KEY_UID = "user_uid"
    }

    fun saveUser(user: User) {
        val editor = sharedPreferences.edit()
        editor.putString(KEY_UID, user.uid)
        editor.apply()
        Log.d("UserPreference", "User saved: ${user.uid}")
    }

    fun getUser(): User? {
        val uid = sharedPreferences.getString(KEY_UID, null)
        return if (uid != null) {
            Log.d("UserPreference", "User retrieved: $uid")
            User(uid)
        } else {
            Log.d("UserPreference", "User not found")
            null
        }
    }

    fun clearUser() {
        val editor = sharedPreferences.edit()
        editor.remove(KEY_UID)
        editor.apply()
        Log.d("UserPreference", "User cleared")
    }
}