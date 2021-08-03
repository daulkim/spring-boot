package com.study.demo.service;

import com.study.demo.domain.MailDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void send(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getTo());
        message.setFrom(from);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());
        logger.info("to : {}, from  : {}, title : {}, message : {}",
                message.getTo(), message.getFrom(), message.getSubject(), message.getText());
        mailSender.send(message);
    }
}
