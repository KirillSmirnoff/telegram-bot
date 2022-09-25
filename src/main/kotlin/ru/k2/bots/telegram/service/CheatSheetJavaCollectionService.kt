package ru.k2.bots.telegram.service

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import ru.k2.bots.telegram.service.handlers.Handler

@Service
class CheatSheetJavaCollectionService: Handler {

    override fun messageHandler(update: Update): SendMessage {
        return when (update.message.text){
            "/collection" -> start(update.message.chatId.toString())
            else -> SendMessage()
        }
    }

    override fun callbackHandler(update: Update): EditMessageText {
        return when (update.callbackQuery.data) {
            "/collection/value" -> value(update)
            "/collection/map" -> map(update)
            "/collection/map/order" -> mapOrder(update)
            else -> EditMessageText() //todo продумать как отбработать
        }
    }

    fun successHandler(update: Update): AnswerCallbackQuery{
        return when (update.callbackQuery.data) {
            "/result/collection/map/order/ordered" -> ordered(update)
            "/result/collection/map/order/sorted" -> sorted(update)
            "/result/collection/map/noOrder" -> mapNoOrder(update)
            else -> AnswerCallbackQuery() //todo продумать как отбработать
        }
    }

    fun start(chatId: String): SendMessage {
        val message = SendMessage()
        message.chatId = chatId
        message.text = "Выбери что будет содержать коллекция"
        message.replyMarkup = getInlineKeyboard(
                mapOf(
                        1 to mapOf("Ключ/Значение" to "/collection/map", "Значение" to "/collection/value"),
                        2 to mapOf("Back" to "/back")
                )
        )
        return message
    }

    fun map(update: Update): EditMessageText {
        return  EditMessageText.builder()
                .messageId(update.callbackQuery.message.messageId)
                .chatId(update.callbackQuery.message.chatId.toString())
                .text("Важен ли порядок ?")
                .replyMarkup(getInlineKeyboard(
                        mapOf(
                                1 to mapOf("ДА" to "/collection/map/order", "НЕТ" to "/result/collection/map/noOrder"),
                                2 to mapOf("Back" to "/back")
                        )
                ))
                .build()
    }

    fun mapOrder(update: Update): EditMessageText{
        return  EditMessageText.builder()
                .messageId(update.callbackQuery.message.messageId)
                .chatId(update.callbackQuery.message.chatId.toString())
                .text("Соблюдать порядок вставки или сортировать по ключам ?")
                .replyMarkup(getInlineKeyboard(
                        mapOf(
                                1 to mapOf("Соблюдать порядок" to "/result/collection/map/order/ordered", "Сортировать" to "/result/collection/map/order/sorted"),
                                2 to mapOf("Back" to "/back")
                        )
                ))
                .build()
    }

    fun mapNoOrder(update: Update): AnswerCallbackQuery{
        val answerCallbackQuery = AnswerCallbackQuery()
        answerCallbackQuery.callbackQueryId = update.callbackQuery.id
        answerCallbackQuery.text = "Используйте HashMap"
        answerCallbackQuery.showAlert = true

        return answerCallbackQuery
    }

    fun sorted(update: Update): AnswerCallbackQuery{
        val answerCallbackQuery = AnswerCallbackQuery()
        answerCallbackQuery.callbackQueryId = update.callbackQuery.id
        answerCallbackQuery.text = "Используйте TreeMap"
        answerCallbackQuery.showAlert = true

        return answerCallbackQuery
    }

    fun ordered(update: Update): AnswerCallbackQuery{
        val answerCallbackQuery = AnswerCallbackQuery()
        answerCallbackQuery.callbackQueryId = update.callbackQuery.id
        answerCallbackQuery.text = "Используйте LinkedHashMap"
        answerCallbackQuery.showAlert = true

        return answerCallbackQuery
    }

    fun value(update: Update): EditMessageText{
        return  EditMessageText.builder()
                .messageId(update.callbackQuery.message.messageId)
                .chatId(update.callbackQuery.message.chatId.toString())
                .text("Будут ли храниться дубликаты ?")
                .replyMarkup(getInlineKeyboard(
                        mapOf(
                                1 to mapOf("ДА" to "/collection/value/", "НЕТ" to "/collection/value"),
                                2 to mapOf("Back" to "/back")
                        )
                ))
                .build()
    }

    private fun getInlineKeyboard(buttonValue: Map<Int, Map<String, String>>): InlineKeyboardMarkup {
        val inlineKeyboardBuilder = InlineKeyboardMarkup.builder()
        for (rowNumber in buttonValue.keys){
            val map = buttonValue[rowNumber]
            if (map != null) {
                val keyboardRow = mutableListOf<InlineKeyboardButton>()
                for ((key, value) in map){
                    keyboardRow.add(InlineKeyboardButton.builder()
                            .text(key)
                            .callbackData(value)
                            .build())
                }
                inlineKeyboardBuilder.keyboardRow(keyboardRow)
            }
        }
        return inlineKeyboardBuilder
                .keyboard(mutableListOf<List<InlineKeyboardButton>>())
                .build()
    }

    fun tryAgain(update: Update): EditMessageText {
        return  EditMessageText.builder()
                .messageId(update.callbackQuery.message.messageId)
                .chatId(update.callbackQuery.message.chatId.toString())
                .text("Выбери что будет содержать коллекция")
                .replyMarkup(getInlineKeyboard(
                        mapOf(
                                1 to mapOf("Ключ/Значение" to "/collection/map", "Значение" to "/collection/value"),
                                2 to mapOf("Back" to "/back")
                        )
                ))
                .build()

    }

}