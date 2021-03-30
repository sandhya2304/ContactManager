package com.smart.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForgotController
{
	
	Random rand  = new Random(1000);
	
	@RequestMapping(value="/forgot")
	public String openEmailForm(){
		
		return "forgotemailForm";
	}
	
	
	
	@PostMapping(value="/sendOTP")
	public String sendotp(@RequestParam("email")String email,HttpSession session){
		
		System.out.println("email .."+email);
		
		
		//generationg otp 4 digit
		
		
		int otp = rand.nextInt(9999999);
		
		System.out.println(otp);
		
		//write code for send otp to email
		
		
		
		
		
		
		
		return "verifyotp";
	}

}
