/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KHAIRUL MUNA
 */
@Entity
@Table(name = "ANNUAL_LEAVES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnnualLeave.findAll", query = "SELECT a FROM AnnualLeave a")
    , @NamedQuery(name = "AnnualLeave.findById", query = "SELECT a FROM AnnualLeave a WHERE a.id = :id")
    , @NamedQuery(name = "AnnualLeave.findByRemain", query = "SELECT a FROM AnnualLeave a WHERE a.remain = :remain")
    , @NamedQuery(name = "AnnualLeave.findByTaken", query = "SELECT a FROM AnnualLeave a WHERE a.taken = :taken")})
public class AnnualLeave implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "REMAIN")
    private Short remain;
    @Column(name = "TAKEN")
    private Short taken;
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee;

    public AnnualLeave() {
    }

    public AnnualLeave(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getRemain() {
        return remain;
    }

    public void setRemain(Short remain) {
        this.remain = remain;
    }

    public Short getTaken() {
        return taken;
    }

    public void setTaken(Short taken) {
        this.taken = taken;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnnualLeave)) {
            return false;
        }
        AnnualLeave other = (AnnualLeave) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.AnnualLeave[ id=" + id + " ]";
    }
    
}
