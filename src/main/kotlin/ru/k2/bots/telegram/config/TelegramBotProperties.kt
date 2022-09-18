package ru.k2.bots.telegram.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "general-bot.settings")
class TelegramBotProperties(
        val name: String,
        val token: String,
        val chatId: String
)