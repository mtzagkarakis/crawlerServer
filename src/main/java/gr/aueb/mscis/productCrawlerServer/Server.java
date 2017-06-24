package gr.aueb.mscis.productCrawlerServer;

import static spark.Spark.get;
import static spark.Spark.notFound;
import static spark.Spark.port;
import static spark.Spark.stop;

import java.util.concurrent.ForkJoinPool;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import gr.aueb.mscis.productCrawlerServer.db.EntityManagerUtils;
import gr.aueb.mscis.productCrawlerServer.httpController.ProductController;
import gr.aueb.mscis.productCrawlerServer.properties.Properties;
import spark.servlet.SparkApplication;

public class Server implements SparkApplication{
	private final static Logger logger = Logger.getLogger(Server.class.getName());
	public static final Properties props = new Properties("server.properties");
	private static final ForkJoinPool forkJoinPool = new ForkJoinPool(props.getParallelThreads().orElse(4));
	@Override
	public void init() {
		EntityManagerUtils.init(props.getDBUrl().orElse(""), props.getDBUsername().orElse(""), props.getDBPassword().orElse(""), false);
		EntityManager em = EntityManagerUtils.getEntityManager();
		if (em == null){
			logger.error("Problem contacting the db. Shutting down!");
			destroy();
		} else {
			em.close();
		}
		
		port(props.getPort().orElse(4567));
		ProductController productController = new ProductController(forkJoinPool);
		
		get("/", productController.getProduct());
		get("/cached", productController.getProductCached());
		
		notFound("<html><body><h1>Sorry! page not found (status 404)</h1></body></html>");
	}
	
	@Override
	public void destroy(){
		EntityManagerUtils.close();
		stop();
	}
	
	public static void main(String[] args) {
		
		DOMConfigurator.configure(Server.class.getResource("/log4jProperties/log4jProperties.xml"));
		
		Server server = new Server();
		server.init();
		
		logger.info("Server started");
	}

}
