package com.qa.opencart.listeners;

public class NGROKWEBHOOKExample {

	//both jenkins and ngrok should be enabled before trying to trigger jenkins job for every push
	
	//dowload ngrok. unzip, add to path
	//open terminal 
	//got to where the ngrok is present
	//enter : ngrok authtoken tokenvalue
	// ngrok http 8080
	
	
	//go to jenkins
	//click on project settings
	// click on webhooks
	// click on add
	//enter URL from terminal
	// if connection established we get message in terminal 200OK
	
	//in jenkins
	//in build triggers
	//select the option "github hook trigger for polling"
	
	
	//do git push origin master
	//automatically project will run in  jenkins
	
	
	
}
