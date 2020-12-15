package io.flaterlab.tests.data


import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.flaterlab.tests.data.model.Attempt
import io.flaterlab.tests.data.model.SignUpData
import io.flaterlab.tests.data.model.Test

import java.lang.reflect.Type

class UserData(var context: Context) {
    private val gson = Gson()
    private val prefsNode = "prefs"
    private val testNode = "tests"
    private val myPrefs: SharedPreferences = context.getSharedPreferences(prefsNode, Context.MODE_PRIVATE)
    private val tokenNode = "token"

    fun getToken(): String? {

        return myPrefs.getString(tokenNode, null)
    }

    var userDetail: SignUpData?
        get() = Gson().fromJson(myPrefs.getString("User", ""), SignUpData::class.java)
        set(value) = myPrefs.edit().putString("User", Gson().toJson(value)).apply()

    fun saveToken(token: String) {
        myPrefs.edit().putString(tokenNode, token).apply()
    }

    fun getAttempts(testId: Long): ArrayList<Attempt> {
        val myPrefs = context.getSharedPreferences(prefsNode, Context.MODE_PRIVATE)
        val listType: Type = object : TypeToken<ArrayList<Attempt>>() {}.type
        val listJson = myPrefs.getString(testId.toString(), "")

        if (listJson == "") {
            return arrayListOf()
        }

        return gson.fromJson(listJson, listType)
    }

    fun saveAttempts(testId: Long, list: ArrayList<Attempt>) {
        myPrefs.edit().putString(testId.toString(), gson.toJson(list)).apply()
    }

    fun replaceAttemptByIndex(testId: Long, index: Int, it: Attempt) {
        val attempt = getAttempts(testId)
        attempt[index] = it
        saveAttempts(testId, attempt)
    }

    fun removeToken() {
        myPrefs.edit().remove(tokenNode).apply()
    }

    private fun getTests(): ArrayList<Test> {
        val listType: Type = object : TypeToken<ArrayList<Test>>() {}.type
        val listJson = myPrefs.getString(testNode, "")
        if (listJson == "") {
            return arrayListOf()
        }
        return gson.fromJson(listJson, listType)
    }

    private fun saveTests(tests: ArrayList<Test>) {
        myPrefs.edit().putString(testNode, gson.toJson(tests)).apply()
    }

    fun getTest(testId: Long): Test? {
        val test = getTests()
        test.forEach {
            if (testId == it.id) {
                return it
            }
        }
        return null
    }

    fun saveTest(test: Test) {
        val tests = getTests()
        var found = false
        tests.forEachIndexed { i: Int, t: Test ->
            if (test.id == t.id) {
                tests[i] = test
                found = true
            }
        }
        if (!found) {
            tests.add(test)
        }
        saveTests(tests)
    }

    fun deleteTest(testId: Long) {
        val tests = getTests()
        tests.forEachIndexed { i: Int, t: Test ->
            if (testId == t.id) {
                tests.removeAt(i)
            }
        }
        saveTests(tests)
    }
}