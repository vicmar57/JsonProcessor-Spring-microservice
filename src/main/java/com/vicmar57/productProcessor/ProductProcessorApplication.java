package com.vicmar57.productProcessor;

import com.vicmar57.productProcessor.model.AttributeProcessor;
import com.vicmar57.productProcessor.model.SimpleInvalidFilter;
import com.vicmar57.productProcessor.model.SimpleNormalizeCaps;
import com.vicmar57.productProcessor.model.SimpleSortByNAame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductProcessorApplication {

  @Bean
  public AttributeProcessor getAttributeProcessor() {
    return new AttributeProcessor(
            new SimpleNormalizeCaps(),
            new SimpleInvalidFilter(),
            new SimpleSortByNAame());
  }

  public static void main(String[] args) {
    SpringApplication.run(ProductProcessorApplication.class, args);
  }
}
