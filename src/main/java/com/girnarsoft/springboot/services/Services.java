package com.girnarsoft.springboot.services;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.girnarsoft.springboot.dao.DataBaseOperations;
/**
 * service class to check all the inputs should be valid and can be reflected in database
 * else and reflected in database
 * @author manish
 *
 */
@Component
public class Services {
	//instance of database class to reflect updation in database
	@Autowired
	private DataBaseOperations db ;
	/**
	 * check user enters all the parameters in correct format and can reflected in the database
	 * @param name
	 * @param password
	 * @param roleName
	 * @param supervisedBy
	 * @param salary
	 * @return
	 */
	public boolean addEmployee(String name,String password,String roleName,int supervisedBy,int salary)
	{
		//list check if supervisor exit in database or not
		List < Map <String,Object>> li= db.getSupervisorRoleId(supervisedBy);
		if(li.isEmpty())
			return false;
		
		int roleIdSupervisor=Integer.parseInt(li.get(0).get("roleId").toString());
		int roleIdEmp = db.getroleId(roleName);
		//list check if we assign this to b supervisor of new employee or not
		if(roleIdSupervisor< roleIdEmp)
			return false;
		else {
			//insert into database
	 	db.insertEmp(name, password, roleName, supervisedBy, salary);
	 	return true;
		}
	}
	/**
	 * updation of employee by checking all his details
	 * @param id
	 * @param name
	 * @param password
	 * @param roleName
	 * @param supervisedBy
	 * @param salary
	 * @return
	 */
	public boolean updateEmp(int id,String name,String password,String roleName,int supervisedBy,int salary)
	{
		//list check if supervisor exit in database or not

		List < Map <String,Object>> li= db.getSupervisorRoleId(supervisedBy);
		if(li.isEmpty())
			return false;
		int roleIdSupervisor=Integer.parseInt(li.get(0).get("roleId").toString());
		int roleIdEmp = db.getroleId(roleName);
		//list check if we assign this to b supervisor of new employee or not

		if(roleIdSupervisor< roleIdEmp)
			return false;
		else {
			//insert into database

			db.updateEmp(id,name, password, roleName, supervisedBy, salary);
	 	return true;
		}
	}
	/**
	 * authenticate user through database that user exit or not
	 * @param id
	 * @param password
	 * @return
	 */
	public boolean authenticate(int id,String password)
	{
		//result from database
		List li  = db.verifyPassword(id, password);
		//if result from database is not empty return true
		if(!li.isEmpty())
			return true;
		else
			return false;
	}
	/**
	 * get desigantion of any employee through his id
	 * @param id
	 * @return
	 */
	public String getDesignation(int id)
	{
		return db.getDesignation(id);
	}
	/**
	 * delete employee by enter id
	 * @param id
	 */
	public void deleteEmployee(int id)
	{
		db.deleteEmployee(id);
	}
	/**
	 * return all the employee which are currently present in company
	 * @return
	 */
	public List showEmployee()
	{
		System.out.println(db.getEmployeeList());
		return db.getEmployeeList(); 
	}
	/**
	 * promote employee through his id
	 * @param empId
	 * @param roleId
	 */
	public boolean promote(int empId,int roleId)
	{
		if(roleId==5)
			return false;
		else {
			db.promote(empId,roleId);
			return true;
		}

	}
	/**
	 * details of any particular employee through his id
	 * @param id
	 * @return
	 */
	public List getDetails(int id)
	{
		return db.getDetails(id);
	}
	/**
	 * team of any director or manager
	 * @param id
	 * @return
	 */
	public List getTeam(int id)
	{
		return db.getTeam(id);
	}
	/**
	 * get role name through role table by inner join with emp table with roleId
	 * @return
	 */
	public List getRoleName()
	{
		return db.getRoleName();
	}
	
}
