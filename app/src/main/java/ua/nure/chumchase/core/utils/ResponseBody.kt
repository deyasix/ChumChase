package ua.nure.chumchase.core.utils

import okhttp3.ResponseBody
import org.json.JSONObject
import ua.nure.chumchase.core.domain.Keys

fun ResponseBody.getErrorMessage(): String {
    return try {
        JSONObject(this.string()).get(KEY_MESSAGE).toString()
    } catch (exception: Exception) {
        Keys.INVALID_DATA
    }
}

private const val KEY_MESSAGE = "message"