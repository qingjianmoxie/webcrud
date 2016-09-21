package com.windy.webcrud.vo;

import java.util.Date;

import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

public class EmployeeVO {
	//测试git pull
	String eid;
	
	@NotEmpty
	String name ;

	
	@Digits(integer=3, fraction=0)
	Integer age;
	
	int gender ;
	
	@NumberFormat(pattern="##,###.#")
	Double salary;
	
	@DateTimeFormat(pattern="yyyy-mm-dd")
	Date birth;
	
	DepartmentVO department;

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public DepartmentVO getDepartment() {
		return department;
	}
	
	

 
 
	public void setDepartment(DepartmentVO department) {
		this.department = department;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "EmployeeVO [eid=" + eid + ", name=" + name + ", age=" + age + ", gender=" + gender + ", salary="
				+ salary + ", birth=" + birth + ", department=" + department + "]";
	}
	
	
	
}
