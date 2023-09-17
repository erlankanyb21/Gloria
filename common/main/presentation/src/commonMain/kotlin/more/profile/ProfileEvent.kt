package more.profile

sealed class ProfileEvent {
    object UpdateData : ProfileEvent()
    object OpenFAQClick : ProfileEvent()
    object OpenQAClick : ProfileEvent()
}