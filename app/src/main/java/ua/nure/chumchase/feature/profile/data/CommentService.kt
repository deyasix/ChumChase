package ua.nure.chumchase.feature.profile.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ua.nure.chumchase.core.data.MessageDto
import ua.nure.chumchase.feature.profile.data.entity.PlainCommentDto

interface CommentService {
    @POST("comment/create")
    suspend fun sendComment(@Body plainCommentDto: PlainCommentDto): Response<MessageDto>
}