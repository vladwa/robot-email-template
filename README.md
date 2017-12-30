# Jenkins-robot-email-ext-template(https://github.com/vladwa/robot-email-template/blob/master/Robot%20Framework%20icon.png)

## Use in Jenkins ![alt text]

1. Have your Jenkins administrator place the script inside $JENKINS_HOME/email-templates.
2. Use the SCRIPT token with the template parameter equal to your script filename without the .groovy extension. For example, if the script filename is email-template.groovy, the email content would look like this ${SCRIPT,template="email-template"}.
3. Change the ${Report html name} and ${Log html name} token to "report.html" and "log.html" in the jenkins "Publish Robot Framework test result" section as per the template. 

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