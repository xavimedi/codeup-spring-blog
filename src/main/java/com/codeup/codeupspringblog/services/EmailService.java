package com.codeup.codeupspringblog.services;

import com.codeup.codeupspringblog.models.Ad;
//import com.codeup.codeupspringblog.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {
	@Autowired
	public JavaMailSender emailSender;

	@Value("${spring.mail.from}")
	private String from;

	public void sendAnAdEmail(Ad ad, String subject, String body){
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(from);
		msg.setTo(ad.getAdUser().getEmail());
		msg.setSubject(subject);
		msg.setText(body);

		try{
			this.emailSender.send(msg);
		}
		catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}

//	public void sendAPostEmail(Post post, String subject, String body){
//		SimpleMailMessage msg = new SimpleMailMessage();
//		msg.setFrom(from);
//		msg.setTo(post.getPostUser().getEmail());
//		msg.setSubject(subject);
//		msg.setText(body);
//
//		try{
//			this.emailSender.send(msg);
//		}
//		catch (MailException ex) {
//			System.err.println(ex.getMessage());
//		}
//	}
}
