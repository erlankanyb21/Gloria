package ktor.more

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.delete
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.path
import io.ktor.util.InternalAPI
import models.more.faq.FAQResponse
import models.more.faq.WhatsAppResponse
import models.more.profile.GetProfileResponse
import models.more.profile.UpdateProfileBody

class MoreDataSource(
    private val httpClient: HttpClient
) {
    suspend fun getProfile(): GetProfileResponse {
        return httpClient.get {
            url {
                path("profile/30")
                bearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk1MzgzOTcxLCJpYXQiOjE2OTUzNzY3NzEsImp0aSI6ImE1MWNmZjhhMGExODQ3M2JhZDEwMDcyN2NiZDIwM2RhIiwidXNlcl9pZCI6MzB9.5AfL08jeq1hgKlvpIkG6NpJXMDTFRGAypG1d9-8Osww")
            }
        }.body()
    }

    suspend fun fetchFAQ(): FAQResponse {
        return httpClient.get {
            url {
                path("quationsanswers")
            }
        }.body()
    }

    suspend fun openWhatsApp(): WhatsAppResponse {
        return httpClient.get {
            url {
                path("whatsapp")
            }
        }.body()
    }

    suspend fun updateProfile(updateProfileBody: UpdateProfileBody): GetProfileResponse {
        return httpClient.patch {
            url {
                path("profile/30/")
                bearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk1MzgzOTcxLCJpYXQiOjE2OTUzNzY3NzEsImp0aSI6ImE1MWNmZjhhMGExODQ3M2JhZDEwMDcyN2NiZDIwM2RhIiwidXNlcl9pZCI6MzB9.5AfL08jeq1hgKlvpIkG6NpJXMDTFRGAypG1d9-8Osww")
                setBody(updateProfileBody)
            }
        }.body()
    }

    @OptIn(InternalAPI::class)
    suspend fun editImage(image: ByteArray?): GetProfileResponse {
        return httpClient.patch {
            url {
                path("profile/30/")
                bearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk1MzgzOTcxLCJpYXQiOjE2OTUzNzY3NzEsImp0aSI6ImE1MWNmZjhhMGExODQ3M2JhZDEwMDcyN2NiZDIwM2RhIiwidXNlcl9pZCI6MzB9.5AfL08jeq1hgKlvpIkG6NpJXMDTFRGAypG1d9-8Osww")
                if (image != null) {
                    body = MultiPartFormDataContent(
                        formData {
                            append("avatar", image, Headers.build {
                                append(HttpHeaders.ContentType, "image/jpeg")
                                append(HttpHeaders.ContentDisposition, "filename=image.png")
                            })
                        }
                    )
                }
            }
        }.body()
    }

    suspend fun deleteAccount(): Boolean {
        val response = httpClient.delete {
            url {
                path("profile/30/")
                bearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk1MzgzOTcxLCJpYXQiOjE2OTUzNzY3NzEsImp0aSI6ImE1MWNmZjhhMGExODQ3M2JhZDEwMDcyN2NiZDIwM2RhIiwidXNlcl9pZCI6MzB9.5AfL08jeq1hgKlvpIkG6NpJXMDTFRGAypG1d9-8Osww")
            }
        }

        return when (response.status) {
            HttpStatusCode.NoContent -> true
            else -> false
        }
    }
}