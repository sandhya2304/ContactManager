package com.smart.controller;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	private BCryptPasswordEncoder passwordEncode;
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	@RequestMapping("/signin")
	public String login(Model model){
		model.addAttribute("title","Login - Contact manager");
		return "login";
	}
	

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
	public String registerUser(@Valid @ModelAttribute ("user") User user,BindingResult results,
		                       @RequestParam(value="agreement",defaultValue = "false") boolean agreement,
		                       Model model, HttpSession session)
	{
	
		try
		{
			
			//if user doesnt click on agreement  exception thrown
			if(!agreement)
			{
				System.out.println("You have not agreed the terms and conditions !!");
				throw new Exception("You have not agreed the terms and conditions !!!!!!");
			}
			
			
			//hibernate errors
			if(results.hasErrors())
			{
				System.out.println("Error "+results.toString());
				model.addAttribute("user",user);
				return "signup";
			}
			
			
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("default.jpg");
			
			//password encrypted here
			user.setPassword(passwordEncode.encode(user.getPassword()));
			
			
			System.out.println("agreement" +agreement);
			System.out.println("USER  "+user);
			
			User result = this.userRepository.save(user);
			
			//user form fields empty
			//A model will be destroyed after processing an request
			model.addAttribute("user",new User());
			
			
			//data remain in session
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
