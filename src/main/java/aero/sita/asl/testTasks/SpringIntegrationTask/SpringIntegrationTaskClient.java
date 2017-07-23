package aero.sita.asl.testTasks.SpringIntegrationTask;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Client class to inject spring configurations and to manage the flow.
 * @author Syed.Subhani
 *
 */
@SpringBootApplication
public class SpringIntegrationTaskClient extends SpringBootServletInitializer{
    /**
    * Sleep time.
    */
    private static final int TEN_SECONDS = 10000;
    /**
    * RESOURCES_DIR.
    */
    private static final File RESOURCES_DIR = new File("src/main/resources");
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(SpringIntegrationTaskClient.class);
    /**
     * context.
     */
    private static String configFile = "springConfig.xml";
    
    
    
    /**
     * private constructor.
     */
    public SpringIntegrationTaskClient(){
    	ClassPathXmlApplicationContext context = null;
        try {
            // Load Log4j configuration
            DOMConfigurator.configure(RESOURCES_DIR + "/log4j-config.xml");
            LOGGER.debug("Loaded logging configurations");
            // Load Spring configurations
            context = new ClassPathXmlApplicationContext(configFile);
            LOGGER.debug("Loaded spring context.");
            // Allow 10 seconds for file processing before shutting down the spring context.
            Thread.sleep(TEN_SECONDS);
        } catch (BeansException e) {
            LOGGER.error("Bean Exception occured in the application."+e);
        } catch (InterruptedException e) {
            LOGGER.error("Error occured ..."+e);
        }
    }
    
    public static String getConfigFile() {
        return configFile;
    }

    public static void setConfigFile(final String configFile) {
        SpringIntegrationTaskClient.configFile = configFile;
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringIntegrationTaskClient.class);
    }

    /**
     * Main method to launch the spring integration example application.
     * @param args
     */
    public static void main(String[] args)
    {
    	SpringApplication.run(SpringIntegrationTaskClient.class, args);
        
    }
}
