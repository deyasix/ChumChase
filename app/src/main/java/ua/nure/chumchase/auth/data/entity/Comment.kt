package ua.nure.chumchase.auth.data.entity

import ua.nure.chumchase.core.base.ResponseEntity
import ua.nure.chumchase.feature.profile.domain.model.CommentDTO

data class Comment(
    val id: Int,
    val author: String,
    val text: String,
    val authorPhotoUrl: String?,
    val dateTime: String
): ResponseEntity<CommentDTO> {
    override fun toDomainModel(): CommentDTO {
        return CommentDTO(author, text, authorPhotoUrl, dateTime)
    }

}

