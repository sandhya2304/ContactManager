package com.smart.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.Helper.Message;
import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;


@Controller
public class HomeController
{
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@RequestMapping("/")
	public String home(Model model){
		model.addAttribute("title","Home - Contact manager");
		return "home";
	}
	
	@RequestMapping("/about")
	public String about(Model model){
		model.addAttribute("title","About - Contact manager");
		return "about";
	}
	
	
	@RequestMapping("/signup")
	public String signUp(Model model){
		model.addAttribute("title","Register - Contact manager");
		model.addAttribute("user",new User());
		return "signup";
	}
	
	@RequestMapping(value="/do_register", method= RequestMethod.POST)		      
	public String registerUser( @ModelAttribute ("user") User user,
		                       @RequestParam(value="agreement",defaultValue = "false") boolean agreement,
		                       Model model,HttpSession session)
	{
		
		try
		{
			
			if(!agreement)
			{
				System.out.println("You have not agreed the terms and conditions !!");
				throw new Exception("You have not agreed the terms and conditions !!!!!!");
			}
			
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			
			
			
			System.out.println("agreement" +agreement);
			System.out.println("USER  "+user);
			
			User result = this.userRepository.save(user);
			
			model.addAttribute("user",new User());
			
            session.setAttribute("message",new Message("Successfully Registred", "alert-success"));
			
			return "signup";
			
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message",new Message("Something Went Wrong!!"+e.getMessage(), "alert-danger"));
			
			return "signup";
		}
		
		
	}
	
}
