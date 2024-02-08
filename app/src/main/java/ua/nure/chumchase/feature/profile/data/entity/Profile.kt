package ua.nure.chumchase.feature.profile.data.entity

import ua.nure.chumchase.core.base.ResponseEntity
import ua.nure.chumchase.feature.profile.domain.model.ProfileDTO

data class Profile(
    val uid: String,
    val login: String,
    val email: String,
    val photo_url: String,
    val tag_list: List<String>
) : ResponseEntity<ProfileDTO> {
    override fun toDomainModel(): ProfileDTO = ProfileDTO(uid, login, email, photo_url, tag_list)

}
