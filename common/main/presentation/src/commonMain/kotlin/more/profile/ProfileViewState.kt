package more.profile

import models.more.profile.GetProfileResponse

data class ProfileViewState(
    val getProfileResponse: GetProfileResponse? = null,
    var name: String? = null,
    var surname: String? = null,
    var date: String? = null,
    var gender: String? = null,
    var phone: String? = null,
    var image: ByteArray? = null,
    var profileUri: String? = null,
    var isAccountDeleted: Boolean? = null
)