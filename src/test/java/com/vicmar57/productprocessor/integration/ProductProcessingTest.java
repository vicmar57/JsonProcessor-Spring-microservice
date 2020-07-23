package com.vicmar57.productprocessor.integration;

import static org.assertj.core.api.Assertions.assertThat;

import com.vicmar57.productProcessor.model.Attribute;
import com.vicmar57.productProcessor.model.Product;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProductProcessingTest extends BaseTest {

  private static final String UPSERT_PRODUCT_URL = "/product-processor/product";

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void normalization_capitalize() {
    Product product = new Product(100L, ImmutableList.<Attribute>builder()
        .add(new Attribute("color", Lists.newArrayList("red", "blue", "GREEN", "bLUE", null, "", "n/a", "not applied")))
        .add(new Attribute("size", Lists.newArrayList("xl", "extra small")))
            .add(new Attribute("otherAttr", Lists.newArrayList("", null)))
            .add(new Attribute("title", Lists.newArrayList("ralph Lauren Men's Tshirt")))

            .build());
    ResponseEntity<Product> productResponseEntity = restTemplate.postForEntity(UPSERT_PRODUCT_URL, product, Product.class);
    assertThat(productResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    List<Attribute> attributes = productResponseEntity.getBody().getAttributes();
    Map<String, List<String>> attributesMap = toAttributesMap(attributes);
    assertThat(attributesMap).containsEntry("title", Lists.newArrayList("ralph Lauren Men's Tshirt"));
    assertThat(attributesMap).containsEntry("color", Lists.newArrayList("Red", "Blue", "Green", "Blue"));
    assertThat(attributesMap).containsEntry("size", Lists.newArrayList("Xl", "Extra small"));
  }

  @Test
  public void nullProductTest_noAction() {
    Product product = null;
    ResponseEntity<Product> productResponseEntity = restTemplate.postForEntity(UPSERT_PRODUCT_URL, product, Product.class);
    assertThat(productResponseEntity.getStatusCode()).isEqualTo(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    List<Attribute> attributes = productResponseEntity.getBody().getAttributes();
    assertThat(attributes).isNull();
  }

  @Test
  public void attributeValuesExclusion() {
    Product product = new Product(100L, ImmutableList.<Attribute>builder()
        .add(new Attribute("title", Lists.newArrayList("ralph Lauren Men's Tshirt")))
        .add(new Attribute("color", Lists.newArrayList("red", "N/A", null, "")))
        .add(new Attribute("size", Lists.newArrayList("Not Applied", null)))
        .build());
    ResponseEntity<Product> productResponseEntity = restTemplate.postForEntity(UPSERT_PRODUCT_URL, product, Product.class);
    assertThat(productResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    List<Attribute> attributes = productResponseEntity.getBody().getAttributes();
    Map<String, List<String>> attributesMap = toAttributesMap(attributes);
    assertThat(attributesMap).containsEntry("title", Lists.newArrayList("ralph Lauren Men's Tshirt"));
    assertThat(attributesMap).containsEntry("color", Lists.newArrayList("Red"));
    assertThat(attributesMap).doesNotContainKey("size");
  }

  @Test
  public void sortByAttributeNames() {
    Product product = new Product(100L, ImmutableList.<Attribute>builder()
            .add(new Attribute("title", Lists.newArrayList("ralph Lauren Men's Tshirt")))
            .add(new Attribute("Size", Lists.newArrayList("xl")))
            .add(new Attribute("Color", Lists.newArrayList("red")))
            .add(new Attribute("brand", Lists.newArrayList("sony")))
            .build());
    ResponseEntity<Product> productResponseEntity = restTemplate.postForEntity(UPSERT_PRODUCT_URL, product, Product.class);
    assertThat(productResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    List<Attribute> attributes = productResponseEntity.getBody().getAttributes();
    List<String> attributeNames = attributes.stream().map(Attribute::getName).collect(Collectors.toList());
    assertThat(attributeNames).containsExactly("Color", "Size", "brand", "title");
  }

  private Map<String, List<String>> toAttributesMap(List<Attribute> attributes) {
    return attributes.stream()
        .collect(Collectors.toMap(Attribute::getName, Attribute::getValues));
  }
}
