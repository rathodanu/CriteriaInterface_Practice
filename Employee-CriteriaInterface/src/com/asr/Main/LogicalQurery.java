package com.asr.Main;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.PropertyProjection;
import org.hibernate.criterion.Restrictions;

import com.asr.Model.Employee;
import com.asr.SQL.ConfigurationClass;

public class LogicalQurery {
	static Session session = ConfigurationClass.createConfig();
	static Transaction beginTransaction = session.beginTransaction();
	

	public static void Q1() {
		
		// What Are The Name OF All Emplolyees
		
		Criteria criteria = session.createCriteria(Employee.class);
		PropertyProjection property = Projections.property("name");
		criteria.setProjection(property);
		List list = criteria.list();
		System.out.println(list);

	}

	public static void Q2()
	{
		// how many employess are there total?
		
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setProjection(Projections.rowCount());
		List list = criteria.list();
		System.out.println(list);
	}
	
	public static void Q3()
	{
		//what department do employee work in
		
		Criteria criteria = session.createCriteria(Employee.class);
		Criteria criteria1 = session.createCriteria(Employee.class);
		Criteria setProjection = criteria.setProjection(Projections.groupProperty("depart"));
		criteria1.add(Restrictions.eq("name", setProjection ));
		List list = criteria1.list();
		for (Object object : list) {
			System.out.println(object);	
		}
	}
	
	public static void Q4()
	{
		// how many employee work in each department
		
		Criteria criteria = session.createCriteria(Employee.class);
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("depart"));
		projectionList.add(Projections.count("depart"));
		criteria.setProjection(projectionList);
		List<Object[]> list = criteria.list();
		for (Object[] objects : list) {
			System.out.println(objects[0]+" "+ objects[1]);
			
		}
		
		
	}
	public static void Q5()
	{
		// Who is the higest paid employee
		Criteria criteria = session.createCriteria(Employee.class);
		Criteria criteria1 = session.createCriteria(Employee.class);
		criteria1.add(Restrictions.eq("salary",criteria.setProjection(Projections.max("salary")).list().get(0))).setProjection
		(Projections.property("name"));
		Object uniqueResult = criteria1.uniqueResult();
		System.out.println(uniqueResult);
	}
	
	public static void Q6()
	{
		// who is the lowest paid employee
		
		Criteria criteria = session.createCriteria(Employee.class);
		Criteria criteria1 = session.createCriteria(Employee.class);
		criteria.setProjection(Projections.min("salary")).list().get(0);
		criteria1.add(Restrictions.eq("salary",criteria.setProjection(Projections.min("salary")).list().get(0))).setProjection(Projections.property("name"));
		Object uniqueResult = criteria1.uniqueResult();
		System.out.println(uniqueResult);
	}
	
	public static void Q7()
	{
		// How Many employee earn more than 20000 per year
		
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.gt("salary", 20000.00)).setProjection(Projections.count("salary"));
		List list = criteria.list();
		System.out.println(list);	
	}
	
	public static void Q8()
	{
		//What Is Avg Salary
		
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setProjection(Projections.avg("salary"));
		Object uniqueResult = criteria.uniqueResult();
		System.out.println(uniqueResult);
		
	}
	
	public static void Q9()
	{
		//who are the top 5 highest paid employee
		
		Criteria criteria = session.createCriteria(Employee.class);
		Criteria criteria1 = session.createCriteria(Employee.class);
		Criteria setMaxResults = criteria.addOrder(Order.desc("salary")).setMaxResults(5).setProjection(Projections.property("name"));
		//criteria1.add(Restrictions.eq("name", setMaxResults));
		List list = criteria.list();
		System.out.println(list);	
	}
	
	public static void Q10()
	{
		//Who are the employess in marketing department
		Criteria criteria = session.createCriteria(Employee.class);
		Criteria criteria1 = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("depart", "market")).setProjection(Projections.property("name"));
		List list = criteria.list();
		System.out.println(list);	
	}
	public static void Q11()
	{
		// How Many Employee Have salary between 15000-25000
		
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.between("salary", 15000.00, 25000.00)).setProjection(Projections.count("id"));
		List list = criteria.list();
		System.out.println(list);
	}
	public static void Q12()
	{
		//Who are The Employee with salary of null
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("salary", 0.0)).setProjection(Projections.property("name"));
		List list = criteria.list();
		System.out.println(list);
	}
	
	public static void Q13()
	{
		// who are the employee with first name starting is j
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.ilike("name", "j%"));
		List list = criteria.list();
		System.out.println(list);
	}
	
	public static void Q14()
	{
		// what are the total salary of all employee in decending order
		
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.addOrder(Order.desc("salary")).setProjection(Projections.property("name"));
		List list = criteria.list();
		System.out.println(list);
	}
	
	public static void Q15()
	{
		// What Are Total expenditure of company
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setProjection(Projections.sum("salary"));
		Object uniqueResult = criteria.uniqueResult();
		System.out.println(uniqueResult);
	}
	
	public static void Q16()
	{
		//Who are the employee with same first name;
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.ilike("name", "%"));
	}
	
	public static void Q17()
	{
		// how many employee are there in pune location
		//select count(id) from employee where location=pune
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("location", "pune")).setProjection(Projections.count("id"));
		List list = criteria.list();
		System.out.println(list);	
		}
	public static void Q18()
	{
		// how many employee live in each location
		Criteria criteria = session.createCriteria(Employee.class);
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("location"));
		projectionList.add(Projections.count("id"));
		criteria.setProjection(projectionList);
		List<Object[]> list = criteria.list();
		for (Object[] objects : list) {
			System.out.println(objects[0]+" "+ objects[1]);
			
		}
	}
	
	public static void Q19()
	{
		// what is avg salary in dev department
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setProjection(Projections.avg("salary")).add(Restrictions.eq("depart", "Dev"));
		criteria.add(Restrictions.gt("salary", criteria));
		List list = criteria.list();
		System.out.println(list);
	}
	
	public static void Q20()
	{
		// who are the employee with salary above avg
		Criteria criteria = session.createCriteria(Employee.class);
		Criteria criteria1 = session.createCriteria(Employee.class);
		 Object object = criteria1.setProjection(Projections.avg("salary")).list().get(0);
		criteria.add(Restrictions.gt("salary",object )).setProjection(Projections.property("name"));
		List list = criteria.list();
		System.out.println(list);
	}
	
	public static void Q21()
	{
		//whos has lowest salary in test department
		Criteria criteria = session.createCriteria(Employee.class);
		Criteria criteria1 = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("depart", "Test"));
		criteria1.setProjection(Projections.min("salary"));
		Object uniqueResult = criteria1.uniqueResult();
		System.out.println(uniqueResult);
		
	}
	
	public static void main(String[] args) {
//		Q1();
//		Q2();
//		Q3();
//		Q4();
//		Q5();
//		Q6();
//		Q7();
//		Q8();
//		Q9();
//		Q10();
//		Q11();
//		Q12();
//		Q13();
//		Q14();
//		Q15();
//		Q17();
//		Q18();
//		Q19();
//		Q20();
		Q21();
		
	}

}
