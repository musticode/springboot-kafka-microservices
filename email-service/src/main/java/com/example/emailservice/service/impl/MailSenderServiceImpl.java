package com.example.emailservice.service.impl;

import com.example.basedomains.payload.OrderEvent;
import com.example.emailservice.dto.EmailDetails;
import com.example.emailservice.kafka.OrderConsumer;
import com.example.emailservice.service.MailSenderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    @Value("${spring.mail.username}")
    private String sender;

    private final JavaMailSender javaMailSender;
    private final OrderConsumer orderConsumer;

    public MailSenderServiceImpl(JavaMailSender javaMailSender, OrderConsumer orderConsumer) {
        this.javaMailSender = javaMailSender;
        this.orderConsumer = orderConsumer;
    }

/*    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )*/
    public void sendMailToUser(OrderEvent orderEvent){

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo("testrecipient@mail.com");
            mailMessage.setText(orderEvent.toString());
            mailMessage.setSubject("Test Subject");

            javaMailSender.send(mailMessage);
        }catch (Exception e){
            e.printStackTrace();
        }

    }




    @Override
    public String sendEmail(EmailDetails emailDetails) {

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(emailDetails.getRecipient());
            mailMessage.setText(emailDetails.getMsgBody());
            mailMessage.setSubject(emailDetails.getSubject());

            javaMailSender.send(mailMessage);

            return "Mail send";

        }catch (Exception e){

            e.printStackTrace();
            return "Something went wrong";
        }

    }
}
