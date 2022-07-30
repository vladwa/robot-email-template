
# Jenkins-robot-email-ext-template ![alt text](https://github.com/vladwa/robot-email-template/blob/master/Robot%20Framework%20icon.png)

## Use in Jenkins

There are two ways to use this in Jenkins.

1. First way:
    a. Have your Jenkins administrator place the script inside $JENKINS_HOME/email-templates.
    b. Use the SCRIPT token with the template parameter equal to your script filename without the .groovy extension. For example, if the script filename is email-template.groovy, the email content would look like this ${SCRIPT,template="email-template"}.
2. 
	a. Using the [Config File Provider Plugin](https://wiki.jenkins.io/display/JENKINS/Config+File+Provider+Plugin), upload a groovy Extended Email Publisher Groovy Template
    b. Use the SCRIPT token with the template parameter equal to your script filename without the .groovy extension and with managed: in front. For example, if the script filename is email-template.groovy, the email content would look like this ${SCRIPT,template="managed:email-template"}.
   
Either way, outputFileName, logFileName, reportFileName should all be the default of "output.xml", "report.html" and "log.html" in the jenkins "Publish Robot Framework test result" section or pipeline step. 

Note: While copying template, use raw file, otherwise may contain escape string.

# Email Template provides the below information.
	1. Build Details
		- Build URL
		- Project URL
		- Build Name
		- Date of Job
		- Job Duration
		- Submitted by 
		
	2. Test Summary
	
	3. Statistics by Suite
		This table contains below details.
			- (Name) Test suite name
			- (Failed tests) Number of Failed test cases in the suite
			- (Passed tests) Number of Passed test cases in the suite
			- (Duration) Suite execution time.
			
	4. Links to below.
		- Detailed Results.
		- Report
		- Log
		- Console output
		
	5. Test Trend(all tests)
		- Test Result Trend Image(Refer: https://github.com/vladwa/robot-email-template/blob/master/Test%20Result%20Trend.png)
		- Duration Trend Image(Refer: https://github.com/vladwa/robot-email-template/blob/master/Duration%20Trend.png)
	
	6. Test Execution Results
		- Test Name
		- Status
		- Message
		- Execution time
		- Duration

![alt text](https://img.shields.io/badge/Say%20Thanks-!-1EAEDB.svg)
