package com.popup_the_local.server.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType

data class Comment(
    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    var id: String = ObjectId().toHexString(),

    @DBRef
    val member: Member,

    @DBRef
    val popup: Popup,

    var content: String,
) {
    companion object {
        fun createComment(
            member: Member,
            content: String,
            popup: Popup,
        ) = Comment(
            member = member,
            content = content,
            popup = popup,
        )
    }
}
