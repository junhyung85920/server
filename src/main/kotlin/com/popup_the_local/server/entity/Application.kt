package com.popup_the_local.server.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.time.LocalDate

@Document(collection = "application")
data class Application(
    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    var id: String = ObjectId().toHexString(),

    @DBRef
    @Field(name = "member_id")
    val member: Member,

    val title: String,

    val startDate: LocalDate,

    val endDate: LocalDate,

    val description: String,

    var category: Category,

    var status: ApplicationStatus = ApplicationStatus.WAITED,

    val address: Address,

    val images: MutableList<String>,
){
    companion object {
        fun createApplication(member: Member, title: String, startDate: LocalDate, endDate: LocalDate, description: String, category: Category,  address: Address, images: MutableList<String> )
        = Application(
            member = member,
            title = title,
            startDate = startDate,
            endDate = endDate,
            description = description,
            category = category,
            address = address,
            images = images
        )
    }
}
