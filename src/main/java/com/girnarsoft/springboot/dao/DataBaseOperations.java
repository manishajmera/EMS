package com.girnarsoft.springboot.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
/**
 * connect to service and reflect changes when service passes all the elements is true
 * @author gspl
 *
 */
@Repository
public class DataBaseOperations {
	//creating instance of jdbc template to reflect changes in database
	@Autowired
	private JdbcTemplate jdbc;
	/**
	 * jdbc connector setter
	 * @param jdbc
	 */
	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	/**
	 * verfy password with the database
	 * @param empId
	 * @param password
	 * @return
	 */
	public List verifyPassword(int empId, String password) {
		//sql query
		String sql = "select name,empId,password from emp where empId = "+empId + "&& password = '" +password + "'&&status=1";
		//list return by database
		List<Map<String, Object>> list = jdbc.queryForList(sql);
		System.out.println(list);
		return list;
	}
	/**
	 * get supervisor designation id by his id
	 * @param supervisorId
	 * @return
	 */
	public List getSupervisorRoleId(int supervisorId)
	{
		String sql="select roleId from emp where empId="+supervisorId +"&& status=1";
		List<Map<String, Object>> list = jdbc.queryForList(sql);
		return list;

	}
	/**
	 * get all the designation name
	 * @return
	 */
	public List getRoleName()
	{
		String sql="select roleName from role where roleName!='CEO'";
		List<Map<String, Object>> list = jdbc.queryForList(sql);
		return list;
		
	}
	/**
	 * 
	 * @param empId
	 * @param name
	 * @param password
	 * @param roleName
	 * @param supervisorId
	 * @param salary
	 */
	public void updateEmp(int empId,String name,String password,String roleName,int supervisorId,int salary)
	{
		int roleId = getroleId(roleName);
		String sql = "update emp set name = '" + name+ "', password = '" + password +"', roleId = " + roleId + " ,supervisedBy = "+supervisorId+" "
				+ ",salary = " + salary + " where empId = "+empId;
		System.out.println(sql);
		
		jdbc.execute(sql);
		
	}
	/**
	 * 
	 * @param roleName
	 * @return
	 */
	public int getroleId(String roleName)
	{
			String sql="select roleId from role where roleName='"+roleName+"';";
			List<Map<String, Object>> list = jdbc.queryForList(sql);

			return Integer.parseInt(list.get(0).get("roleId").toString());
 
	}
	/**
	 * 
	 * @param empId
	 * @return
	 */
	public String getDesignation(int empId)
	{
		String sql = "select roleName from role inner join emp on emp.roleId = role.roleId where empId = " +empId + "&& status=1;";
		List<Map<String, Object>> list = jdbc.queryForList(sql);
		return list.get(0).get("roleName").toString();
	}
	/**
	 * 
	 * @param name
	 * @param password
	 * @param roleName
	 * @param supervisorId
	 * @param salary
	 */
	public void insertEmp(String name,String password,String roleName,int supervisorId,int salary)
	{
		int roleId = getroleId(roleName);
		String sql = "insert into emp(name,password,roleId,supervisedBy,salary) values('"+name+"','"
				   +password+"',"+roleId+","+supervisorId+","+salary+");";
		jdbc.execute(sql);
		
	}
	/**
	 * 
	 * @param id
	 */
	public void deleteEmployee(int id)
	{
		String sql = "update emp set status = 0 where empId = " + id;
		String sql2 = "update emp set supervisedBy = 0 where supervisedBY=" + id;
		jdbc.batchUpdate(sql,sql2);
	}
	/**
	 * 
	 * @return
	 */
	public List getEmployeeList()
	{
		String sql = "select empId,name,password,supervisedBy,emp.roleId,roleName,salary from emp inner join role on emp.roleId = role.roleId where emp.status=1;";
		List<Map<String, Object>> list = jdbc.queryForList(sql);
		return list;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List getDetails(int id)
	{
		String sql = "select empId,name,supervisedBy,emp.roleId,roleName,password,salary from emp inner join role on emp.roleId = role.roleId where emp.status=1&&emp.empId="+id+"";
		List<Map<String, Object>> list = jdbc.queryForList(sql);
		return list;
		
	}

	/**
	 * 
	 * @param empId
	 * @return
	 */
	public List getTeam(int empId) {
		System.out.println(empId);
		String sql = "select empId , name ,roleName,emp.roleId from emp inner join role on emp.roleId = role.roleId "
				+ "where supervisedBy =" +empId;
		System.out.println(sql);
		List<Map<String, Object>> list = jdbc.queryForList(sql);
		return list;
	}
	/**
	 * 
	 * @param empId
	 * @param roleId
	 */
	public void promote(int empId,int roleId) {
		String sql2 = "update emp set supervisedBy = 0 where supervisedBY=" + empId;

		String sql = "update emp set roleId= "+ ++roleId + " where empId = " + empId + ";";
		jdbc.batchUpdate(sql,sql2);
	}

	
}
