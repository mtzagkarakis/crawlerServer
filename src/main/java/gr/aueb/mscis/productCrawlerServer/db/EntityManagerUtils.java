package gr.aueb.mscis.productCrawlerServer.db;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

public class EntityManagerUtils {
	private final static Logger logger = Logger.getLogger(EntityManagerUtils.class.getName());
	private static EntityManagerFactory entityManagerFactory;
	private EntityManagerUtils(){}
	public static boolean checkIfEntityManagersOK(){		
		EntityManager em = getEntityManager();
		if (em == null){
			return false;
		}
		em.close();
		return true;
	}
	public static void init(String dbUrl, String dbUser,  String dbPass, boolean showSQL){
		try {
			Map<String, String> persistanceProps = new HashMap<>();
			persistanceProps.put("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
			persistanceProps.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			persistanceProps.put("javax.persistence.schema-generation.database.action", "none");
			persistanceProps.put("hibernate.connection.zeroDateTimeBehavior", "convertToNull");
			persistanceProps.put("hibernate.globally_quoted_identifiers", "true");
			persistanceProps.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
			persistanceProps.put("hibernate.connection.CharSet", "utf8");
			persistanceProps.put("hibernate.connection.characterEncoding", "utf8");
			persistanceProps.put("hibernate.connection.useUnicode", "true");
			persistanceProps.put("hibernate.connection.autocommit", "false");
//			persistanceProps.put("hibernate.connection.pool_size", "1");
			
			persistanceProps.put("javax.persistence.jdbc.url", "jdbc:mysql:"+dbUrl+"?useSSL=false&zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=UTF8");
			persistanceProps.put("javax.persistence.jdbc.user", dbUser);
			persistanceProps.put("javax.persistence.jdbc.password", dbPass);
			
       		persistanceProps.put("hibernate.connection.provider_class", "org.hibernate.hikaricp.internal.HikariCPConnectionProvider");
       		persistanceProps.put("hikari.dataSourceClassName", "org.mariadb.jdbc.MySQLDataSource");
       		persistanceProps.put("hikari.autoCommit", "false");
       		persistanceProps.put("hikari.dataSource.user", "jdbc:mysql:"+dbUser);
			persistanceProps.put("hikari.dataSource.password", "jdbc:mysql:"+dbPass);
       		
       		
			String showSQLStr = showSQL?"true":"false";
			persistanceProps.put("hibernate.show_sql", showSQLStr);
			persistanceProps.put("hibernate.format_sql", showSQLStr);
			
			entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit", persistanceProps);
		} catch (Exception e){
			logger.error(e);
		}	
	}
	public static void doInTransaction(Consumer<EntityManager> function){
		EntityManager em = EntityManagerUtils.getEntityManager();
		EntityTransaction et = null;
		if (em == null){
			logger.error("Null entity Manager");
			return;
		}
		try{
			et = em.getTransaction();
			et.begin();
			function.accept(em);
			et.commit();
		} catch(Exception e){
			if (et != null && et.isActive())
				et.rollback();
			logger.warn(e);
		} finally{
			em.close();
		}
	}
	public static <T> T doInTransaction(Function<EntityManager, T> function){
		EntityManager em = EntityManagerUtils.getEntityManager();
		EntityTransaction et = null;
		T result = null;
		if (em == null){
			logger.error("Null entity Manager");
			return null;
		}
		try{
			et = em.getTransaction();
			et.begin();
			result = function.apply(em);
			et.commit();
		} catch(Exception e){
			if (et != null && et.isActive())
				et.rollback();
			logger.warn(e);
		} finally{
			em.close();
		}
		return result;
	}
	public static EntityManager getEntityManager(){
		if (entityManagerFactory == null){
			return null;
		}
		if (!entityManagerFactory.isOpen()){
			return null;
		}
		return entityManagerFactory.createEntityManager();
	}

	public static void close(){
		if (entityManagerFactory != null && entityManagerFactory.isOpen())
			entityManagerFactory.close();
		entityManagerFactory = null;
	}
}
