package com.ycar.reservation.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailSenderService")
public class MailSenderService {
	
	@Autowired
	private JavaMailSender sender;
	
	public void send(String getEmail, String code) {
		
		MimeMessage message = sender.createMimeMessage();
		
		try {
			message.setSubject("[YCAR]연차에서 새로운 카풀 요청이 왔습니다.", "UTF-8");
			String htmlMsg = "<h1>새로운 카풀 요청이 도착했습니다!</h1>";
			
			htmlMsg += "<h3></h3>";
			htmlMsg += "<h3><a href=>수락하기</a><a href=>거절하기</a></h3>";

			message.setText(htmlMsg, "UTF-8", "html");
			message.setFrom(new InternetAddress("emailacc"));
			message.addRecipient(RecipientType.TO, new InternetAddress(getEmail, "emailacc", "UTF-8"));
			
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	
	//재요청 할 때 
//	public void reSend(String getId, String code) {
//		
//		MimeMessage message = sender.createMimeMessage();
//		
//		try {
//			message.setSubject("[YCAR]연차에서 새로운 카풀 요청이 들어왔습니다.", "UTF-8");
//			String htmlMsg = "<h1>이메일 인증을 해주세요.</h1>";
//			
//			htmlMsg += "<h3>인증을 위해 아래 링크를 클릭해주세요</h3>";
//			htmlMsg += "<h3><a href=>인증하기</a></h3>";
//
//			message.setText(htmlMsg, "UTF-8", "html");
//			message.setFrom(new InternetAddress("emailacc"));
//			message.addRecipient(RecipientType.TO, new InternetAddress(getId, "emailacc", "UTF-8"));
//			
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//	}
}
