package com.qa.opencart.listeners;

public class JenkinsDetails {

	
	
	//go to jenkins war file folder
	// cd "C:\Users\Sunitha\Desktop\Automation Training\JenkinsWar"
	// command: java -jar jenkins.war
	
	// enter below in google chrome
	//jenkins url :local  //8080
	//enter user name password
	
	//Click on new item and add the project
	
	//how to parameterize
	// in general -> click on -> this project is parameterized
	// enter name, values, description
	
	//in build, goals option
	// clean install -Denv=${envName}
	
	//once cliked on Build with parameters
	//it will show a dropdown to select environment
	
	//cron job periodically
	
	/*Jenkins used a cron expression (official documentation), and the different fields are:

		MINUTES Minutes in one hour (0-59)
		HOURS Hours in one day (0-23)
		DAYMONTH Day in a month (1-31)
		MONTH Month in a year (1-12)
		DAYWEEK Day of the week (0-7) where 0 and 7 are sunday */
	
	
	//if port is used by other tool and need to change port number
	// java -jar jenkins.war --httpPort=9090
}
