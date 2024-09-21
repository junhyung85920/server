package com.popup_the_local.server.repository

import com.popup_the_local.server.entity.Comment
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository: MongoRepository<Comment, String>