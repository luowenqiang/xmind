package cn.org.xmind.commons.db.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author rodney
 */
@Entity
@Table(name="COMMONS_PROPERTY")
public class GeneralProperty implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "PROP_NAME")
    private String propertyName;
    @Column(name = "PROP_TYPE")
    private GeneralPropertyType propertyType;
    @Column(name = "LONG_VALUE")
    private Long longValue;
    @Column(name = "TEXT_VALUE")
    private String textValue;
    @Column(name = "DOUBLE_VALUE")
    private Double doubleValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GeneralProperty)) {
            return false;
        }
        GeneralProperty other = (GeneralProperty) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.openoa.commons.db.entity.GeneralProperty[ id=" + id + " ]";
    }

    /**
     * @return the propertyName
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * @param propertyName the propertyName to set
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * @return the propertyType
     */
    public GeneralPropertyType getPropertyType() {
        return propertyType;
    }

    /**
     * @param propertyType the propertyType to set
     */
    public void setPropertyType(GeneralPropertyType propertyType) {
        this.propertyType = propertyType;
    }

    /**
     * @return the longValue
     */
    public Long getLongValue() {
        return longValue;
    }

    /**
     * @param longValue the longValue to set
     */
    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    /**
     * @return the textValue
     */
    public String getTextValue() {
        return textValue;
    }

    /**
     * @param textValue the textValue to set
     */
    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    /**
     * @return the doubleValue
     */
    public Double getDoubleValue() {
        return doubleValue;
    }

    /**
     * @param doubleValue the doubleValue to set
     */
    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }
}
