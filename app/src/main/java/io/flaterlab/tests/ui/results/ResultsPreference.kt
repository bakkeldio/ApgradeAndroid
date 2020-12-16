package io.flaterlab.tests.ui.results

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.flaterlab.tests.data.model.Attempt
import java.lang.reflect.Type


class ResultsPreference(context: Context){
    private val prefsNode = "prefs"
    private val sharedPreferences : SharedPreferences = context.getSharedPreferences(prefsNode, Context.MODE_PRIVATE)

    fun changeList(username: String, list: List<UserScore>){
        sharedPreferences.edit().putString(username, Gson().toJson(list)).apply()
    }

    fun getList(username: String) : MutableList<UserScore> ?{
        val json = sharedPreferences.getString(username, "")
        val listType: Type = object : TypeToken<MutableList<UserScore>>() {}.type

        if (json != "") {
            return Gson().fromJson(json, listType)
        }
        return null
    }

    fun getUser() : String?{
        return sharedPreferences.getString("userEmail",null)
    }

}