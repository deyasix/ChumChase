package ua.nure.chumchase.feature.settings

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ua.nure.chumchase.feature.settings.presentation.SettingsViewModel

val settingsModule = module {
    viewModelOf(::SettingsViewModel)
}