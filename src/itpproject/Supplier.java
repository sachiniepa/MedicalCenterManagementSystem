/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpproject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author HP User
 */
@Entity
@Table(name = "supplier", catalog = "hms", schema = "")
@NamedQueries({
    @NamedQuery(name = "Supplier.findAll", query = "SELECT s FROM Supplier s")
    , @NamedQuery(name = "Supplier.findBySupCode", query = "SELECT s FROM Supplier s WHERE s.supCode = :supCode")
    , @NamedQuery(name = "Supplier.findByName", query = "SELECT s FROM Supplier s WHERE s.name = :name")
    , @NamedQuery(name = "Supplier.findByRegNo", query = "SELECT s FROM Supplier s WHERE s.regNo = :regNo")
    , @NamedQuery(name = "Supplier.findByRating", query = "SELECT s FROM Supplier s WHERE s.rating = :rating")
    , @NamedQuery(name = "Supplier.findByRemark", query = "SELECT s FROM Supplier s WHERE s.remark = :remark")
    , @NamedQuery(name = "Supplier.findByContactNo", query = "SELECT s FROM Supplier s WHERE s.contactNo = :contactNo")})
public class Supplier implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "supCode")
    private Integer supCode;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "regNo")
    private String regNo;
    @Basic(optional = false)
    @Column(name = "rating")
    private String rating;
    @Basic(optional = false)
    @Column(name = "remark")
    private String remark;
    @Basic(optional = false)
    @Column(name = "contactNo")
    private String contactNo;

    public Supplier() {
    }

    public Supplier(Integer supCode) {
        this.supCode = supCode;
    }

    public Supplier(Integer supCode, String name, String regNo, String rating, String remark, String contactNo) {
        this.supCode = supCode;
        this.name = name;
        this.regNo = regNo;
        this.rating = rating;
        this.remark = remark;
        this.contactNo = contactNo;
    }

    public Integer getSupCode() {
        return supCode;
    }

    public void setSupCode(Integer supCode) {
        Integer oldSupCode = this.supCode;
        this.supCode = supCode;
        changeSupport.firePropertyChange("supCode", oldSupCode, supCode);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange("name", oldName, name);
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        String oldRegNo = this.regNo;
        this.regNo = regNo;
        changeSupport.firePropertyChange("regNo", oldRegNo, regNo);
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        String oldRating = this.rating;
        this.rating = rating;
        changeSupport.firePropertyChange("rating", oldRating, rating);
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        String oldRemark = this.remark;
        this.remark = remark;
        changeSupport.firePropertyChange("remark", oldRemark, remark);
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        String oldContactNo = this.contactNo;
        this.contactNo = contactNo;
        changeSupport.firePropertyChange("contactNo", oldContactNo, contactNo);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (supCode != null ? supCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        if ((this.supCode == null && other.supCode != null) || (this.supCode != null && !this.supCode.equals(other.supCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itpproject.Supplier[ supCode=" + supCode + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
