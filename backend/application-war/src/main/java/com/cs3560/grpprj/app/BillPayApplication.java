package com.cs3560.grpprj.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/** WORK AREA: application-war | OWNER: Lead | Composes approved developer JARs into the sole deployable WAR. */
@SpringBootApplication(scanBasePackages = "com.cs3560.grpprj")
public class BillPayApplication extends SpringBootServletInitializer {
  public static void main(String[] args) { SpringApplication.run(BillPayApplication.class, args); }
}
