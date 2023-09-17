package more.profile

import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import models.more.profile.UpdateProfileBody
import repositories.more.MoreRepository

class ProfileViewModel : BaseSharedViewModel<ProfileViewState, ProfileAction, ProfileEvent>(
    initialState = ProfileViewState()
) {

    private val moreRepository: MoreRepository = Inject.instance()

    override fun obtainEvent(viewEvent: ProfileEvent) {
        when (viewEvent) {
            is ProfileEvent.OpenFAQClick -> openFAQScreen()
            is ProfileEvent.UpdateData -> sendProfile()
            is ProfileEvent.UploadAvatar -> uploadAvatar()
            else -> {}
        }
    }

    private fun uploadAvatar() = viewModelScope.launch {
        viewState = try {
            viewState.image?.let {
                moreRepository.uploadImage(viewState.image)
            }
            viewState.copy(image = viewState.image)
        } catch (e:Exception){
            e.printStackTrace()
            viewState.copy(image = null)
        }
    }

    private fun sendProfile() = viewModelScope.launch {
        viewState = try {
            val response = moreRepository.updateProfile(
                UpdateProfileBody(
                    dateOfBirthday = viewState.date,
                    fullname = viewState.name,
                    gender = viewState.gender,
                    lastName = viewState.surname,
                    phoneNumber = viewState.phone
                ),
            )
            viewState.copy(
                getProfileResponse = response
            )

        } catch (e: Exception) {
            e.printStackTrace()
            viewState.copy(getProfileResponse = null)
        }
    }

    private fun openFAQScreen() {

    }

    init {
        getProfile()
    }

    private fun getProfile() {
        viewModelScope.launch {
            viewState = try {
                val response = moreRepository.getProfile()
                viewState.copy(
                    getProfileResponse = response
                )
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.copy(
                    getProfileResponse = null
                )
            }
        }
    }
}