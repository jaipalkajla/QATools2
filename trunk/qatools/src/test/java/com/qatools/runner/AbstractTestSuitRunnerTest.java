package com.qatools.runner;

import java.io.InputStream;
import java.lang.reflect.Constructor;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import junit.framework.TestCase;
import mockit.Expectations;
import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

import com.qatools.TestCaseContext;
import com.qatools.exception.ToolRuntimeException;
import com.qatools.internal.ActionCommand;
import com.qatools.internal.ActionCommandFactory;
import com.qatools.model.AutomationTestCase;
import com.qatools.model.AutomationTestCaseStep;
import com.qatools.model.AutomationTestSuite;
import com.qatools.model.TestStatus;
import com.qatools.model.ValidTestStatus;
import com.qatools.runner.AbstractTestSuitRunner;
import com.qatools.runner.TestSuitRunner;

/**
 * The class <code>AbstractTestSuitRunnerTest</code> contains tests for the
 * class {@link <code>AbstractTestSuitRunner</code>}
 * 
 * @pattern JUnit Test Case
 * 
 * @generatedBy CodePro at 12/13/13 11:15 AM
 * 
 * @author jaipal kajla
 * 
 * @version $Revision$
 */

public class AbstractTestSuitRunnerTest extends TestCase {

    @Mocked
    ActionCommandFactory factory;
    @Mocked
    TestCaseContext.TestCaseContextBuilder builder;

    @Test
    public void testrunTestSuit() throws Exception {

        new NonStrictExpectations() {
            {
                new TestCaseContext.TestCaseContextBuilder();
                builder.getTestCaseContext();
                returns(new TestCaseContext());
               // result = new TestCaseContext.TestCaseContextBuilder();

                ActionCommandFactory.getActionCommand(anyString);
                result = new ActionCommand() {
                    @Override
                    public TestStatus execute(AutomationTestCaseStep step,
                            TestCaseContext context) {
                        TestStatus status = new TestStatus(
                                ValidTestStatus.PASS, "Mock");
                        if (step == null) {
                            return status;
                        } else {
                            step.setStatus(status);
                        }
                        return status;
                    }
                };

            }
        };

        AutomationTestCaseStep dummyStep = null;
        try {
            Constructor consturctor = AutomationTestCaseStep.class
                    .getDeclaredConstructor(new Class[] {});
            consturctor.setAccessible(true);
            dummyStep = ((AutomationTestCaseStep) consturctor
                    .newInstance(new Object[] {}));
        } catch (Exception e) {
            throw new ToolRuntimeException("Exception in getting constructor",
                    e);
        }

        System.out.println(ActionCommandFactory.getActionCommand("CLICK")
                .execute(dummyStep, null));

        System.out.println(ActionCommandFactory.getActionCommand("CLICK")
                .execute(null, null));

        TestSuitRunner runner = new AbstractTestSuitRunner() {
            @Override
            public AutomationTestSuite runTestSuite(String testFilePath) {
                InputStream stream = this.getClass().getClassLoader()
                        .getResourceAsStream("Test_Scenarios.xml");

                AutomationTestSuite suit = null;

                try {
                    JAXBContext jaxbContext = JAXBContext
                            .newInstance(AutomationTestSuite.class);
                    Unmarshaller jaxbunmarshaller = jaxbContext
                            .createUnmarshaller();

                    SchemaFactory sf = SchemaFactory
                            .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                    Schema schema = sf.newSchema(this.getClass()
                            .getClassLoader()
                            .getResource("qatools-model.xsd"));
                    jaxbunmarshaller.setSchema(schema);

                    suit = (AutomationTestSuite) jaxbunmarshaller
                            .unmarshal(stream);
                } catch (Exception e) {
                    throw new ToolRuntimeException(e.getMessage(), e);
                }

                this.runTestSuit(suit);

                return suit;
            }
        };

        runner.runTestSuite(this.getClass().getClassLoader()
                .getResource("Test_Scenarios.xml").getPath());

    }
}
