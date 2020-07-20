package com.vicmar57.productProcessor.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SimpleSortByNAame implements IsortBehaviour{
    @Override
    public void sortAttributes(List<Attribute> attributes) {
            Collections.sort(attributes, Comparator.comparing(Attribute::getName));
    }
}
