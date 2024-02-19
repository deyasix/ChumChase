package ua.nure.chumchase.feature.profile.domain

import ua.nure.chumchase.core.base.BaseOperationResult
import ua.nure.chumchase.feature.profile.domain.model.PlainComment

interface CommentDataSource {
    suspend fun sendComment(plainComment: PlainComment): BaseOperationResult
}