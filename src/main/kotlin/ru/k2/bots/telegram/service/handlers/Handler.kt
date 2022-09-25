package ru.k2.bots.telegram.service.handlers

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText
import org.telegram.telegrambots.meta.api.objects.Update

interface Handler {
    fun messageHandler(update: Update): SendMessage
    fun callbackHandler(update: Update): EditMessageText
}