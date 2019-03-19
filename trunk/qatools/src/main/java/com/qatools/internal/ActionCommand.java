package com.qatools.internal;

import com.qatools.TestCaseContext;
import com.qatools.model.AutomationTestCaseStep;
import com.qatools.model.TestStatus;

/**
 */
public interface ActionCommand {
    /**
     * Method execute. Executes step, updates status in it and returns status.
     * 
     * @param step
     *            AutomationTestCaseStep
     * @param context
     *            TestCaseContext
     * @return TestStatus
     */
    TestStatus execute(AutomationTestCaseStep step, TestCaseContext context);
}
