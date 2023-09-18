package ktor.more

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.onUpload
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.forms.FormPart
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.patch
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.content.ByteArrayContent
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.content.PartData
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.http.path
import io.ktor.util.InternalAPI
import io.ktor.utils.io.core.Input
import io.ktor.utils.io.core.buildPacket
import io.ktor.utils.io.core.writeFully
import io.ktor.utils.io.errors.IOException
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
                path("profile/5")
                bearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk1MTA0MDIzLCJpYXQiOjE2OTUwOTY4MjMsImp0aSI6IjFiOGY5ZTVmYzk0OTQwOGY5NTQ1NTI3YmE2OWMxMGRiIiwidXNlcl9pZCI6NX0.Y2JXLuH4ViDCOiM08ciaE3bQltF5Dt3UHP2P12ZN-xE")
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
                path("profile/5/")
                bearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk1MTA0MDIzLCJpYXQiOjE2OTUwOTY4MjMsImp0aSI6IjFiOGY5ZTVmYzk0OTQwOGY5NTQ1NTI3YmE2OWMxMGRiIiwidXNlcl9pZCI6NX0.Y2JXLuH4ViDCOiM08ciaE3bQltF5Dt3UHP2P12ZN-xE")
                setBody(updateProfileBody)
            }
        }.body()
    }

    @OptIn(InternalAPI::class)
    suspend fun editImage(image: ByteArray?): GetProfileResponse {
        return httpClient.patch {
            url {
                path("profile/5/")
                bearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk1MTA0MDIzLCJpYXQiOjE2OTUwOTY4MjMsImp0aSI6IjFiOGY5ZTVmYzk0OTQwOGY5NTQ1NTI3YmE2OWMxMGRiIiwidXNlcl9pZCI6NX0.Y2JXLuH4ViDCOiM08ciaE3bQltF5Dt3UHP2P12ZN-xE")
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
}