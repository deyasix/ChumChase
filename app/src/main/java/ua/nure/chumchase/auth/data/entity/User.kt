package ua.nure.chumchase.auth.data.entity

import ua.nure.chumchase.core.base.ResponseEntity
import ua.nure.chumchase.feature.profile.domain.model.UserInfoDTO

data class User(
    val id: Int,
    val login: String,
    val password: String,
    val email: String,
    val tags: List<String> = listOf(),
    val photoUrl: String? = null,
    val comments: List<Comment> = listOf()
):ResponseEntity<UserInfoDTO> {
    override fun toDomainModel(): UserInfoDTO {
        return UserInfoDTO(login, tags, photoUrl, comments.map { it.toDomainModel() })
    }

}