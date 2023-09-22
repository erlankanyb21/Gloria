package more.profile

import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import models.more.profile.UpdateProfileBody
import repositories.more.MoreRepository

class ProfileViewModel : BaseSharedViewModel<ProfileViewState, ProfileAction, ProfileEvent>(
    initialState = ProfileViewState()
) {

    private val moreRepository: MoreRepository = Inject.instance()

    override fun obtainEvent(viewEvent: ProfileEvent) {
        when (viewEvent) {
            is ProfileEvent.OpenFAQClick -> openFAQScreen()
            is ProfileEvent.OpenQAClick -> openQAScreen()
            is ProfileEvent.UpdateData -> sendProfile()
            is ProfileEvent.UploadAvatar -> uploadAvatar()
            is ProfileEvent.DeleteAccount -> deleteAccount()
        }
    }

    init {
        getProfile()
    }

    private fun getProfile() = withViewModelScope {
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

    private fun openFAQScreen() {
        viewAction = ProfileAction.OpenFAQ
    }

    private fun openQAScreen() {
        viewAction = ProfileAction.OpenQA
    }

    private fun sendProfile() = withViewModelScope {
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

    private fun uploadAvatar() = withViewModelScope {
        viewState = try {
            val data = viewState.image?.let {
                moreRepository.uploadImage(viewState.image)
            }
            viewState.copy(getProfileResponse = data)
        } catch (e: Exception) {
            e.printStackTrace()
            viewState.copy(image = null)
        }
    }

    private fun deleteAccount() = withViewModelScope {
        viewState = try {
            val response = moreRepository.deleteAccount()
            viewState.copy(
                isAccountDeleted = response
            )
        } catch (e: Exception) {
            e.printStackTrace()
            viewState.copy(
                isAccountDeleted = null
            )
        }
    }
}