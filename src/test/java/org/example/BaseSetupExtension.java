package org.example;

import io.restassured.RestAssured;
import org.example.util.TestUserService;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

public class BaseSetupExtension implements BeforeAllCallback {

    protected TestUserService testUserService;

    @Override
    public void beforeAll(ExtensionContext context) {
        String classNameKey = this.getClass().getName();
        Object classNameValue = context.getRoot().getStore(GLOBAL).get(classNameKey);
        if (classNameValue == null) {
            context.getRoot().getStore(GLOBAL).put(classNameKey, this);
            initFields(context);
            setup();
        }
    }

    private void initFields(ExtensionContext context) {
        var springContext = SpringExtension.getApplicationContext(context);
        testUserService = springContext.getBean(TestUserService.class);

        var serverPort = springContext.getEnvironment().getProperty("local.server.port");
        RestAssured.baseURI = "http://localhost:%s/".formatted(serverPort);
    }

    public void setup() {
        testUserService.createTestUsers();
    }
}