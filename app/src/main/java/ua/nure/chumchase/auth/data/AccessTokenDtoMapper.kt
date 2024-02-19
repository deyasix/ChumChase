package ua.nure.chumchase.auth.data

import ua.nure.chumchase.auth.data.entity.AccessTokenDto
import ua.nure.chumchase.core.data.token.AccessToken
import ua.nure.chumchase.core.domain.DomainMapper

class AccessTokenDtoMapper : DomainMapper<AccessTokenDto, AccessToken> {
    override fun mapToDomainModel(model: AccessTokenDto): AccessToken {
        return AccessToken(model.accessToken, model.refreshToken, model.expiresIn)
    }

    override fun mapFromDomainModel(domainModel: AccessToken): AccessTokenDto {
        return AccessTokenDto(
            domainModel.accessToken,
            domainModel.refreshToken,
            domainModel.expiresIn
        )
    }
}