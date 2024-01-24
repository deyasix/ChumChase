package ua.nure.chumchase.feature.friends.domain

import kotlinx.coroutines.delay
import ua.nure.chumchase.core.base.BaseResult

class GetFriendsUseCase {
    suspend fun execute(): BaseResult<List<FriendDTO>> {
        delay(1000)
        return BaseResult(isSuccess = true, data = listOf())
    }
}