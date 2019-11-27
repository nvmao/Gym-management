package com.mao;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {

    public static void sendMail(String recepient) {
        System.out.println("Preparing message");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String myEmail = "newman33399@gmail.com";
        String password = "rtyfghvb12";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail,password);
            }
        });

        Message message = prepareMessage(session,myEmail,recepient);
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("Send message successful");
    }

    private static Message prepareMessage(Session session,String email,String recepient){
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(email));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));

            message.setSubject("Please confirm your account");
            String htmlCode = String.format("<h1>Hello %s </h1> <br> <p> To confirm your account: <a href='http://youtube.com'> Click here</a> </p>",recepient);
            message.setContent(htmlCode,"text/html");

            return message;

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String arg[]){
        SendEmail.sendMail("newman33399@gmail.com");
    }

}
