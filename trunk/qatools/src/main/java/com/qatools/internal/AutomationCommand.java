package com.qatools.internal;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author jaipal kajla
 * 
 * @version $Revision: 1.0 $
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface AutomationCommand {

    /**
     * Method name
     * 
     * @return string
     */
    String name();
}
