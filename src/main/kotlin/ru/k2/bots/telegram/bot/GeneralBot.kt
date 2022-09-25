package ru.k2.bots.telegram.bot

import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText
import org.telegram.telegrambots.meta.api.objects.Update
import ru.k2.bots.telegram.config.TelegramBotProperties
import ru.k2.bots.telegram.service.CheatSheetJavaCollectionService
import java.util.regex.Pattern

@Component
class GeneralBot(private val properties: TelegramBotProperties,
                 private val collectionService: CheatSheetJavaCollectionService) : TelegramLongPollingBot() {

    override fun getBotToken(): String {
        return properties.token
    }

    override fun getBotUsername(): String {
        return properties.name
    }

    override fun onUpdateReceived(update: Update?) {
        if (update != null) {
            if (update.hasMessage()) {
                messageHandler(update)
            } else if (update.hasCallbackQuery()) {
                callbackHandler(update)
            }
        }
    }

    private fun callbackHandler(update: Update) {
        when (messageParsing("(/\\w*)", update.callbackQuery.data.plus("/"))) {
            "/collection" -> execute(collectionService.callbackHandler(update))
            "/result" -> success(update)
            else -> execute(EditMessageText()) //todo продумать
        }
    }

    private fun messageHandler(update: Update) {
        when (messageParsing("(/\\w*)", update.message.text.plus("/"))) {
            "/collection" -> execute(collectionService.messageHandler(update))
            else -> failure(update)
        }
    }

    private fun failure(update: Update): SendMessage {
        val message = SendMessage()
        message.chatId = update.message.chatId.toString()
        message.text = "Команда \"${update.message.text}\" не поддерживается"
        message.replyToMessageId = update.message.messageId

        return message
    }

     private fun success(update: Update) {
         when (messageParsing("/result(/\\w*)", update.callbackQuery.data.plus("/"))) {
             "/collection" -> execute(collectionService.successHandler(update))
             else -> failure(update)
         }
         execute(collectionService.tryAgain(update))
    }

    private fun callbackFailure(update: Update): AnswerCallbackQuery {
        return AnswerCallbackQuery()
    }

    private fun messageParsing(regex: String, message: String): String {
        val pattern = Pattern.compile(regex, Pattern.MULTILINE)
        val matcher = pattern.matcher(message)
        matcher.find()

        return matcher.group(1)
    }
}