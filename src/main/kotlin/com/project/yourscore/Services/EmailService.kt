package com.project.yourscore.Services

import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService (
    private val javaMailSender: JavaMailSender,
    @Value("\${spring.mail.username}")
    private val senderEmail: String,
) {
    fun sendEmail(receiver: String, subject: String, text: String) {
        val message = SimpleMailMessage()
        message.setFrom(senderEmail)
        message.setTo(receiver)
        message.setBcc(senderEmail)
        message.setSubject(subject)
        message.setText(text)
        javaMailSender.send(message)
    }
}