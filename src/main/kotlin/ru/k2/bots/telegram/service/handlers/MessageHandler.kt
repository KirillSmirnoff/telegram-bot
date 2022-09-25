//package ru.k2.bots.telegram.service.handlers
//
//import org.springframework.stereotype.Service
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage
//import org.telegram.telegrambots.meta.api.objects.Update
//import ru.k2.bots.telegram.service.CheatSheetJavaCollectionService
//
//@Service
//class MessageHandler(private val collectionService: CheatSheetJavaCollectionService): Handler {
//
//    override fun messageHandler(update: Update): SendMessage{
//        return when (update.message.text) {
//           "/collection" -> collectionService.start(update)
//           else -> failure(update)
//       }
//    }
//

////
////    fun error() {
////        val message = SendMessage()
////        message.chatId = properties.chatId
////        message.text = "Text"
////
////        execute(message)
////    }
//
////    fun failure(update: Update): SendMessage {
////        val message = SendMessage()
////        message.chatId = update.message.chatId.toString()
////        message.text = "Команда \"${update.message.text}\" не поддерживается"
////        message.replyToMessageId = update.message.messageId
////
////        return message
////    }
//}