package myproject.nativesql.test;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import myproject.nativesql.entity.Employee;

public class Test {

	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Session session = null;
		StandardServiceRegistry registry = null;
		try {
			Configuration cfg = new Configuration();
			cfg.configure("/myproject/nativesql/resources/hibernate.cfg.xml");
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
			builder = builder.applySettings(cfg.getProperties());
			registry = builder.build();
			sessionFactory = cfg.buildSessionFactory(registry);
			session = sessionFactory.openSession();
			SQLQuery sqlQuery = session.createSQLQuery("select * from emp15 where ESAL > :min and ESAL < :max");
			sqlQuery.setFloat("min", 6000);
			sqlQuery.setFloat("max", 9000);
			sqlQuery.addEntity(Employee.class);
			List<Employee> empsList = sqlQuery.list();
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			System.out.println("------------------------------------------");
			for(Employee emp : empsList) {
				System.out.print(emp.getEno()+"\t");
				System.out.print(emp.getEname()+"\t");
				System.out.print(emp.getEsal()+"\t");
				System.out.println(emp.getEaddr());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}
