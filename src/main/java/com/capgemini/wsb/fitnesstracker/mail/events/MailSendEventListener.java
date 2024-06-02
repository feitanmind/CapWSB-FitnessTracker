package com.capgemini.wsb.fitnesstracker.mail.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MailSendEventListener implements ApplicationListener<MailSendEvent> {
    @Override
    public void onApplicationEvent(MailSendEvent event) {
        System.out.println("Mail send event: " + event.getMessage());
    }
}
