package com.example.complaintsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Emailservice {

    @Autowired
    JavaMailSender mailSender;//it is a spring interface used to send the mail to gmail by communicating with smtp

    public void sendmail(String to,String subject,String body){

        SimpleMailMessage message = new SimpleMailMessage();//it is a spring class that holds email contents,lika container
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);//using spring interface send the mail to user
    }
}
