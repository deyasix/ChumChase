package ua.nure.chumchase.feature.profile.data

import ua.nure.chumchase.core.domain.DomainMapper
import ua.nure.chumchase.feature.profile.data.entity.PlainCommentDto
import ua.nure.chumchase.feature.profile.domain.model.PlainComment

class PlainCommentDtoMapper : DomainMapper<PlainCommentDto, PlainComment> {
    override fun mapToDomainModel(model: PlainCommentDto): PlainComment {
        return PlainComment(model.receiverId, model.text)
    }

    override fun mapFromDomainModel(domainModel: PlainComment): PlainCommentDto {
        return PlainCommentDto(domainModel.receiverId, domainModel.text ?: "")
    }
}