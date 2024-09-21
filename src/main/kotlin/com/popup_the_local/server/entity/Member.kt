package com.popup_the_local.server.entity

import org.bson.types.ObjectId
import org.example.springmongodb.entity.Role
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType

@Document(collection = "member")
data class Member (
    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    var id : String = ObjectId().toHexString(),

    var socialId: String,

    val email: String,

    val image: String,

    val role: Role,

    val name : String,

    var phone: String? = null,

    var address: Address? = null,

    var businessNumber: String? = null,

    ) {
    companion object {
        fun createMember(socialId: String,
                         email: String,
                         image: String,
                         role: Role,
                         name : String,
                         businessNumber: String? = null,
                         address: Address? = null,
                         phone: String? = null,
                         ): Member = Member(socialId =  socialId,
                             email = email,
                             image = image,
                             role = role,
                             name = name,
                             phone = phone,
                             address = address,
                             businessNumber = businessNumber,
                         )
    }
}
