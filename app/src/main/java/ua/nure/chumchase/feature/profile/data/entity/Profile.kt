package ua.nure.chumchase.feature.profile.data.entity

import com.google.gson.annotations.SerializedName
import ua.nure.chumchase.core.base.ResponseEntity
import ua.nure.chumchase.feature.profile.domain.model.ProfileDTO

data class Profile(
    val uid: String,
    val login: String,
    val email: String,
    @SerializedName("photo_url") val photoUrl: String,
    @SerializedName("tag_list") val tags: List<String>
) : ResponseEntity<ProfileDTO> {
    override fun toDomainModel(): ProfileDTO = ProfileDTO(uid, login, email, photoUrl, tags)

}
