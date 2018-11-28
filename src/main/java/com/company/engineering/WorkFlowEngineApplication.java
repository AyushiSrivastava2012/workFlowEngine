package com.company.engineering;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;
import com.company.engineering.State;
import com.company.engineering.ActionEnum;
import com.company.engineering.Action;
import com.company.engineering.GenericPojo;

@SpringBootApplication
@ComponentScan(basePackages={"com.company.engineering"})
public class WorkFlowEngineApplication  extends SpringBootServletInitializer implements WebApplicationInitializer {

	public static void main(String[] args) {
		System.out.println("in main");
		List<ActionEnum> list = new ArrayList<ActionEnum>();
		list.add(ActionEnum.CreateUser);
		GenericPojo pojo = new GenericPojo("1",State.Start, new Action("1",list));
		System.out.println(pojo.toString());
		SpringApplication.run(WorkFlowEngineApplication.class, args);
	}
}
