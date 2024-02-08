package ua.nure.chumchase.core.domain

import android.util.Patterns
import ua.nure.chumchase.R

object Keys {
    const val USER_EXISTED = "USER_EXISTED"
    const val INVALID_DATA = "INVALID_DATA"
}

interface ErrorMessage {
    val message: Int
}

enum class AuthResponseMessage(val status: String) : ErrorMessage {
    USER_EXISTED(Keys.USER_EXISTED) {
        override val message: Int = R.string.error_user_existed
    },
    INVALID_DATA(Keys.INVALID_DATA) {
        override val message: Int = R.string.invalid_login_or_password
    };

    companion object {
        fun getResponseMessage(status: String?): ErrorMessage {
            return values().find { it.status == status } ?: OperationStatusMessage.FAILURE
        }
    }
}

enum class BaseFieldErrors : ErrorMessage {
    EMPTY {
        override val message = R.string.field_should_be_not_empty
    }
}

enum class OperationStatusMessage : ErrorMessage {
    FAILURE {
        override val message = R.string.failed_operation
    },
    SUCCESS {
        override val message = R.string.success_operation
    },
    TIMEOUT {
        override val message = R.string.error_timeout
    },
    UNAUTHORIZED {
        override val message: Int = R.string.error_unauthorized
    }
}

enum class EmailFieldErrors : ErrorMessage {
    EMPTY {
        override val message = R.string.field_should_be_not_empty
    },
    INVALID {
        override val message = R.string.invalid_email_error
    };

    companion object {
        fun getMessage(email: String): EmailFieldErrors? {
            if (email.isBlank()) return EMPTY
            else {
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) return INVALID
            }
            return null
        }
    }
}

enum class PasswordFieldErrors : ErrorMessage {
    EMPTY {
        override val message = R.string.field_should_be_not_empty
    },
    SHORT {
        override val message = R.string.password_too_short
    },
    NOT_MATCH {
        override val message = R.string.password_dont_match
    };

    companion object {
        private const val MINIMUM_LENGTH = 6
        fun getMessage(password: String): PasswordFieldErrors? {
            return if (password.isBlank()) EMPTY
            else if (password.length < MINIMUM_LENGTH) SHORT
            else null
        }
    }
}