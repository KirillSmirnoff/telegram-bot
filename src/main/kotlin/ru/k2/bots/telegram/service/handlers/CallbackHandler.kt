//package ru.k2.bots.telegram.service.handlers
//
//import org.springframework.stereotype.Service
//import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage
//import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText
//import org.telegram.telegrambots.meta.api.objects.Update
//import ru.k2.bots.telegram.service.CheatSheetJavaCollectionService
//
//@Service
//class CallbackHandler(private val collectionService: CheatSheetJavaCollectionService) { //: Handler {
//
//    fun handle(update: Update): EditMessageText {
//       return when (update.callbackQuery.data){
//            "/value" -> collectionService.value(update)
//            "/map" -> collectionService.map(update)
////            "map/noOrder" -> collectionService.mapNoOrder(update)
//           else -> EditMessageText() //todo продумать как отбработать
//       }
//
////        val message = SendMessage()
////        message.chatId = update.callbackQuery.message.chatId.toString()
////        message.text = "Команда \"${update.callbackQuery.data}\" не поддерживается"
//////        message.replyToMessageId = update.message.messageId
//////        message.replyMarkup = getInlineKeyboard()
////
////        return message
//    }
//
//
//}