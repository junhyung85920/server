package com.popup_the_local.server.dto

import com.popup_the_local.server.external.chatgpt.PROMPT
import org.w3c.dom.Text


data class RequestMessage(
    val role: String,
    val content: List<Any>,
)

data class ResponseMessage(
    val role: String,
    val content: String,
)


data class TextContent(
    val text: String,
    val type: String = "text",
)


data class ChatGPTRequest(
    var model: String,
    var messages: MutableList<RequestMessage>,
) {
    constructor(model: String, description: String) :
            this(
                model,
                mutableListOf(
                    RequestMessage(
                        "user",
                        mutableListOf(TextContent("$PROMPT \n $description")),
                    ),
                ),
            )
}

data class ChatGPTResponse(
    val choices: List<Choice>,
)

data class Choice(
    var index: Int,
    var message: ResponseMessage,
)
