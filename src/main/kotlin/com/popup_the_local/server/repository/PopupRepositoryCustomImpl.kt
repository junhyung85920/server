package com.popup_the_local.server.repository

import com.popup_the_local.server.entity.Category
import com.popup_the_local.server.entity.Popup
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

@Repository
class PopupRepositoryCustomImpl(
    private val mongoTemplate: MongoTemplate
): PopupRepositoryCustom  {

    override fun findByCategoryAndCity(category: Category?, city: String?): List<Popup> {
        val query = Query()

        if (category != null) {
            query.addCriteria(Criteria.where("category").`is`(category))
        }

        if (city != null) {
            query.addCriteria(Criteria.where("address.city").`is`(city))
        }

        return mongoTemplate.find(query, Popup::class.java)
    }
}