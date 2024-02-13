package ua.nure.chumchase.feature.profile.data

import ua.nure.chumchase.core.base.BaseOperationResult
import ua.nure.chumchase.core.utils.getErrorMessage
import ua.nure.chumchase.core.utils.handle
import ua.nure.chumchase.feature.profile.domain.CommentDataSource
import ua.nure.chumchase.feature.profile.domain.model.PlainComment

class CommentDataSourceImpl(
    private val commentService: CommentService,
    private val plainCommentDtoMapper: PlainCommentDtoMapper
) : CommentDataSource {
    override suspend fun sendComment(plainComment: PlainComment): BaseOperationResult {
        return try {
            val response =
                commentService.sendComment(plainCommentDtoMapper.mapFromDomainModel(plainComment))
            response.handle()
        } catch (exception: Exception) {
            BaseOperationResult(false, exception.getErrorMessage())
        }
    }
}