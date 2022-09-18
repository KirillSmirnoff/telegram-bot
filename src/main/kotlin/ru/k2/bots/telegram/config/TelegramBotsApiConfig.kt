package ru.k2.bots.telegram.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import ru.k2.bots.telegram.bot.GeneralBot

@Configuration
@EnableConfigurationProperties(TelegramBotProperties::class)
class TelegramBotsApiConfig(private val generalBot: GeneralBot) {

    @Bean
    fun telegramBotsApi(): TelegramBotsApi {
        val telegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
        telegramBotsApi.registerBot(generalBot)

        return telegramBotsApi
    }
}