package com.b.nsofronovic.bitcoinwallet.model

import androidx.room.TypeConverter
import java.lang.IllegalArgumentException

class AuthTypeConverter {

    companion object {

        @TypeConverter
        @JvmStatic
        fun toAuthenticationType(type: Int): AuthenticationType {
            if (type == AuthenticationType.PIN.value) {
                return AuthenticationType.PIN
            } else if (type == AuthenticationType.FINGERPRINT.value) {
                return AuthenticationType.FINGERPRINT
            } else {
                throw IllegalArgumentException("Could not recognize auth type")
            }
        }

        @TypeConverter
        @JvmStatic
        fun ofAuthenticationType(type: AuthenticationType): Int = when (type) {
            AuthenticationType.PIN -> AuthenticationType.PIN.value
            AuthenticationType.FINGERPRINT -> AuthenticationType.FINGERPRINT.value
        }
    }
}