package com.qatools.internal.command;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qatools.TestCaseContext;
import com.qatools.internal.ActionCommand;
import com.qatools.internal.AutomationCommand;
import com.qatools.internal.IdentifierType;
import com.qatools.model.AutomationTestCaseStep;
import com.qatools.model.TestStatus;
import com.qatools.model.ValidTestStatus;

/**
 * @author jaipal kajla
 * 
 * @version $Revision: 2.0 $
 */
@AutomationCommand(name = "EXISTS")
public class Exists implements ActionCommand {

    /**
     * slf4j Logger instance.
     */
    private static final Logger LOG = LoggerFactory.getLogger(Exists.class);

    /**
     * Method execute. Executes step, updates status in it and returns status.
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
        WebElement element = null;
        try {
            element = IdentifierType.getElementForIdentifier(
                    step.getIdentifierType(), step.getIdentifier(),
                    step.getData(), context.getDriver());
            if (element != null) {
                status.setStatus(ValidTestStatus.PASS);
            } else {
                status.setStatus(ValidTestStatus.FAIL);
                status.setStatusDesc("Element not found");
            }
        } catch (NoSuchElementException exp) {
            status.setStatus(ValidTestStatus.FAIL);
            status.setStatusDesc("Element not found");
        } catch (Exception e) {
            LOG.error("Exception occurred", e);
            status.setStatus(ValidTestStatus.FAIL);
            status.setStatusDesc("Exception Occurred :-" + e.getMessage());
        }
        step.setStatus(status);
        return status;
    }

}
