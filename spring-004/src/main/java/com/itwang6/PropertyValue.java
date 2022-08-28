package com.itwang4;

public class PropertyValue {
    // 属性名字
    private String propertyName;

    // 属性值
    private Object propertyValue;

    public PropertyValue(String propertyName, Object propertyValue){
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

    public Object getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyValue(Object propertyValue) {
        this.propertyValue = propertyValue;
    }
}
