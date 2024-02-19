package ua.nure.chumchase.feature.profile.domain

import ua.nure.chumchase.core.base.BaseOperationResult
import ua.nure.chumchase.feature.profile.domain.model.PlainComment

class SendCommentUseCase(private val commentDataSource: CommentDataSource) {
    suspend fun execute(plainComment: PlainComment): BaseOperationResult {
        return commentDataSource.sendComment(plainComment)
    }
}