package fr.treeptik.jpasample.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Employee implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String statut;
	
	private String firstName;
	
	private String lastName;
	
	private Integer Salary;
	
	private Date startDate;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Address address;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Department department;
	
	@ElementCollection(fetch=FetchType.LAZY)
	private Set<String> phoneNumbers;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Employee manager;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Project> projects;
	
	

	public Employee() {
	}
	
	
	
	
	
	

	public Employee( String statut, String firstName,
			String lastName, Integer salary, Date startDate,
			Address adresse, Department departement,
			Set<String> phoneNumbers,
			List<Project> project) {
		this.statut = statut;
		this.firstName = firstName;
		this.lastName = lastName;
		Salary = salary;
		this.startDate = startDate;
		this.address = adresse;
		this.department = departement;
		this.phoneNumbers = phoneNumbers;
		this.projects = project;
	}







	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getSalary() {
		return Salary;
	}

	public void setSalary(Integer salary) {
		Salary = salary;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Address getAdresse() {
		return address;
	}

	public void setAdresse(Address adresse) {
		this.address = adresse;
	}

	public Department getDepartement() {
		return department;
	}

	public void setDepartement(Department departement) {
		this.department = departement;
	}

	public List<String> getPhoneNumbers() {
		return new ArrayList<>(phoneNumbers);
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = new HashSet<>(phoneNumbers);
	}








	public Employee getManager() {
		return manager;
	}







	public void setManager(Employee manager) {
		this.manager = manager;
	}







	public List<Project> getProject() {
		return projects;
	}

	public void setProject(List<Project> project) {
		this.projects = project;
	}
	
	
	
	
	

}
