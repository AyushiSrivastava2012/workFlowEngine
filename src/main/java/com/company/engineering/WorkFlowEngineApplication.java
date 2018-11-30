package com.company.engineering;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;

import com.company.engineering.eventListener.EventSource;
import com.company.engineering.eventListener.EventType;
import com.company.engineering.eventListener.Listener;
import com.company.engineering.events.BaseEvent;
import com.company.engineering.events.InsertUserInMongoEvent;
import com.company.engineering.pojo.Action;
import com.company.engineering.pojo.ActionEnum;
import com.company.engineering.pojo.GenericPojo;
import com.company.engineering.pojo.State;

@SpringBootApplication
@ComponentScan(basePackages={"com.company.engineering"})
public class WorkFlowEngineApplication  extends SpringBootServletInitializer implements WebApplicationInitializer {

	public static void main(String[] args) {        
		SpringApplication.run(WorkFlowEngineApplication.class, args);
	}
}
