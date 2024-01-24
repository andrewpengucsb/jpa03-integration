package edu.ucsb.cs156.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ExampleApplication {

  private static ApplicationContext applicationContext;
  public static void main(String[] args) {
    applicationContext = 
          new AnnotationConfigApplicationContext(ExampleApplication.class);
    System.out.println("******************************************************");
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }
        System.out.println("******************************************************");
    // SpringApplication.run(ExampleApplication.class, args);
  }

}
