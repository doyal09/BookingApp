package com.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class MyApplication {
	    public static void main(String[] args) {
	    	//final Logger logger = LoggerFactory.getLogger(MyApplication.class);
		 SpringApplication.run(MyApplication.class, args);
		//logger.debug("--Application Started--");
	    }       
}
