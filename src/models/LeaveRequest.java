/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Arif Fridasari
 */
@Entity
@Table(name = "LEAVE_REQUESTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LeaveRequest.findAll", query = "SELECT l FROM LeaveRequest l")
    , @NamedQuery(name = "LeaveRequest.findById", query = "SELECT l FROM LeaveRequest l WHERE l.id = :id")
    , @NamedQuery(name = "LeaveRequest.findByStartDate", query = "SELECT l FROM LeaveRequest l WHERE l.startDate = :startDate")
    , @NamedQuery(name = "LeaveRequest.findByEndDate", query = "SELECT l FROM LeaveRequest l WHERE l.endDate = :endDate")
    , @NamedQuery(name = "LeaveRequest.findByDetail", query = "SELECT l FROM LeaveRequest l WHERE l.detail = :detail")
    , @NamedQuery(name = "LeaveRequest.findByEmployee", query = "SELECT l FROM LeaveRequest l WHERE l.employee = :employee")
    , @NamedQuery(name = "LeaveRequest.findByManager", query = "SELECT l FROM LeaveRequest l WHERE l.manager = :manager")})
public class LeaveRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @Column(name = "DETAIL")
    private String detail;
    @Basic(optional = false)
    @Column(name = "EMPLOYEE")
    private long employee;
    @Basic(optional = false)
    @Column(name = "MANAGER")
    private long manager;
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee1;
    @JoinColumn(name = "TYPE", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LeaveType type;
    @JoinColumn(name = "STATUS", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Status status;

    public LeaveRequest() {
    }

    public LeaveRequest(Long id) {
        this.id = id;
    }

    public LeaveRequest(Long id, Date startDate, Date endDate, String detail, long employee, long manager) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.detail = detail;
        this.employee = employee;
        this.manager = manager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getEmployee() {
        return employee;
    }

    public void setEmployee(long employee) {
        this.employee = employee;
    }

    public long getManager() {
        return manager;
    }

    public void setManager(long manager) {
        this.manager = manager;
    }

    public Employee getEmployee1() {
        return employee1;
    }

    public void setEmployee1(Employee employee1) {
        this.employee1 = employee1;
    }

    public LeaveType getType() {
        return type;
    }

    public void setType(LeaveType type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        if (!(object instanceof LeaveRequest)) {
            return false;
        }
        LeaveRequest other = (LeaveRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.LeaveRequest[ id=" + id + " ]";
    }
    
}
