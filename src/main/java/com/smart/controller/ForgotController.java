package com.smart.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.service.EmailService;

@Controller
public class ForgotController
{
	
	@Autowired
	EmailService emailService;
	
	
	Random rand  = new Random(1000);
	
	@RequestMapping(value="/forgot")
	public String openEmailForm(){
		
		return "forgotemailForm";
	}
	
	
	
	@PostMapping(value="/sendOTP")
	public String sendotp(@RequestParam("email")String email,HttpSession session){
		
		System.out.println("email .."+email);
		
		
		//Generating otp 4 digit
		
		
		int otp = rand.nextInt(9999999);
		
		System.out.println(otp);
		
		//write code for send otp to email
		
		String subject = "OTP from MAnager";
		String message  = "OTP is = "+otp;
		String to = email;
		
		boolean flag = this.emailService.sendEmail(subject, message, to);
		
		if(flag)
		{
			session.setAttribute("otp", otp);
			return "verifyotp";
			
		}else{
			
			session.setAttribute("msg","checked your email ID");
			
			return "forgotemailForm";
		}

		
	}

}
