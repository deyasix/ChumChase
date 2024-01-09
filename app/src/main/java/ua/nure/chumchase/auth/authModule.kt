package ua.nure.chumchase.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.nure.chumchase.auth.data.*
import ua.nure.chumchase.auth.presentation.*
import ua.nure.chumchase.auth.domain.*

val authModule = module {
    single { androidContext().dataStore }
    singleOf(::UserDataSourceFakeImpl) bind UserDataSource::class
    singleOf(::LoginUseCase)
    singleOf(::RegisterUseCase)
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
}

private const val DATASTORE_KEY = "DATASTORE_KEY"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATASTORE_KEY)