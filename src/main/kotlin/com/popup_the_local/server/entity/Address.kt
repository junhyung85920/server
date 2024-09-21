package com.popup_the_local.server.entity


import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "address")
data class Address (
    val city : String,
    val street: String,
    val zipCode : String?,
) {
    override fun toString(): String {
        return "$city $street $zipCode"
    }
}
