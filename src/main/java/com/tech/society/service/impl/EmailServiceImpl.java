package com.tech.society.service.impl;


import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Service
public class EmailServiceImpl {

    Logger logger = org.slf4j.LoggerFactory.getLogger(EmailServiceImpl.class);

    public void sendEmail(String to, String subject, String body) {
        String smtpserver = new String("smtp.gmail.com");
        Properties prop = new Properties();
        prop.put("mail.smtp.host", smtpserver);
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.socketFactory.fallback", "false");
        prop.put("mail.smtp.password", "Vaniprasad@1608");
        //Session session = Session.getInstance(prop);
        try {
            Session session = Session.getInstance(prop, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("shiva Dharmala", "Vaniprasad@1608");
                }
            });


            InternetAddress iaSender = new InternetAddress(
                    "shivaprasadreddy.dharmala@gmail.com", "Shiva Prasad Reddy Dharmala");

            MimeMessage mes = new MimeMessage(session);
            mes.setSender(iaSender);
            mes.setRecipients(MimeMessage.RecipientType.TO, "shivaprasadreddy.dharmala@gmail.com");
            mes.setSubject("New Registration");
            mes.setText(body);
            Transport.send(mes);
            logger.info("Mail sent");
        } catch (UnsupportedEncodingException | MessagingException e) {
            throw new RuntimeException(e);
        }
        logger.info("Method executed");

    }
}