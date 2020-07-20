package com.vicmar57.productProcessor.model;

import java.util.List;

public class AttributeProcessor implements INormalizeCapsBehaviour, IFilterBehaviour, IsortBehaviour {
    private INormalizeCapsBehaviour ncb;
    private IFilterBehaviour fb;
    private IsortBehaviour sb;

    public AttributeProcessor(INormalizeCapsBehaviour ncb, IFilterBehaviour fb, IsortBehaviour sb) {
        this.ncb = ncb;
        this.fb = fb;
        this.sb = sb;
    }

    public void normalize_caps(List<Attribute> attributes) {
        this.ncb.normalize_caps(attributes);
    }

    public void filterInvalids(List<Attribute> attributes, List<String> invalids) {
        this.fb.filterInvalids(attributes, invalids);
    }

    public void sortAttributes(List<Attribute> attributes) {
        this.sb.sortAttributes(attributes);
    }
}
