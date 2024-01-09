package ua.nure.chumchase.auth.presentation

import android.util.Patterns
import ua.nure.chumchase.R

interface ErrorMessage {
    val message: Int
}

enum class BaseFieldErrors : ErrorMessage {
    EMPTY {
        override val message = R.string.field_should_be_not_empty
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