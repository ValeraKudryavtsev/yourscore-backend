package com.project.yourscore.Configs

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.*

@Configuration
class EmailConfig {
    @Value("\${spring.mail.host}")
    private val host: String? = null

    @Value("\${spring.mail.username}")
    private val username: String? = null

    @Value("\${spring.mail.password}")
    private val password: String? = null

    @Value("\${spring.mail.port}")
    private val port = 0

    @Value("\${spring.mail.protocol}")
    private val protocol: String? = null

    @Bean
    fun mailSender(): JavaMailSender {
        val mailSender = JavaMailSenderImpl()

        mailSender.host = host
        mailSender.port = port
        mailSender.username = username
        mailSender.password = password
        val properties: Properties = mailSender.javaMailProperties
        properties.setProperty("mail.transport.protocol", protocol)

        return mailSender
    }
}