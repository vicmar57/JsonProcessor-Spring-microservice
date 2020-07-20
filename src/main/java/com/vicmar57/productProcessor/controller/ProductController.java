package com.vicmar57.productProcessor.controller;

import com.vicmar57.productProcessor.model.Attribute;
import com.vicmar57.productProcessor.model.AttributeProcessor;
import com.vicmar57.productProcessor.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/product-processor")
public class ProductController {

  @Autowired
  private AttributeProcessor attrProc;

  @Value("${attribute-values.exclusion-list}")
  private List <String> exclusionList;

  @RequestMapping(path = "/product", method = RequestMethod.POST)
  public ResponseEntity<Product> upsert(@RequestBody Product product) {

    if(product != null) {
      //the AttributeProcessor does the processing and changes the product object
      exclusionList.replaceAll(str -> str.toLowerCase());
      List <Attribute> attrs = product.getAttributes();
      attrProc.filterInvalids(attrs, Arrays.asList(""));

      attrProc.normalize_caps(attrs);
      attrProc.filterInvalids(attrs, exclusionList);
      attrProc.sortAttributes(attrs);

      //return the changed product object
      return ResponseEntity.ok(product);
    }
    return ResponseEntity.of(null);
  }
}
