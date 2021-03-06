package myproject.nativesql.test;

import java.util.List;

import org.hibernate.Query;
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
//			Query sqlQuery = session.getNamedQuery("sql_query");
//			sqlQuery.setFloat(0, 6000);
//			sqlQuery.setFloat("max", 9000);
//			sqlQuery.addEntity(Employee.class);
//			SQLQuery sqlQuery = session.createSQLQuery("select eno,ename,esal,eaddr from emp15");
			Query sqlQuery = session.getNamedQuery("sql_query");
			List<Object[]> list = sqlQuery.list();
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			System.out.println("------------------------------------------");
			for(Object[] objs : list) {
				for(Object obj : objs) {
				System.out.print(obj+"\t");
			}
			System.out.println();	
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
