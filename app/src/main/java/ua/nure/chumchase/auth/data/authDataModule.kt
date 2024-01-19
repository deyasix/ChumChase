package ua.nure.chumchase.auth.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.nure.chumchase.auth.domain.UserDataSource

val authDataModule = module {
    single { androidContext().dataStore }
    singleOf(::UserDataSourceFakeImpl) bind UserDataSource::class
}

private const val DATASTORE_KEY = "DATASTORE_KEY"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATASTORE_KEY)