package ua.nure.chumchase.main

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val mainModule = module {
    singleOf(::MainViewModel)
}