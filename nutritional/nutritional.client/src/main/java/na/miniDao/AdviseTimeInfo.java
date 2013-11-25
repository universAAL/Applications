/**
 * AdviseTimeInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package na.miniDao;

public class AdviseTimeInfo implements java.io.Serializable {
    private boolean isAnyTime;

    private na.miniDao.AdviseTime[] times;

    public AdviseTimeInfo() {
    }

    public AdviseTimeInfo(boolean isAnyTime, na.miniDao.AdviseTime[] times) {
	this.isAnyTime = isAnyTime;
	this.times = times;
    }

    /**
     * Gets the isAnyTime value for this AdviseTimeInfo.
     * 
     * @return isAnyTime
     */
    public boolean isIsAnyTime() {
	return isAnyTime;
    }

    /**
     * Sets the isAnyTime value for this AdviseTimeInfo.
     * 
     * @param isAnyTime
     */
    public void setIsAnyTime(boolean isAnyTime) {
	this.isAnyTime = isAnyTime;
    }

    /**
     * Gets the times value for this AdviseTimeInfo.
     * 
     * @return times
     */
    public na.miniDao.AdviseTime[] getTimes() {
	return times;
    }

    /**
     * Sets the times value for this AdviseTimeInfo.
     * 
     * @param times
     */
    public void setTimes(na.miniDao.AdviseTime[] times) {
	this.times = times;
    }

    public na.miniDao.AdviseTime getTimes(int i) {
	return this.times[i];
    }

    public void setTimes(int i, na.miniDao.AdviseTime _value) {
	this.times[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;

    public synchronized boolean equals(java.lang.Object obj) {
	if (!(obj instanceof AdviseTimeInfo))
	    return false;
	AdviseTimeInfo other = (AdviseTimeInfo) obj;
	if (obj == null)
	    return false;
	if (this == obj)
	    return true;
	if (__equalsCalc != null) {
	    return (__equalsCalc == obj);
	}
	__equalsCalc = obj;
	boolean _equals;
	_equals = true
		&& this.isAnyTime == other.isIsAnyTime()
		&& ((this.times == null && other.getTimes() == null) || (this.times != null && java.util.Arrays
			.equals(this.times, other.getTimes())));
	__equalsCalc = null;
	return _equals;
    }

    private boolean __hashCodeCalc = false;

    public synchronized int hashCode() {
	if (__hashCodeCalc) {
	    return 0;
	}
	__hashCodeCalc = true;
	int _hashCode = 1;
	_hashCode += (isIsAnyTime() ? Boolean.TRUE : Boolean.FALSE).hashCode();
	if (getTimes() != null) {
	    for (int i = 0; i < java.lang.reflect.Array.getLength(getTimes()); i++) {
		java.lang.Object obj = java.lang.reflect.Array.get(getTimes(),
			i);
		if (obj != null && !obj.getClass().isArray()) {
		    _hashCode += obj.hashCode();
		}
	    }
	}
	__hashCodeCalc = false;
	return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
	    AdviseTimeInfo.class, true);

    static {
	typeDesc.setXmlType(new javax.xml.namespace.QName("http://miniDao/",
		"adviseTimeInfo"));
	org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
	elemField.setFieldName("isAnyTime");
	elemField.setXmlName(new javax.xml.namespace.QName("http://miniDao/",
		"isAnyTime"));
	elemField.setXmlType(new javax.xml.namespace.QName(
		"http://www.w3.org/2001/XMLSchema", "boolean"));
	elemField.setNillable(false);
	typeDesc.addFieldDesc(elemField);
	elemField = new org.apache.axis.description.ElementDesc();
	elemField.setFieldName("times");
	elemField.setXmlName(new javax.xml.namespace.QName("http://miniDao/",
		"times"));
	elemField.setXmlType(new javax.xml.namespace.QName("http://miniDao/",
		"adviseTime"));
	elemField.setMinOccurs(0);
	elemField.setNillable(false);
	elemField.setMaxOccursUnbounded(true);
	typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
	return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
	    java.lang.String mechType, java.lang.Class _javaType,
	    javax.xml.namespace.QName _xmlType) {
	return new org.apache.axis.encoding.ser.BeanSerializer(_javaType,
		_xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType,
	    javax.xml.namespace.QName _xmlType) {
	return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType,
		_xmlType, typeDesc);
    }

}
