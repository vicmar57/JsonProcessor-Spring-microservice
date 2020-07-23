package com.vicmar57.productProcessor.model;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SimpleNormalizeCaps implements INormalizeCapsBehaviour {

    @Override
    public void normalize_caps(List<Attribute> attributes) {
        attributes.stream()
                .forEach(at -> {
                    if(!at.getName().equals("title")) {
                        at.getValues()
                                .replaceAll(val ->
                                        StringUtils.capitalize(val.toLowerCase()));
                    }
                });
    }
}


// the java 7 way
//    @Override
//    public void normalize_caps(List<Attribute> attributes) {
//            //iterate over all attributes
//            for(Attribute attr : attributes) {
//                //exclude "title" attribute
//                if(!attr.getName().equals("title")) {
//                    List<String> vals = attr.getValues();
//                    for(int i =0; i< vals.size(); i++) {
//                        //capitalize every String value (only first word in sentence)
//                        //O(1) time for set and get in ArrayList
//                        vals.set(i, StringUtils.capitalize(vals.get(i).toLowerCase()));
//                    }
//                }
//            }
//    }