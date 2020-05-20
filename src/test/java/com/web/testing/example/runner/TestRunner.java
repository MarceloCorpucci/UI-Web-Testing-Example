package com.web.testing.example.runner;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", 
				 glue = "com.web.testing.example.glue",
				 plugin= {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm"})
public class TestRunner {

}
