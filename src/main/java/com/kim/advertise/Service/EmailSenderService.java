/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kim.advertise.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.kim.advertise.Repository.ConfirmationTokenRepository;
import com.kim.advertise.entity.ConfirmationToken;
import com.kim.advertise.entity.User;
 
 
@Service("emailSenderService")
public class EmailSenderService {

    private JavaMailSender javaMailSender;
    @Autowired
    ConfirmationTokenRepository  confirmationRepository;
    
    private  String url_domain="http://localhost:4200";
    
    private  Logger log=LoggerFactory.getLogger(this.getClass());
    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }
    public void sendNewActivationCode( User user) {
        ConfirmationToken  confirmationToken=new ConfirmationToken(user);
        confirmationRepository.save(confirmationToken);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Activate Account  !");
        mailMessage.setFrom("dawa.age2020@gmail.com");
        mailMessage.setText( this.getAccountActivateMessage(confirmationToken.getConfirmationToken(), user.getEmail()));

        this.sendEmail(mailMessage);
    }
    public void passwordRecover(ConfirmationToken ct, User user) {
         
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Forgot Password!");
        mailMessage.setFrom("dawa.age2020@gmail.com");
        mailMessage.setText( this.getMessageForgotPassword(ct.getConfirmationToken(), user.getUsername()));
          log.info("kemal Password Reset Email Sent !!!! ");

        this.sendEmail(mailMessage);
    }
    
    public String getMessageForgotPassword(String token_string, String user_n) {
        String message = "Dear " + user_n + "\n"
                + "You have asked to reset your password becouse you forgot it . please click link below to  \n"
                + "reset password for your account : \n"
                + url_domain + "/newpassword/" + token_string;

                  return message;
    }
    public String getAccountActivateMessage(String token_string, String user_n) {
        String message = "Dear " + user_n + "\n"
                + "In order to Activate your account please click link below \n"
                + url_domain + "/activate/" + token_string;

                  return message;
    }
}
