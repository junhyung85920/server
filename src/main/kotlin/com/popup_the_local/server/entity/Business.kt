package com.popup_the_local.server.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType

@Document(collection = "business")
data class Business (

    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    var id: String = ObjectId().toHexString(),

    val phoneNum: String,

    val name: String,

)