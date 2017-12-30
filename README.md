#Jenkins-robot-email-ext-template

##Use in Jenkins
![alt text](https://github.com/vladwa/robot-email-template/blob/master/Robot%20Framework%20icon.png)

	1.Have your Jenkins administrator place the script inside $JENKINS_HOME/email-templates.
	2.Use the SCRIPT token with the template parameter equal to your script filename without the .groovy extension. For example, if the script filename is email-template.groovy, the email content would look like this ${SCRIPT,template="email-template"}.
	3.Change the ${Report html name} and ${Log html name} token to "report.html" and "log.html" in the jenkins "Publish Robot Framework test result" section as per the template. 



