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
@AutomationCommand(name = "SWITCH_TO_MAIN_WINDOW")
public class SwitchToMainWindow implements ActionCommand {

    /**
     * slf4j Logger instance.
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(SwitchToMainWindow.class);

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
        LOG.info("Executing SWITCH_TO_MAIN_WINDOW for step");

        try {
            LOG.info("Executing step:- " + step);
            LOG.info("Master window handle is:- "
                    + context.getMasterWindowHandle());
            // LOG.info("Current window handle is:- " +
            // context.getDriver().getWindowHandle());

            for (String str : context.getDriver().getWindowHandles()) {
                LOG.info("window handle is:- " + str);
            }

            context.getDriver().switchTo()
                    .window(context.getMasterWindowHandle());
            status.setStatus(ValidTestStatus.PASS);
            status.setStatusDesc("Switched to main window with title '"
                    + context.getDriver().getTitle() + "'");
        } catch (Exception e) {
            LOG.error("Exception occurred in SWITCH_TO_MAIN_WINDOW", e);
            status.setStatus(ValidTestStatus.FAIL);
            status.setStatusDesc("Exception Occurred :-" + e.getMessage());
        }

        step.setStatus(status);
        return status;
    }

}
