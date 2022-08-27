package com.itwang5;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {

    private List<PropertyValue> propertyValueList = new ArrayList<>();

    public PropertyValue[] getPropertyValues(){
        return propertyValueList.toArray(new PropertyValue[0]);
    }


    public void addPropertyValue(PropertyValue propertyValue){
        this.propertyValueList.add(propertyValue);
    }

    public PropertyValue getPropertyValue(String propertyName){
        for(PropertyValue propertyValue : propertyValueList){
            if(propertyValue.getPropertyName().equals(propertyName)){
                return propertyValue;
            }
        }
        return null;
    }
}
