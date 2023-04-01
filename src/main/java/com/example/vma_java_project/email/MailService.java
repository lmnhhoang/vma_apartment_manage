package com.example.vma_java_project.email;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailService {

  private static final String CONTENT_TYPE_TEXT_HTML = "text/html;charset=\"utf-8\"";
  @Autowired
  ThymeleafService thymeleafService;
  @Value("${config.mail.host}")
  private String host;
  @Value("${config.mail.port}")
  private String port;
  @Value("${config.mail.username}")
  private String email;
  @Value("${config.mail.password}")
  private String password;

  public void sendMail(ArrayList<String> usernames, ArrayList<String> emails) {
    Properties props = new Properties();
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", port);

    Session session = Session.getInstance(props,
        new Authenticator() {
          @Override
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(email, password);
          }
        });
    Message message = new MimeMessage(session);
    for (int i = 0; i < usernames.size(); i++) {
      try {
        message.setRecipients(RecipientType.TO,
            new InternetAddress[]{new InternetAddress(emails.get(i))});

        message.setFrom(new InternetAddress(email));
        message.setSubject("Reminder monthly payment - User " + usernames.get(i));
        message.setContent(thymeleafService.getContent(), CONTENT_TYPE_TEXT_HTML);
        Transport.send(message);
      } catch (MessagingException e) {
        e.printStackTrace();
      }
    }

  }
}
