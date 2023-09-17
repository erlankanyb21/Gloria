package ktor.more

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
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
                bearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk0OTYxMDk4LCJpYXQiOjE2OTQ5NTM4OTgsImp0aSI6IjFiZmZmYmE1NTU5NTRjMDg4Y2NjYTcxNjEzNDQxM2IwIiwidXNlcl9pZCI6NX0.cTRdfKADNh8QWIi61sap8I3XLWtCRb4O4hjmnjIzIwU")
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
                bearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk0OTYxMDk4LCJpYXQiOjE2OTQ5NTM4OTgsImp0aSI6IjFiZmZmYmE1NTU5NTRjMDg4Y2NjYTcxNjEzNDQxM2IwIiwidXNlcl9pZCI6NX0.cTRdfKADNh8QWIi61sap8I3XLWtCRb4O4hjmnjIzIwU")
                setBody(updateProfileBody)
            }
        }.body()
    }

    @OptIn(InternalAPI::class)
    suspend fun editImage(image: ByteArray): GetProfileResponse {
        return httpClient.patch {
            url {
                path("profile/5/")
                bearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk0OTUyODY4LCJpYXQiOjE2OTQ5NDU2NjgsImp0aSI6IjZhMmI2ZTQzNGJmMzQzODhiMzU1NzM2MjU2ZmU5NzcwIiwidXNlcl9pZCI6NX0.Hq60YHcNxKO4nVYpYYt9jON-hgDYk_5M0XXC3-rmIGs")
                contentType(ContentType.MultiPart.FormData)
                body = MultiPartFormDataContent(formData {
                    append("avatar", ByteArrayContent(image), Headers.build {
                        append(HttpHeaders.ContentType, "image/png")
                    })
                })
            }
        }.body()
    }
}