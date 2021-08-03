package com.study.demo.controller;

import com.study.demo.domain.MailDto;
import com.study.demo.service.MailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Controller
public class MailController {

    private final MailService mailService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/mail")
    public String mailForm() {
        logger.info("mail form");
        return "mail";
    }

    @PostMapping("/mail")
    public void sendMail(MailDto mailDto) {
        logger.info("to : {}, title : {}, message : {}",
                mailDto.getTo(), mailDto.getTitle(), mailDto.getMessage());

        mailService.send(mailDto);
    }
}
