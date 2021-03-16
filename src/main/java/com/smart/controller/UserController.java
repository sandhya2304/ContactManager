package com.smart.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.smart.Helper.Message;
import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;

@Controller
@RequestMapping(value="/user")
public class UserController
{
	@Autowired
	private UserRepository userRepository;
	
	
	//method for adding common data to response
	
	@ModelAttribute
	public void addCommonData(Model model,Principal principal)
	{
		String userName = principal.getName();
		System.out.println(userName);
		
		User user = this.userRepository.getUserByUsername(userName);
		System.out.println("USER" +user);
		
		model.addAttribute("user",user);
	}
	
	
	//dashboard home
	@RequestMapping(value="/index")
	public String dashboard(Model model,Principal principal)
	{
		
		model.addAttribute("title","Home");
	    return "normal/user_dashboard";	
	}
	
	//open hand form handler
	
	
	@GetMapping("/add-contact")
	public String openAddContactForm(Model model)
	{
		model.addAttribute("title","Add Contact");
		model.addAttribute("contact", new Contact());
		
		return "normal/add_contact_form";
	}
	
	//processing addcontact form
	@PostMapping("/process-contact")
	public String processContact(@ModelAttribute Contact contact ,
			                    @RequestParam("profile")MultipartFile file, 
			                    Principal principal,HttpSession session)  
	{
		
		try{
		
		String name = principal.getName();
		User user = this.userRepository.getUserByUsername(name);
		
		
		if(file.isEmpty())
		{
			System.out.println("File is empty...");
		}else{
			
			//upload the file to folder and update the name in contacts
			
			contact.setcImageURL(file.getOriginalFilename());
			
			File saveFile = new ClassPathResource("/static/image").getFile();
			
			Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			
			System.out.println("Image is uploaded");
		}
		
		
		//here we set user in contact
		contact.setUser(user);
		
		user.getContacts().add(contact);
		this.userRepository.save(user);
		
		//dry run
		System.out.println("data....."+contact);
		
		System.out.println("Added to data");
		
		//msg
		session.setAttribute("msg",new Message("Your contact added now", "success"));
	
		
		
		}catch(Exception e)
		{
			e.printStackTrace();
			
			//msg
			
			session.setAttribute("msg",new Message("Your not contact Try Again !!!", "danger"));
		}
		
		
		return "normal/add_contact_form";
	}
	
	
	
	
	
	
	
	

}
