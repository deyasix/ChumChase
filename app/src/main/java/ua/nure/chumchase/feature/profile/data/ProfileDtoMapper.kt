package ua.nure.chumchase.feature.profile.data

import ua.nure.chumchase.core.domain.DomainMapper
import ua.nure.chumchase.feature.profile.data.entity.ProfileDto
import ua.nure.chumchase.feature.profile.domain.model.Profile

class ProfileDtoMapper(private val commentDtoMapper: CommentDtoMapper) :
    DomainMapper<ProfileDto, Profile> {
    override fun mapToDomainModel(model: ProfileDto): Profile {
        val comments = model.comments?.map { commentDtoMapper.mapToDomainModel(it) }
        return Profile(
            model.uid,
            model.login,
            model.email,
            model.photoUrl,
            model.tags ?: listOf(),
            comments ?: listOf()
        )
    }

    override fun mapFromDomainModel(domainModel: Profile): ProfileDto {
        val comments = domainModel.comments.map { commentDtoMapper.mapFromDomainModel(it) }
        return ProfileDto(
            domainModel.uid,
            domainModel.login,
            domainModel.email,
            domainModel.photoUrl,
            domainModel.tags,
            comments
        )
    }
}