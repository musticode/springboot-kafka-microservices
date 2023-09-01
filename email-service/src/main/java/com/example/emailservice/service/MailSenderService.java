package com.example.emailservice.service;

import com.example.emailservice.dto.EmailDetails;

public interface MailSenderService {
    String sendEmail(EmailDetails emailDetails);
}
