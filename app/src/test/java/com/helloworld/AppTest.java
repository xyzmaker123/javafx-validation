/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.helloworld;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
    @Test public void appHasAGreeting() {
        JavaFXApplication classUnderTest = new JavaFXApplication();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }
}
