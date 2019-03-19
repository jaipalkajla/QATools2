package com.qatools.internal.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qatools.TestCaseContext;
import com.qatools.internal.ActionCommand;
import com.qatools.internal.AutomationCommand;
import com.qatools.model.AutomationTestCaseStep;
import com.qatools.model.TestStatus;
import com.qatools.model.ValidTestStatus;

/**
 */
@AutomationCommand(name = "BROWSE_BACK")
public class BrowseBack implements ActionCommand {

    /**
     * slf4j Logger instance.
     */
    private static final Logger LOG = LoggerFactory.getLogger(BrowseBack.class);

    /**
     * Method execute.
     *
     * @param step
     *            AutomationTestCaseStep
     * @param context
     *            TestCaseContext
     * @return TestStatus
     * @see com.qatools.internal.ActionCommand#execute(AutomationTestCaseStep,
     *      TestCaseContext)
     */
    @Override
    public final TestStatus execute(final AutomationTestCaseStep step,
            final TestCaseContext context) {
        TestStatus status = new TestStatus();
        LOG.info("Executing BROWSE BACK for step");
        try {
            LOG.info("Executing step:- " + step);
            context.getDriver().navigate().back();
            status.setStatusDesc("back to page with title '"
                    + context.getDriver().getTitle() + "'");
            status.setStatus(ValidTestStatus.PASS);
        } catch (Exception e) {
            LOG.error("Exception occurred in browser back", e);
            status.setStatus(ValidTestStatus.FAIL);
            status.setStatusDesc("Exception Occurred :-" + e.getMessage());
        }
        step.setStatus(status);
        return status;
    }

}
