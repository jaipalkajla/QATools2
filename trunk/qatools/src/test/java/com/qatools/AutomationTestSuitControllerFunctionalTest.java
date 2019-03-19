package com.qatools;

import org.junit.Test;

import com.qatools.AutomationTestSuitController;

/**
 */
public class AutomationTestSuitControllerFunctionalTest {
	/*private static final Logger LOG = LoggerFactory.getLogger(AutomationTestSuiteTest.class);
*/

	@Test
	public void testAutomationForExcel() {
	    AutomationTestSuitController.main(new String[]{"-f",this.getClass().getClassLoader().getResource("Test_Scenarios.xls").getPath()});
	}
	
	@Test
    public void testAutomationForXml() {
        AutomationTestSuitController.main(new String[]{"-f",this.getClass().getClassLoader().getResource("Test_Scenarios.xml").getPath()});
    }
	
	@Test
    public void testAutomationForXml1() {
        AutomationTestSuitController.main(new String[]{"-f",this.getClass().getClassLoader().getResource("Test_Scenarios.XML").getPath()});
    }


}
