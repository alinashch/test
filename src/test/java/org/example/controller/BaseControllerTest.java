package org.example.controller;

import org.example.BaseSetupExtension;
import org.example.config.MockingConfiguration;
import org.example.config.TestContainersConfiguration;
import org.example.repository.BookRepository;
import org.example.util.TestDatabaseManager;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Testcontainers
@ActiveProfiles("test")
@Import({
        MockingConfiguration.class,
        TestContainersConfiguration.class
})
@ExtendWith(BaseSetupExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class BaseControllerTest {

    @Autowired
    protected TestDatabaseManager testDatabaseManager;

    @Autowired
    protected BookRepository bookRepository;

    @Autowired
    protected ResourceLoader resourceLoader;

}
