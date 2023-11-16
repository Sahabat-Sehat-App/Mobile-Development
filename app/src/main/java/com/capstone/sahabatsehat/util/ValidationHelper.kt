package com.capstone.sahabatsehat.util

import android.util.Patterns

class ValidationHelper {
    fun validEmail(value: String): String? {
        if(!Patterns.EMAIL_ADDRESS.matcher(value).matches())
        {
            return "Invalid Email Address"
        }
        return null
    }

    fun validPassword(value: String): String? {
        if(value.length < 8)
        {
            return "Minumum 8 Character Password"
        }else if(value.filter { it.isLetter() }.firstOrNull { it.isUpperCase() } == null){
            return "It must have at least 1 lowercase and uppercase."
        }else if(value.filter { it.isLetter() }.firstOrNull { it.isLowerCase() } == null){
            return "It must have at least 1 lowercase and uppercase."
        }
        return null
    }

    fun validNoHp(value: String): String? {
        if(value.length < 10)
        {
            return "Minumum 10 Character NoHP"
        }
        return null
    }

    fun validName(value: String): String? {
        if(value.length < 3)
        {
            return "Minumum 3 Character Name"
        }
        return null
    }

    fun validRepeatPassword(value1: String, value2: String): String?{
        if(value1 != value2){
            return "Password not match"
        }
        return null
    }
}