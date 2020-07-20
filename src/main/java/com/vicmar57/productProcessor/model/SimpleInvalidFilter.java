package com.vicmar57.productProcessor.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimpleInvalidFilter implements IFilterBehaviour {

    @Override
    public void filterInvalids(List<Attribute> attributes, List <String> invalids) {
            attributes.forEach(at ->
                    at.getValues()
                            .removeIf(line ->
                                    line == null || filterConditionally(line, invalids)));
            attributes.removeIf(at -> at.getValues().size() == 0);
    }

    public static boolean filterConditionally(String value, List<String> exclusionList) {
        return exclusionList.contains(value.toLowerCase());
    }
}




//    The Java 7 way
//    @Override
//    public void filterInvalids(List<Attribute> attributes) {
//            //iterate over all attributes
//            Iterator<Attribute> attrIt = attributes.iterator();
//            while (attrIt.hasNext()) {
//                Attribute attr = attrIt.next();
//                Iterator<String> it = attr.getValues().iterator();
//                //iterate over all battribute values
//                while (it.hasNext()) {
//                    String curr = it.next().toLowerCase();
//                    if (curr.equals("n/a") || curr.equals("not applied"))
//                        it.remove(); // NOTE: Iterator's remove method, not ArrayList's, is used.
//                }
//                if(attr.getValues().size() == 0)
//                    attrIt.remove();
//            }
//    }


//    public void cleanAttributes(List<Attribute> attributes) {
//        the java 7 way
//        //iterate over all attributes
//        Iterator<Attribute> attrIt = attributes.iterator();
//        while (attrIt.hasNext()) {
//            Attribute attr = attrIt.next();
//            Iterator<String> it = attr.getValues().iterator();
//            //iterate over all attribute values
//            while (it.hasNext()) {
//                String curr = it.next();
//                if (curr == null || curr.equals(""))
//                    it.remove(); // NOTE: Iterator's remove method, not ArrayList's, is used.
//            }
//            if (attr.getValues().size() == 0)
//                attrIt.remove();
//        }
//    }