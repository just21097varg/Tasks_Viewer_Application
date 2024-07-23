package com.firstproject.firstproject.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public String otp;
	
	public String sendEmail(String email) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("just21097varg@gmail.com");
		message.setTo(email);
		message.setSubject("Test mail");
		message.setText("This is the test mail set successfully. Use following OTP to verify your identity "+generateOTP());
		
		
		mailSender.send(message);
		
		return this.otp;
		
	}
	
	private String generateOTP() {
		
		String otp=String.valueOf((Math.floor(100000 + Math.random() * 900000))).substring(0, 6);
		this.otp = otp;
		return otp;
	}

}
