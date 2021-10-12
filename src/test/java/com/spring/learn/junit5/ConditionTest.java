package com.spring.learn.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledForJreRange;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

public class ConditionTest {

    @EnabledOnOs(OS.WINDOWS)
    public void windowsTest() {

    }

    @EnabledOnJre(JRE.JAVA_8)
    @DisabledForJreRange(max = JRE.JAVA_9)
    public void jreTest() {

    }

    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    @DisabledIfSystemProperty(named = "java.vm.name", matches = "*.*HotSpot.*")
    public void sysTest() {
        System.out.println("sys");
    }

    @EnabledIfEnvironmentVariable(named = "JAVA_HOME", matches = ".*")
    public void variableTest() {

    }


}
