package com.popup_the_local.server.service

import com.popup_the_local.server.common.cloudstorage.CloudStorageService
import com.popup_the_local.server.common.responsebody.InvalidInputException
import com.popup_the_local.server.dto.*
import com.popup_the_local.server.entity.Address
import com.popup_the_local.server.entity.Application
import com.popup_the_local.server.entity.Popup
import com.popup_the_local.server.repository.ApplicationRepository
import com.popup_the_local.server.repository.MemberRepository
import com.popup_the_local.server.repository.PopupRepository
import kotlinx.coroutines.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate

@Service
@Transactional(readOnly = true)
class ApplicationService(
    private val memberRepository: MemberRepository,
    private val applicationRepository: ApplicationRepository,
    private val cloudStorageService: CloudStorageService,
    private val popupRepository: PopupRepository,
    private val template: RestTemplate,

) {
    @Value("\${openai.model}")
    lateinit var model: String

    @Value("\${openai.api.url}")
    lateinit var url: String


    @Transactional
    fun applyPopup(memberId: String, request: ApplyPopupRequest): ApplyPopupResponse {
        val member = memberRepository.findByIdOrNull(memberId) ?: throw InvalidInputException(fieldName = "member")
        val imageUrlList: MutableList<String> = request.images.map { image ->
            cloudStorageService.uploadObject(image)
        }.toMutableList()

        val strings = request.address.split(",")

        Popup.createPopup(
            title = request.title,
            description = request.description,
            startDate = request.startDate,
            endDate = request.endDate,
            category = request.category,
            member = member,
            images = imageUrlList.toMutableList(),
            address = Address(city = strings[0], street = strings[1], zipCode = strings[2]),
        ).let { popupRepository.save(it)  }

        val application = Application.createApplication(
            title = request.title,
            description = request.description,
            startDate = request.startDate,
            endDate = request.endDate,
            category = request.category,
            member = member,
            address = Address(city = strings[0], street = strings[1], zipCode = strings[2]),
            images = imageUrlList.toMutableList()

        ).apply {
            applicationRepository.save(this)
        }

        return ApplyPopupResponse(
            application.id
        )
    }

    fun recommendEvent(
        request: RecommendEventRequest,
    ): RecommendEventResponse {

        val chatGptModel = ChatGPTRequest(model, request.description)
        val chatGPTResponse = template.postForObject(url, chatGptModel, ChatGPTResponse::class.java)

        // openAI로부터 메시지 수신
        val response =
            chatGPTResponse!!
                .choices[0]
                .message.content
                .split('#')

        return RecommendEventResponse(content = response)
    }

}