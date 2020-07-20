package com.vicmar57.productProcessor.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.List;

public class Product {

  private Long id;
  private List<Attribute> attributes;
  private AttributeProcessor pr;
  public Product() {
  }

  public Product(Long id, List<Attribute> attributes) {
    this.id = id;
    this.attributes = attributes;
  }

  public Long getId() {
    return id;
  }

  public List<Attribute> getAttributes() {
    return attributes;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("attributes", attributes)
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equal(id, product.id) &&
        Objects.equal(attributes, product.attributes);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, attributes);
  }

}













//  public void normalize_caps() {
//    try {
//      //iterate over all attributes
//      for(Attribute attr : attributes) {
//        //exclude "title" attribute
//        if(!attr.getName().equals("title")) {
//          List<String> vals = attr.getValues();
//          for(int i =0; i< vals.size(); i++) {
//            //capitalize every String value (only first word in sentence)
//            //O(1) time for set and get in ArrayList
//            vals.set(i, StringUtils.capitalize(vals.get(i).toLowerCase()));
//          }
//        }
//      }
//    }
//    catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//
//  public void filterInvalids() {
//    try {
//      Iterator<Attribute> attrIt = attributes.iterator();
//      while (attrIt.hasNext()) {
//        Attribute attr = attrIt.next();
//        Iterator<String> it = attr.getValues().iterator();
//        while (it.hasNext()) {
//          String curr = it.next();
//          if (curr.equals("N/a") || curr.equals("N/A") || curr.equals("Not Applied"))
//            it.remove(); // NOTE: Iterator's remove method, not ArrayList's, is used.
//        }
//        if(attr.getValues().size() == 0)
//          attrIt.remove();
//      }
//    }
//    catch (Exception e) {
//        e.printStackTrace();
//    }
//  }
//
//
//
//  public void sortAttributesByName() {
//    try {
//      //sort attributes by attribute name, in alphabetical order
//      Collections.sort(attributes, (a,b) -> {
//        return a.getName().compareTo(b.getName());
//        // >= 1   means a.getName() > b.getName()
//        // 0      means a.getName() == b.getName()
//        // <= -1  means a.getName() < b.getName()
//      });
//    }
//    catch (Exception e) {
//      e.printStackTrace();
//    }
//  }

