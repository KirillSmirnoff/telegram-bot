package ru.k2.bots.telegram.bot

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText

data class GenericMessage (
    var sendMessage: SendMessage? = null
    )