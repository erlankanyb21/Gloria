package more.profile

sealed class ProfileEvent {
    object UpdateData : ProfileEvent()
    object UploadAvatar : ProfileEvent()
    object OpenFAQClick : ProfileEvent()
    object OpenQAClick : ProfileEvent()
    object DeleteAccount : ProfileEvent()
}