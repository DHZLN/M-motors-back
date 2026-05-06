package com.m_motors.mmotors.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void send(String to, String subject, String content) {
        System.out.println("===== EMAIL =====");
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Content: " + content);
        System.out.println("=================");
    }
}