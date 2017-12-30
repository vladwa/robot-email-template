
<!-- Robot Framework Results -->
<%
import java.text.DateFormat
import java.text.SimpleDateFormat
%>
<STYLE>
BODY, TABLE, TD, TH, P {
  font-family:Verdana,Helvetica,sans serif;
  font-size:11px;
  color:black;
}
h1 { color:black; }
h2 { color:black; }
h3 { color:black; }
TD.bg1 { color:white; background-color:#0000C0; font-size:120% }
TD.bg2 { color:white; background-color:#4040FF; font-size:110% }
TD.bg3 { color:white; background-color:#8080FF; }
TD.test_passed { color:blue; }
TD.test_failed { color:red; }
TD.console { font-family:Courier New; }
</STYLE>
<BODY>
<h4><b>****** Automation Results******</b></h4>
<TABLE>
  <TR>
       <TD><IMG SRC="${rooturl}static/e59dfe28/images/32x32/<%= build.result.toString() == 'SUCCESS' ? "blue.gif" : build.result.toString() == 'FAILURE' ? 'red.gif' : 'yellow.gif' %>" />
        <TD><b style="color:black; background-color:#8080ff;font-size: 150%;"><%= build.result == hudson.model.Result.SUCCESS ? "ALL TESTS PASSED" : "SOME TESTS FAILED OR THE JOB ENCOUNTERED A PROBLEM" %></b></TD></TR>
  <TR><TD>Build URL:</TD><TD><A href="${rooturl}${build.url}">${rooturl}${build.url}</A></TD></TR>
  <TR><TD>Project URL:</TD><TD><A href="${rooturl}${project.url}">${rooturl}${project.url}</A></TD></TR>
  <TR><TD>Build Name:</TD><TD>${build.displayName}</TD></TR>
  <TR><TD>Date of job:</TD><TD>${it.timestampString}</TD></TR>
  <TR><TD>Job duration:</TD><TD>${build.durationString}</TD></TR>
 <TR><TD><b>Submitted by:</b></TD><TD><A href="vinayakladwa@gmail.com">Vinayaka Ladwa</A></TD></TR>
</TABLE>
<BR/>
</BODY>
<%
def robotResults = false
def actions = build.actions // List<hudson.model.Action>
actions.each() { action ->
    if( action.class.simpleName.equals("RobotBuildAction") ) { //

    hudson.plugins.robot.RobotBuildAction
    robotResults = true %>
    <table>
        <tr>
            <td><IMG SRC="${rooturl}static/cc2e27ea/plugin/robot/robot.png">
            <td style="color:black; background-color:#8080ff; font-size:150%;">
            <b><h4><u>Test Summary:</u></h4></b>
            </td>
        </tr>
    </table>
                <br/>
    <table id="robot-summary-table" style="border-collapse:collapse;text-align:center;">
                                <tr>
                                                <th style="font-weight:normal;padding:0 10px;">Type </th>
                                                <th style="font-weight:normal;padding:0 10px;">Total</th>
                                                <th style="font-weight:normal;padding:0 10px;">Failed</th>
                                                <th style="font-weight:normal;padding:0 10px;">Passed</th>
                                                <th style="font-weight:normal;padding:0 10px;">Pass %</th>
                                </tr>
                                <tr style="font-weight:bold">
                                                <th style="font-weight:normal">Critical tests</th>
                                                <td style="border-right:1px solid #000;border-bottom:1px solid #000;">${action.result.criticalTotal}</td>
                                                <td style="border-right:1px solid #000;border-bottom:1px solid #000;color:red">${action.result.criticalFailed}</td>
                                                <td style="border-right:1px solid #000;border-bottom:1px solid #000;color:green">${action.result.criticalPassed}</td>
                                                <td style="border-bottom:1px solid #000;">${action.criticalPassPercentage}</td>
                                </tr>
                                <tr style="font-weight:bold">
                                <th style="font-weight:normal">All tests</th>
                                                <td style="border-right:1px solid #000;">${action.result.overallTotal}</td>
                                                <td style="border-right:1px solid #000;color:red">${action.result.overallFailed}</td>
                                                <td style="border-right:1px solid #000;color:green">${action.result.overallPassed}</td>
                                                <td>${action.overallPassPercentage}</td>
                                </tr>
                                <tr style="font-weight:bold">
                                                <th style="font-weight:normal">Duration</hd>
                                                <td colspan=4 align="right">${action.result.humanReadableDuration}</td>
                                </tr>
                                <tr><td><br></td></tr>
                </table>

                 <table>
        <tr>
            <td><IMG SRC="${rooturl}static/cc2e27ea/plugin/robot/robot.png">
	     <td style="color:black; background-color:#8080ff; font-size:150%;">
            <b><h4 ><u>Statistics by Suite:</u></h4></b>
            </td>
        </tr>
    </table>
                <br/>

                <table cellspacing="1" cellpadding="4" border="2" align="left">
        <thead>
            <tr bgcolor="#F3F3F3">
                <td><b>Name      </b></td>
                <td><b>Failed tests     </b></td>
               <td><b>Passed tests     </b></td>
                <td><b>Duration</b></td>
            </tr>
        </thead>
        <tbody>
        <% def suites = action.result.allSuites
        suites.each() { suite ->
        def currSuite = suite
        def suiteName = currSuite.displayName
        // ignore top 2 elements in the structure as they are placeholders
        while (currSuite.parent != null && currSuite.parent.parent != null) {
        currSuite = currSuite.parent
        suiteName = currSuite.displayName + "." + suiteName
        } %>
            <tr>
                <td>
                    <b><%= suiteName %></b>
                </td>
                <td style="border-right:1px solid #000;border-bottom:1px solid #000;color:red">${suite.failed}</td>
                <td style="border-right:1px solid #000;border-bottom:1px solid #000;color:green">${suite.passed}</td>
                <td>${suite.humanReadableDuration}</td>
            </tr>
        <%  DateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SS")
        def execDateTcPairs = []
        suite.caseResults.each() { tc ->
        Date execDate = format.parse(tc.starttime)
        execDateTcPairs << [execDate, tc]
        }
        // primary sort execDate, secondary displayName
        execDateTcPairs = execDateTcPairs.sort{ a,b -> a[1].displayName <=> b[1].displayName }
        execDateTcPairs = execDateTcPairs.sort{ a,b -> a[0] <=> b[0] }
         // tests
        } %>
        </tbody>
    </table>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>

                <ul>
                                <li><a href="${rooturl}${build.url}robot">Detailed results</a></li>
                                <li><a href="${rooturl}${build.url}robot/report/report.html"> Open Report.html</a></li>
                                <li><a href="${rooturl}${build.url}robot/report/log.html"> Open Log.html</a></li>
				<li><a href="${rooturl}${build.url}console"> Console Output</a></li>
                </ul>
    <p> </p>
        <table width="500" border="0" cellpadding="5">
                <tr>
                  <td style="color:black; background-color:#8080ff; font-size:150%;">
                <b><h4><u>Test Trend(all tests)</u></h4></b>
                </tr>
                <tr>
                        <td align="center" valign="center"><img src="${rooturl}${build.url}robot/graph?zoomSignificant=true&hd=true&failedOnly=false&criticalOnly=false&maxBuildsToShow=0" width="500" height="300" alt="Test Result Tred">
</br>
        Test Result Trend
        </td>
</br>
</br>
                <td align="center" valign="center"><img src="${rooturl}${build.url}robot/durationGraph?maxBuildsToShow=0&hd=true" width="500" height="300" alt="Duration Trend">
</br>
        Duration Trend
</td>

                </tr>
        </table>
        </br>
        <table>
        <tr>
            <td><img src="${rooturl}static/cc2e27ea/plugin/robot/robot.png">
            <td style="color:black; background-color:#8080ff; font-size:150%;">
            <b><h4><u>Test Execution Results</u></h4></b>
            </td>
        </tr>
    </table>
</br>
   

    <table cellspacing="0" cellpadding="4" border="1" align="left">
        <thead>
            <tr bgcolor="#F3F3F3">
                <td><b> Test Name</b></td>
                <td><b>Status</b></td>
               <td><b>Message</b></td>
                <td><b>Execution</b></td>
                <td><b>Duration</b></td>
            </tr>
        </thead>
        <tbody>
        <% def suites1 = action.result.allSuites
        suites1.each() { suite ->
        def currSuite = suite
        def suiteName = currSuite.displayName
        // ignore top 2 elements in the structure as they are placeholders
        while (currSuite.parent != null && currSuite.parent.parent != null) {
        currSuite = currSuite.parent
        suiteName = currSuite.displayName + "." + suiteName
        } %>
            <tr>
                <td colspan="4">
                    <b><%= suiteName %></b>
                </td>
                <td>${suite.humanReadableDuration}</td>
            </tr>
        <%  DateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SS")
        def execDateTcPairs = []
        suite.caseResults.each() { tc ->
        Date execDate = format.parse(tc.starttime)
        execDateTcPairs << [execDate, tc]
        }
        // primary sort execDate, secondary displayName
        execDateTcPairs = execDateTcPairs.sort{ a,b -> a[1].displayName <=> b[1].displayName }
        execDateTcPairs = execDateTcPairs.sort{ a,b -> a[0] <=> b[0] }
        def i = 1
        execDateTcPairs.each() {
        def execDate = it[0]
        def tc = it[1]  %>
            <tr>
                <td><a href="${rooturl}${build.url}robot/report/log.html#s1-s1-t<%= i%>"><%= tc.displayName %></a></td>
                <% i = i + 1 %>
                <td style="color: <%= tc.isPassed() ? "#66CC00" : "#FF3333" %>"><%= tc.isPassed() ? "PASS" : "FAIL" %></td>
                         <td><%      if ( tc.errorMsg == null ) {
                        tc.errorMsg=""
    } else {
         tc.errorMsg
}%>
<%= tc.errorMsg %>
</td>
                <td><%= execDate %></td>
                <td>${tc.humanReadableDuration}</td>
            </tr>
        <%  } // tests
        } // suites %>
        </tbody>
    </table>
    <%
    } // robot results
    }
if (!robotResults) { %>
    <p>No Robot Framework test results found.</p>
    <%
    }
    %>
