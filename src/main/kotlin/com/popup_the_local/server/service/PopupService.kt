package com.popup_the_local.server.service

import com.popup_the_local.server.common.MEMBER_ID
import com.popup_the_local.server.common.cloudstorage.CloudStorageService
import com.popup_the_local.server.common.responsebody.InvalidInputException
import com.popup_the_local.server.dto.*
import com.popup_the_local.server.entity.Address
import com.popup_the_local.server.entity.Category
import com.popup_the_local.server.entity.Popup
import com.popup_the_local.server.repository.BookmarkRepository
import com.popup_the_local.server.repository.MemberRepository
import com.popup_the_local.server.repository.PopupRepository
import com.popup_the_local.server.repository.PopupRepositoryCustom
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PopupService(
    private val memberRepository: MemberRepository,
    private val popupRepository: PopupRepository,
    private val cloudStorageService: CloudStorageService,
    private val popupRepositoryCustom: PopupRepositoryCustom,
    private val bookmarkRepository: BookmarkRepository
) {

    fun createPopup(memberId: String, request: CreatePopupRequest): CreatePopupResponse {

        val imageUrlList: MutableList<String> = request.images.map { image ->
            cloudStorageService.uploadObject(image)
        }.toMutableList()

        val popup = popupRepository.save(
            Popup.createPopup(
                title = request.title,
                images = imageUrlList,
                description = request.description,
                startDate = request.startDate,
                endDate = request.endDate,
                address = Address(
                    zipCode = request.address.zipCode,
                    city = request.address.city,
                    street = request.address.street,
                ),
                member = memberRepository.findByIdOrNull(memberId) ?: throw InvalidInputException(fieldName = "member"),
                category = request.category
            )
        )

        return CreatePopupResponse(popupId = popup.id)
    }

    fun getPopupDetail(popupId: String): GetPopupDetailResponse {
        val popup = popupRepository.findByIdOrNull(popupId) ?: throw InvalidInputException(fieldName = "popup")
        val bookmark = bookmarkRepository.findByMemberIdAndPopupId(MEMBER_ID, popupId)

        return GetPopupDetailResponse(
            popupId = popup.id,
            title = popup.title,
            description = popup.description,
            status = popup.status,
            startDate = popup.toStringStartDate(),
            endDate = popup.toStringEndDate(),
            category = popup.category,
            images = popup.images,
            address = popup.address.toString(),
            bookmarkId  = bookmark?.id
        )
    }

    fun getPopupList(city: String?, category: String?): List<GetPopupListByCategoryResponse> {

        val categoryEnum = try {
            category?.let {
                Category.valueOf(category)
            }

        }catch (e:Exception){
            throw InvalidInputException(fieldName = "category")
        }

        val popupList = popupRepositoryCustom.findByCategoryAndCity(
            category = categoryEnum,
            city = city
            )

        return popupList.map { popup ->
                GetPopupListByCategoryResponse(
                    title = popup.title,
                    description = popup.description,
                    status = popup.status,
                    startDate = popup.toStringStartDate(),
                    endDate = popup.toStringEndDate(),
                    address = popup.address.toString(),
                    popupId = popup.id,
                    category = popup.category,
                    image = popup.images[0]
                )
            }


    }
}