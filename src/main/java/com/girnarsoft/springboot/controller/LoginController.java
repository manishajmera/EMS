package com.girnarsoft.springboot.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.girnarsoft.springboot.services.Services;

/**
 * first user is enter the login info in login page then he will redirect to
 * this controller page and authenticate user
 * 
 * @author manish
 *
 */
@Controller
public class LoginController implements ErrorController {
	// instance of servvice class which connects controller to database and verify
	@Autowired
	private Services service;
	// checker checks the user must be enetered a valid input
	@Autowired
	private Checkers checkers;

	/**
	 * set the home page to index
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	/**
	 * set a by default error page when try something wrong;
	 */
	@RequestMapping("/error")
	@Override
	public String getErrorPath() {
		return "errorPage";
	}

	/**
	 * verify the user and redirect it to its corresponding page
	 * 
	 * @param password
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/authenticate")
	public String Login(@RequestParam("password") String password, @RequestParam("id") String id, Model model) {
		// check that user enter the id within range and integer
		if (!checkers.validateInt(id)) {
			// set a error msg in frontend if user enters invalid id
			model.addAttribute("errormsg", "pls enter id to be integer or within range ");
			return "index";
		}
		// authenticate users with his id and password
		if (service.authenticate(Integer.parseInt(id), password)) {
			// if user is trainee redirect to trainee jsp
			if (service.getDesignation(Integer.parseInt(id)).equals("Trainee")) {
				model.addAttribute("empId", "123");
				System.out.println(service.getDetails(Integer.parseInt(id)));
				model.addAttribute("details", service.getDetails(Integer.parseInt(id)));
				model.addAttribute("designation", service.getDesignation(Integer.parseInt(id)));
				return "trainee";

			}
			// if user is manager and director redirect them to managerDirector jsp page
			else if (service.getDesignation(Integer.parseInt(id)).equals("Manager")
					|| service.getDesignation(Integer.parseInt(id)).equals("Director")) {
				model.addAttribute("team", service.getTeam(Integer.parseInt(id)));
				model.addAttribute("details", service.getDetails(Integer.parseInt(id)));

				model.addAttribute("designation", service.getDesignation(Integer.parseInt(id)));
				return "manageDirector";
			}
			// if user is Hr and ceo redirect to hr / ceo jsp page
			else {
				model.addAttribute("designation", service.getDesignation(Integer.parseInt(id)));
				model.addAttribute("employees", service.showEmployee());
				model.addAttribute("roles", service.getRoleName());
				return "ceo";
			}
		} else {
			model.addAttribute("errormsg", "enter correct details");
			return "index";
		}
	}

	// for logout return to homepage
	@RequestMapping(value = "/logout")
	public String Logout() {
			
		return "/";

	}

}
