package ua.nure.chumchase.feature.friends.domain

import kotlinx.coroutines.delay
import ua.nure.chumchase.core.base.BaseResult

class SearchFriendUseCase {
    suspend fun execute(query: String): BaseResult<List<FriendDTO>> {
        delay(1000)
        return BaseResult(isSuccess = true, data = listOf())
    }
}