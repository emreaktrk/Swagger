package com.oneframe.plugin.swagger.utils

class ValidationUtilsTest extends GroovyTestCase {

    void testUrl() {
        boolean result = ValidationUtils.url("http://178.211.54.214:5000/swagger/v1/swagger.json")
        assertTrue(result)
    }

    void testHas() {
        boolean result = ValidationUtils.has("hello")
        assertTrue(result)
    }
}
