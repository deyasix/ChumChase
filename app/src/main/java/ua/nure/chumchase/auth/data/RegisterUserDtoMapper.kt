package ua.nure.chumchase.auth.data

import ua.nure.chumchase.auth.data.entity.RegisterUserDto
import ua.nure.chumchase.auth.domain.model.RegisterUser
import ua.nure.chumchase.core.domain.DomainMapper

class RegisterUserDtoMapper : DomainMapper<RegisterUserDto, RegisterUser> {
    override fun mapToDomainModel(model: RegisterUserDto): RegisterUser {
        return RegisterUser(model.login, model.email, model.password)
    }

    override fun mapFromDomainModel(domainModel: RegisterUser): RegisterUserDto {
        return RegisterUserDto(domainModel.login, domainModel.email, domainModel.password)
    }

}