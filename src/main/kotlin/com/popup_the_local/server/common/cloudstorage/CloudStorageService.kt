package com.popup_the_local.server.common.cloudstorage

import com.google.cloud.storage.BlobId
import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.StorageOptions
import com.google.cloud.storage.Storage
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.nio.ByteBuffer
import java.util.*

@Component
class CloudStorageService() {

    @Value("\${cloud.project.id}")
    lateinit var projectId: String

    @Value("\${cloud.bucket.name}")
    lateinit var bucketName: String

    fun uploadObject(image: MultipartFile): String {

        val storage: Storage = StorageOptions.newBuilder().setProjectId(projectId).build().service
        val objectName = UUID.randomUUID().toString()
        val blobId: BlobId = BlobId.of(bucketName, objectName)
        val blobInfo: BlobInfo = BlobInfo.newBuilder(blobId)
            .setContentType(image.contentType)
            .build()

        storage.writer(blobInfo).use { writer ->
            writer.write(ByteBuffer.wrap(image.bytes))
        }

        return "https://storage.googleapis.com/$bucketName/$objectName"
    }
}
