package com.vicmar57.productprocessor.integration;

import com.vicmar57.productProcessor.ProductProcessorApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProductProcessorApplication.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class BaseTest {

}
