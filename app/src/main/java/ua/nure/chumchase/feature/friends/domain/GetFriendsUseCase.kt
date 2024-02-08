package ua.nure.chumchase.feature.friends.domain

import kotlinx.coroutines.delay
import ua.nure.chumchase.core.base.BaseDataResult

class GetFriendsUseCase {
    suspend fun execute(): BaseDataResult<List<FriendDTO>> {
        delay(1000)
        return BaseDataResult(isSuccess = true, data = listOf())
    }
}