package com.example.vma_java_project.email;

import com.example.vma_java_project.repository.UserRepository;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

  private static final Logger logger = LoggerFactory.getLogger(MailController.class);
  @Autowired
  MailService mailService;
  @Autowired
  UserRepository userRepository;

  @PostMapping("/email")
  @Scheduled(cron = "0 0 19 15 * ? *")
  public void sendMail() {
    ArrayList<String> usernames = userRepository.findAllUsername();
    ArrayList<String> emails = userRepository.findAllEmail();
    mailService.sendMail(usernames, emails);
  }
}
