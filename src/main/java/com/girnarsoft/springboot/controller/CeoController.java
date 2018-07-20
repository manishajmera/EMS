package com.girnarsoft.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.girnarsoft.springboot.services.Services;

/**
 * main controller class of ceo and hr where they do lot of actions like edit
 * employee profile,delete,and promote
 * 
 * @author manish
 *
 */

@Controller
public class CeoController {
	// service class instance to connect to database if users enters valid input
	@Autowired
	private Services services;
	// checker to check user enter a valid input
	@Autowired
	private Checkers checkers;

	/**
	 * add employee by input his details by the user
	 * @param session
	 * @param name
	 * @param password
	 * @param roleName
	 * @param supervisorId
	 * @param salary
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String insertEmployee(@RequestParam("session_id") String session, @RequestParam("name1") String name,
			@RequestParam("password") String password, @RequestParam("roleName") String roleName,
			@RequestParam("supervisorid") String supervisorId, @RequestParam("salary") String salary, Model model) {
		
		//set employee list to the jsp page
		model.addAttribute("employees", services.showEmployee());
		//roles of all the user who logged in jsp page
		model.addAttribute("roles", services.getRoleName());
		//set designation of user who logged in the jsp page
		model.addAttribute("designation", session);
		//validate name to be of right format without number and special character
		if (!checkers.validateString(name)) {
			//add error msg on jsp page
			model.addAttribute("errormsg", "Enter name to be in right format");
			return "ceo";

		}
		//validate supervisor id to be integer
		if (!checkers.validateInt(supervisorId)) {
			//add error msg on jsp page
			model.addAttribute("errormsg", "Enter supervisorId to be int and in range of 10 digits ");
			return "ceo";
		}
		//validate salary to be integer

		if (!checkers.validateInt(salary)) {
			//add error msg on jsp page

			model.addAttribute("errormsg", "Enter salary to be in int and in range of 10 digits");
			return "ceo";

		}

		else {
			//check of supervisor of employee should be at higher post or same post ;
			if (!services.addEmployee(name, password, roleName, Integer.parseInt(supervisorId),
					Integer.parseInt(salary))) {
				model.addAttribute("errormsg", "Can't assign this to be a supervisor");
				return "ceo";
			//else when all the fields are right then add employee successfully
			} else {
				model.addAttribute("success_msg", "Employee added successfully");
				model.addAttribute("employees", services.showEmployee());
				return "ceo";
			}
		}
	}
	/**
	 * update employee details by editing his details by the user
	 * @param session
	 * @param id
	 * @param name
	 * @param password
	 * @param roleName
	 * @param supervisorId
	 * @param salary
	 * @param model
	 * @return
	 */
	@RequestMapping("/update")
	public String updateEmployee(@RequestParam("session_id") String session, @RequestParam("id") String id,
			@RequestParam("name1") String name, @RequestParam("password") String password,
			@RequestParam("roleName") String roleName, @RequestParam("supervisorid") String supervisorId,
			@RequestParam("salary") String salary, Model model) {
		model.addAttribute("employees", services.showEmployee());
		model.addAttribute("roles", services.getRoleName());
		model.addAttribute("designation", session);
		if (!checkers.validateString(name)) {
			model.addAttribute("errormsg", "Enter name to be in right format");
			return "ceo";

		}
		if (!checkers.validateInt(supervisorId)) {
			model.addAttribute("errormsg", "Enter supervisorId to be int and in range of 10 digits ");
			return "ceo";
		}
		if (!checkers.validateInt(salary)) {
			model.addAttribute("errormsg", "Enter salary to be in int and in range of 10 digits");
			return "ceo";

		}

		else {
			if (!services.updateEmp(Integer.parseInt(id), name, password, roleName, Integer.parseInt(supervisorId),
					Integer.parseInt(salary))) {
				model.addAttribute("errormsg", "Can't assign this to be a supervisor");
				return "ceo";

			} else {
				
				model.addAttribute("success_msg", "Employee Updated successfully");
				model.addAttribute("employees", services.showEmployee());
				return "ceo";
			}

		}
	}

	@RequestMapping(value = "/delete")
	public String delete(@RequestParam("session_id") String session, @RequestParam("id") String id, Model model) {

		System.out.println(id);
		services.deleteEmployee(Integer.parseInt(id));
		model.addAttribute("employees", services.showEmployee());
		model.addAttribute("roles", services.getRoleName());
		model.addAttribute("designation", session);

		return "ceo";
	}

	@RequestMapping("/promote")
	public String promote(@RequestParam("session_id") String session, @RequestParam("id") String id,
			@RequestParam("roleId") String roleId, Model model) {
		services.promote(Integer.parseInt(id), Integer.parseInt(roleId));
		model.addAttribute("employees", services.showEmployee());
		model.addAttribute("roles", services.getRoleName());
		model.addAttribute("designation", session);
		return "ceo";
	}

	@RequestMapping("/changeSupervisor")
	public String changeSuperviosr(@RequestParam("session_id") String session, @RequestParam("id") String id,
			Model model) {

		model.addAttribute("employees", services.showEmployee());
		model.addAttribute("roles", services.getRoleName());
		model.addAttribute("designation", session);

		return "ceo";
	}

}
