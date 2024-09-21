package com.popup_the_local.server.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.time.LocalDate

@Document(collection = "bookmark")
data class Bookmark(
    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    var id: String = ObjectId().toHexString(),

    @DBRef
    @Field(name = "member_id")
    val member: Member,

    @DBRef
    @Field(name = "popup_id")
    val popup: Popup,

    @CreatedDate
    var createdAt: LocalDate? = null
){
    companion object {
        fun createBookmark(member: Member, popup: Popup) = Bookmark(
            member =  member,
            popup = popup,
        )
    }
}
