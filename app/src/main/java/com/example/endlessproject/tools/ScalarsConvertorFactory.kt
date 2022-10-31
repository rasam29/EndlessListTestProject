package com.example.endlessproject.tools


import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


fun getScalarConverter() = customConverter
val customConverter by lazy {
    ScalarsConvertorFactory()
}


class ScalarsConvertorFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return if (type.toString() == "java.util.List<java.lang.Integer>") {
            ListResponseConverter()
        } else if (type.toString() == "class java.lang.String") {
            StringResponseConvertor()
        } else
            super.responseBodyConverter(type, annotations, retrofit)
    }

}


class ListResponseConverter : Converter<ResponseBody, List<Int>> {

    override fun convert(value: ResponseBody): List<Int> {
        val numbers = value.string().split(",").map {
            it.trim().toInt()
        }.toMutableList()
        return numbers
    }

}

class StringResponseConvertor : Converter<ResponseBody, String> {
    override fun convert(value: ResponseBody): String {
        return value.string()
    }

}