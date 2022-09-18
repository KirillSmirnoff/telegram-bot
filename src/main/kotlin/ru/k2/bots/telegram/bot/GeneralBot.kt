package ru.k2.bots.telegram.bot

import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import ru.k2.bots.telegram.config.TelegramBotProperties
import java.time.Instant

@Component
class GeneralBot(val properties: TelegramBotProperties) : TelegramLongPollingBot() {

    override fun getBotToken(): String {
        return properties.token
    }

    override fun getBotUsername(): String {
        return properties.name
    }

    override fun onUpdateReceived(update: Update?) {
        val message = SendMessage()

        if (update != null) {
            if (update.hasMessage()) {
                val chaId = update.message.chatId.toString()

                message.chatId = chaId
                message.text = "Your chatId is $chaId"

                execute(message)
            }
        }
    }

    fun success(availableSlot: Int, s: String?) {
        val message = SendMessage()

        message.chatId = properties.chatId
        message.text = "Tesxt"

        execute(message)
    }

    fun error() {
        val message = SendMessage()
        message.chatId = properties.chatId
        message.text = "Text"

        execute(message)
    }

    fun failure() {
        val message = SendMessage()
        message.chatId = properties.chatId
        message.text = "Text"

        execute(message)
    }
}