package ua.nure.chumchase.feature.profile.data

import ua.nure.chumchase.core.domain.DomainMapper
import ua.nure.chumchase.feature.profile.data.entity.CommentDto
import ua.nure.chumchase.feature.profile.data.entity.CommentSenderDto
import ua.nure.chumchase.feature.profile.domain.model.Comment

class CommentDtoMapper : DomainMapper<CommentDto, Comment> {
    override fun mapToDomainModel(model: CommentDto): Comment {
        return Comment(
            model.id,
            model.sender.id,
            model.sender.photoUrl,
            model.sender.login,
            model.receiverId,
            model.text,
            model.date
        )
    }

    override fun mapFromDomainModel(domainModel: Comment): CommentDto {
        return CommentDto(
            domainModel.id,
            domainModel.receiverId,
            CommentSenderDto(
                domainModel.senderId,
                domainModel.senderLogin,
                domainModel.senderPhotoUrl
            ),
            domainModel.text,
            domainModel.date
        )
    }
}