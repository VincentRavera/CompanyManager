package fr.treeptik.runtime;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.treeptik.jpasample.dto.Query19DTO;
import fr.treeptik.jpasample.model.Department;
import fr.treeptik.jpasample.model.Employee;
import fr.treeptik.jpasample.model.Project;


public class Runtime {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		EntityManager en = 
				Persistence.createEntityManagerFactory(
						"sample").createEntityManager();
		/*
		 * Q1
		 */
		System.out.println("Q1");
		TypedQuery<Employee> Q1 = en.createQuery(
				"SELECT e FROM Employee e",
				Employee.class);
		List<Employee> l1 = Q1.getResultList();
		for (Employee employee : l1) {
			System.out.println(employee.getId()+" = "+ employee.getLastName());
		}
		System.out.println("END: Q1");
		
		System.out.println("Q2");
		TypedQuery<Employee> Q2 = en.createQuery(
				"SELECT e FROM Employee e "
				+ "LEFT JOIN FETCH e.department",
				Employee.class);
		List<Employee> l2 = Q2.getResultList();
		for (Employee employee : l2) {
			System.out.println(employee.getLastName()
					+" work at "
					+ employee.getDepartement().getName());
			
		}
		System.out.println("END: Q2");
		
		
		System.out.println("Q3");
		TypedQuery<Employee> Q3 = en.createQuery(
				"SELECT e FROM Employee e "
				+ "JOIN e.projects",
				Employee.class);
		List<Employee> l3 = Q3.getResultList();
		for (Employee employee : l3) {
			System.out.println(employee.getLastName()
					+" work on "
					+ employee.getProject());
		}
		
		System.out.println("END: Q3");
		
		System.out.println("Q4");
		TypedQuery<Project> Q4 = en.createQuery(
				"SELECT p FROM Project p "
				+ "LEFT JOIN FETCH p.employees "
				+ "GROUP BY p",
				Project.class);
		List<Project> l4 = Q4.getResultList();
		for (Project p : l4) {
			System.out.println(p.getName()
					+" work on :");
			for (Employee employee : p.getEmployees()) {
				System.out.println("\t"+employee.getLastName());
				
			}
		}
		System.out.println(l4.size());
		System.out.println("END: Q4");
		
		System.out.println("Q5");
		TypedQuery<Employee> Q5 = en.createQuery(
				"SELECT e FROM Employee e "
				+ "JOIN e.projects p "
				+ "WHERE e.manager IS null "
				+ "AND p IN "
				+ "(SELECT p FROM Project p "
				+ "WHERE p.name = :pname)",
				Employee.class);
		Q5.setParameter("pname", "Cloudunit");
		List<Employee> l5 = Q5.getResultList();
		for (Employee p : l5) {
			System.out.println(p.getLastName());
		}
		System.out.println("END: Q5");
		
		
		System.out.println("Q6");
		TypedQuery<Department> Q6 = en.createQuery(
				"SELECT d FROM Department d "
				+ "ORDER BY d.name ",
				Department.class);
		List<Department> l6 = Q6.getResultList();
		for (Department d : l6) {
			System.out.println(d.getName());
		}
		System.out.println("END: Q6");
		
		
		System.out.println("Q7");
		TypedQuery<Employee> Q7 = en.createQuery(
				"SELECT e FROM Employee e "
				+ "ORDER BY e.Salary ",
				Employee.class);
		List<Employee> l7 = Q7.getResultList();
		for (Employee e : l7) {
			System.out.println(e.getLastName() + " has " + e.getSalary()+" euro");
		}
		System.out.println("END: Q7");
		
		
		System.out.println("Q8");
		List<Double> Q8 = new ArrayList<>();
		Q8 = en.createQuery(
				"SELECT AVG(e.Salary) FROM Employee e "
				+ "WHERE e.statut= 'Manager'").getResultList();
		for (Double d : Q8) {
			System.out.println("Salaire moyen est de "+d+" Eur");
		}
		System.out.println("END: Q8");
		
		
		System.out.println("Q9");
		TypedQuery<Employee> Q9 = en.createQuery(
				"SELECT e FROM Employee e "
				+ "WHERE e.manager IS null "
				+ "AND e.statut != 'Manager' "
				+ "ORDER BY e.Salary DESC",
				Employee.class);
		List<Employee> l9 = Q9.setMaxResults(1).getResultList();
		for (Employee p : l9) {
			System.out.println(p.getLastName());
		}
		System.out.println("END: Q9");
		
		
		System.out.println("Q10");
		TypedQuery<Employee> Q10 = en.createQuery(
				"SELECT e FROM Employee e "
				+ "ORDER BY e.Salary DESC",
				Employee.class);
		List<Employee> l10 = Q10.setMaxResults(2).getResultList();
		for (Employee p : l10) {
			System.out.println(p.getLastName());
		}
		System.out.println("END: Q10");
	
		System.out.println("Q11");
		Date now = new Date();
		TypedQuery<Employee> Q11 = en.createQuery(
				"SELECT e FROM Employee e "
				+ "WHERE (e.startDate BETWEEN '2013-04-13' AND :today )",
				Employee.class);
		Q11.setParameter("today", now);
		List<Employee> l11 = Q11.getResultList();
		for (Employee p : l11) {
			System.out.println(p.getLastName());
		}
		System.out.println("END: Q11");
		
		
		System.out.println("Q12");
		TypedQuery<Employee> Q12 = en.createQuery(
				"SELECT e FROM Employee e "
				+ "WHERE e.lastName LIKE 'G%' ",
				Employee.class);
		List<Employee> l12 = Q12.getResultList();
		for (Employee p : l12) {
			System.out.println(p.getLastName());
		}
		System.out.println("END: Q12");
		
		
		
		System.out.println("Q13");
		TypedQuery<Employee> Q13 = en.createQuery(
				"SELECT e FROM Employee e "
				+ "JOIN FETCH e.projects "
				+ "JOIN FETCH e.phoneNumbers ",
				Employee.class);
		List<Employee> l13 = Q13.getResultList();
		for (Employee p : l13) {
			System.out.println(p.getLastName()+" is in ");
			for (Project proj : p.getProject()) {
				System.out.println("\t \t"+proj.getName());
			}
			System.out.println("\t and owns ");
			for (String phone : p.getPhoneNumbers()) {
				System.out.println("\t \t"+phone);
			}
		}
		System.out.println("END: Q13");
		
		
		
		System.out.println("Q14");
		TypedQuery<Employee> Q14 = en.createQuery(
				"SELECT e FROM Employee e "
				+ "JOIN FETCH e.address a "
				+ "WHERE a IN "
				+ "(SELECT a FROM Address "
				+ "WHERE a.town = :town)",
				Employee.class);
		Q14.setParameter("town", "Detroit");
		List<Employee> l14 = Q14.getResultList();
		for (Employee p : l14) {
			System.out.println(p.getLastName());
		}
		System.out.println("END: Q14");
		
		
		System.out.println("Q15");
		TypedQuery<Long> Q15 = en.createQuery(
				"SELECT COUNT(e) FROM Employee e "
				+ "WHERE e.Salary < :maxSalary ",
				Long.class);
		Q15.setParameter("maxSalary", 30000);
		Long l15 = Q15.getSingleResult();
		System.out.println("Numbers of poor employee :"+l15);
		System.out.println("END: Q15");
		
		
		System.out.println("Q16");
		TypedQuery<Project> Q16 = en.createQuery(
				"SELECT p FROM Project p "
				+ "WHERE p NOT IN "
				+ "(SELECT p FROM Project p "
				+ "JOIN p.employees e "
				+ "WHERE e IN "
				+ "(SELECT e FROM Employee e "
				+ "WHERE e.firstName = :firstName "
				+ "AND e.lastName = :lastName )) ",
				Project.class);
		Q16.setParameter("lastName", "John");
		Q16.setParameter("firstName", "Harper");
		List<Project> l16 = Q16.getResultList();
		for (Project project : l16) {
			System.out.println("Project :" + project.getName());
		}
		System.out.println("END: Q16");
		
		
		System.out.println("Q17");
		TypedQuery<Employee> Q17 = en.createQuery(
				"SELECT DISTINCT e FROM Employee e "
				+ "JOIN e.phoneNumbers n "
				+ "WHERE e.statut != :stat "
				+ "ORDER BY e.Salary DESC ",
				Employee.class);
		Q17.setParameter("stat", "Manager");
		List<Employee> l17 = Q17.setMaxResults(3).getResultList();
		for (Employee e : l17) {
			System.out.println(e.getFirstName());
		}
		System.out.println("END: Q17");
		
		
		System.out.println("Q18");
		TypedQuery<Long> Q18 = en.createQuery(
				"SELECT COUNT(e) FROM Employee e "
				+ "JOIN e.address a "
				+ "WHERE a IN "
				+ "(SELECT a FROM Address a "
				+ "WHERE a.town = :town) ",
				Long.class);
		Q18.setParameter("town", "Detroit");
		Long l18 = Q18.getSingleResult();
		System.out.println("Detroit employee :"+l18);
		System.out.println("END: Q18");
		
		
		System.out.println("Q19");
		TypedQuery<Query19DTO> Q19 = en.createQuery(
				"SELECT new fr.treeptik.jpasample.dto.Query19DTO(e.lastName, e.firstName, m.lastName) FROM Employee e "
				+ "JOIN e.manager m",
				Query19DTO.class);
		List<Query19DTO> l19 = Q19.getResultList();
		for (Query19DTO q : l19) {
			System.out.println("Employee : " 
					+q.getEmployeeFName()
					+" "
					+q.getEmployeeLName()
					+" -- Managed by :"
					+q.getManagerLName());
			
		}
		System.out.println("END: Q19");
		
		
		
		
		
		System.out.println("Q20");
		Query Q20 = en.createNativeQuery(
				"Select AVG(test.salary) FROM "
				+ "(SELECT * FROM Employee "
				+ "ORDER BY salary DESC "
				+ "LIMIT 0,5) AS test");
		BigDecimal a = (BigDecimal) Q20.getSingleResult();
		System.out.println("Average Best Salary :"+a);
		System.out.println("END: Q20");
		
		
		
		System.out.println("Q21");
		SimpleDateFormat date0sample = new SimpleDateFormat("yy/MM/dd");
		Date date0 = null;
		Date date1 = null;
		try {
			date0 = date0sample.parse("13/01/01");
			date1 = date0sample.parse("13/12/07");
			
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		TypedQuery<Employee> Q21 = en.createQuery(
				"SELECT e FROM Employee e "
				+ "WHERE e.startDate BETWEEN "
				+ ":date0 AND :date1 ",
				Employee.class);
		Q21.setParameter("date0", date0);
		Q21.setParameter("date1", date1);
		System.out.println(date0.toString());
		System.out.println(date1.toString());
		List<Employee> l21 = Q21.setMaxResults(3).getResultList();
		for (Employee e : l21) {
			System.out.println(e.getLastName());
		}
		System.out.println("END: Q21");
		
		
		
		System.out.println("Q22");
		TypedQuery<Long> Q22 = en.createQuery(
				"SELECT COUNT(e) FROM Employee e "
				+ "WHERE e.id IN "
				+ "(SELECT e.id "
				+ "FROM Employee e "
				+ "JOIN e.projects "
				+ "GROUP BY e.id "
				+ "HAVING COUNT(e.id)>=2 ) "
				+ "AND e.statut = :stat ",
				Long.class);
		Q22.setParameter("stat", "Manager");
		Long l22 = Q22.getSingleResult();
		System.out.println("Manager over worked : " +l22);
		System.out.println("END: Q22");
		
		/*
		 * ICI LA QUESTION 23 !!
		 * (Elle est commenté car elle modidfie la DataBase
		 * Mais elle marche :)
		 */
//		
//		try {
//			TypedQuery<Employee> Q23 = en.createQuery(
//					"Select e from Employee e "
//					+ "ORDER BY e.Salary", Employee.class);
//			List<Employee> l23 = Q23.setMaxResults(2).getResultList();
//			en.getTransaction().begin();
//			for (Employee employee : l23) {
//				employee.setSalary(26000);
//				en.merge(employee);
//			}
//			
//			en.getTransaction().commit();
//		} catch (PersistenceException e) {
//			en.getTransaction().rollback();
//			e.printStackTrace();
//		}
		en.close();
		
		
		
		
	}

}
