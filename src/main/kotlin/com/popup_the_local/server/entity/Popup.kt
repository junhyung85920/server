package com.popup_the_local.server.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.time.LocalDate

@Document(collection = "popup")
class Popup (
    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    var id : String = ObjectId().toHexString(),

    val title: String,

    val startDate: LocalDate,

    val endDate: LocalDate,

    val address: Address,

    val description: String,

    val category: Category,

    var status: PopupStatus,

    val images: MutableList<String>,

    @DBRef
    @Field(name = "member_id")
    val member: Member

){
    fun toStringStartDate(): String {
        return "${startDate.year}년 ${startDate.month.value}월 ${startDate.dayOfMonth}일"
    }

    fun toStringEndDate(): String {
        return "${endDate.year}년 ${endDate.month.value}월 ${endDate.dayOfMonth}일"

    }

    companion object {
        fun createPopup(title: String, startDate: LocalDate, endDate: LocalDate,
                        address: Address, description: String, category: Category, images: MutableList<String>, member: Member): Popup
        {
            val status =
                if (LocalDate.now().isAfter(endDate)) {
                    PopupStatus.END
                }else if (LocalDate.now().isBefore(startDate)){
                    PopupStatus.SCHEDULED
                }else {PopupStatus.PROGRESS}

            return Popup(
            title = title,
            startDate = startDate,
            endDate = endDate,
            address = address,
            description = description,
            category = category,
            images = images,
            member = member,
                status = status
            )
        }
    }
}