package com.popup_the_local.server.repository

import com.popup_the_local.server.entity.Member
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : MongoRepository<Member, String> {

    fun findByEmail(email: String) : Member?

    fun findBySocialId(socialId: String) : Member?
}