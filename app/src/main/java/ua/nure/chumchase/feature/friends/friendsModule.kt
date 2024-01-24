package ua.nure.chumchase.feature.friends

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ua.nure.chumchase.feature.friends.domain.GetFriendsUseCase
import ua.nure.chumchase.feature.friends.domain.SearchFriendUseCase
import ua.nure.chumchase.feature.friends.presentation.FriendsViewModel

val friendsModule = module {
    singleOf(::GetFriendsUseCase)
    singleOf(::SearchFriendUseCase)
    viewModelOf(::FriendsViewModel)
}