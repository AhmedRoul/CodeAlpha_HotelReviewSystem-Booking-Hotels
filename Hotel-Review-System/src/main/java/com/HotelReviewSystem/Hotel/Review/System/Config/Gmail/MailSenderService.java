package com.HotelReviewSystem.Hotel.Review.System.Config.Gmail;

public class MailSenderService {
    /**
     //
     // Source code recreated from a .class file by IntelliJ IDEA
     // (powered by FernFlower decompiler)
     //

     package Config.Gmail;

     import Entitys.User;
     import org.springframework.beans.factory.annotation.Autowired;
     import org.springframework.mail.SimpleMailMessage;
     import org.springframework.mail.javamail.JavaMailSender;
     import org.springframework.stereotype.Service;

     @Service
     public class MailSenderService {
     @Autowired
     private JavaMailSender mailSender;

     public MailSenderService() {
     }

     public void MailSender(User user, String Code) {
     String Body = this.BodyConfirmEmail(user, Code);
     this.sendNewMail(user.getEmail(), "Confirm Email FlexBook", Body);
     }

     private String BodyConfirmEmail(User user, String Code) {
     return String.format("** FlexBook Verification Code\n------------------------------------------------------------\n\nHello %s,\nDear FlexBook User,\n\nWe received a message requesting access to your Flex Account (%s) using your email address.\nThe verification code is: %s\n\nIf you don't request this code, it's possible that someone else is trying to access your Flexbook Account (%s). Please be careful not to forward this message or give the code to anyone.\n\nSincerely,\n\nFlex Accounts Team", user.getFirstName(), user.getEmail(), Code, user.getEmail());
     }

     private void sendNewMail(String to, String Subject, String Body) {
     SimpleMailMessage message = new SimpleMailMessage();
     message.setTo(to);
     message.setSubject(Subject);
     message.setText(Body);
     this.mailSender.send(message);
     }
     }

      * **/
}
