package com.gameda.prueba_pokedex.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun <T> Gson.parseList(json: String?, clazz: Class<T>): List<T>? {
    val typeOfT = TypeToken.getParameterized(List::class.java, clazz).type
    return fromJson(json, typeOfT)
}