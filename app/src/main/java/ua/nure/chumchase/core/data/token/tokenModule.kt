package ua.nure.chumchase.core.data.token

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val tokenModule = module {
    single { androidContext().dataStore }
    singleOf(::TokenManagerImpl) bind TokenManager::class
}

private const val DATASTORE_KEY = "DATASTORE_KEY"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATASTORE_KEY)
