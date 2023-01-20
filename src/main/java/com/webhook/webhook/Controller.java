package com.webhook.webhook;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class Controller {
    @Value("${spring.mail.from}")
    private String from;

    @Value("${spring.mail.to}")
    private String to;

    @Value("${spring.mail.response}")
    private String msg;

    @Autowired
    private JavaMailSender mailSender;

    private static final Logger logger =
            LoggerFactory.getLogger(Controller.class);
    @PostMapping(value = "/send-mail", produces = "application/xml")
    @ResponseBody
    public String handleSmsWebhook(@RequestParam("From") String subject,
                                   @RequestParam("Body") String body, HttpServletRequest request,
                                   HttpServletResponse response) {
        try {

            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(from);
            message.setTo(to);
            message.setText(body);
            message.setSubject(subject);
            mailSender.send(message);

            return new MessagingResponse.Builder()
                    .message(new Message.Builder(msg).build())
                    .build().toXml();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            logger.error("Exception while sending sms to  " + e);
        }
        return subject;
    }
}
