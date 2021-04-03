package com.smart.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.service.EmailService;

@Controller
public class ForgotController
{
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	UserRepository userRepository;
	
	
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
		String message  = ""
				+"<div style='border:1px solid grey; padding:20px; '>"
				+"<h1>"
				+ "OTP is"
				+"<b>" +otp
				+"</b>"
				+ "</h1> "
				+"</div>";
				
		String to = email;
		
		boolean flag = this.emailService.sendEmail(subject, message, to);
		
		if(flag)
		{
			
			session.setAttribute("myotp", otp);
			session.setAttribute("email",email);
			
			return "verifyotp";
			
		}else{
			
			session.setAttribute("msg","checked your email ID");
			
			return "forgotemailForm";
		}		
	}
	
	
	@PostMapping(value="verify-otp")
	public String verifyOTP(@RequestParam("otp") int otp,HttpSession session)
	{
		
		int myotp = (Integer) session.getAttribute("myotp");
		String email = (String) session.getAttribute("email");
		
		if(myotp == otp)
		{
			
			User user = this.userRepository.getUserByUsername(email);
			if(user == null)
			{
				//error msg
				session.setAttribute("msg","User doent exist with this email ID");
				
				return "forgotemailForm";
				
			}else{
				
				//password change form 
				
				
			}
			
			return "changePassword";
		}else{
			
			session.setAttribute("msg", "Wrong OTP entered!!");
			return "verifyotp";
		}
		
		
	}
	
	
	
	

}
