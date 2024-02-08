package ua.nure.chumchase.core.utils

import okhttp3.ResponseBody
import org.json.JSONObject

fun ResponseBody.getErrorMessage(): String {
    return JSONObject(this.string()).get(KEY_MESSAGE).toString()
}

private const val KEY_MESSAGE = "message"