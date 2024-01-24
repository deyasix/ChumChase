package ua.nure.chumchase.feature.friends

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ua.nure.chumchase.feature.friends.presentation.FriendsViewModel

val friendsModule = module {
    viewModelOf(::FriendsViewModel)
}