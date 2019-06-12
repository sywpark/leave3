/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Arif Fridasari
 */
@Entity
@Table(name = "EMPLOYEES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
    , @NamedQuery(name = "Employee.findById", query = "SELECT e FROM Employee e WHERE e.id = :id")
    , @NamedQuery(name = "Employee.findByFirstName", query = "SELECT e FROM Employee e WHERE e.firstName = :firstName")
    , @NamedQuery(name = "Employee.findByLastName", query = "SELECT e FROM Employee e WHERE e.lastName = :lastName")
    , @NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email")
    , @NamedQuery(name = "Employee.findByPhoneNumber", query = "SELECT e FROM Employee e WHERE e.phoneNumber = :phoneNumber")
    , @NamedQuery(name = "Employee.findByIsDelete", query = "SELECT e FROM Employee e WHERE e.isDelete = :isDelete")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @Column(name = "PHONE_NUMBER")
    private long phoneNumber;
    @Basic(optional = false)
    @Column(name = "IS_DELETE")
    private Character isDelete;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employee1", fetch = FetchType.LAZY)
    private LeaveRequest leaveRequest;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.LAZY)
    private List<EmployeeJob> employeeJobList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manager", fetch = FetchType.LAZY)
    private List<Employee> employeeList;
    @JoinColumn(name = "MANAGER", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee manager;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.LAZY)
    private Account account;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.LAZY)
    private AnnualLeave annualLeave;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.LAZY)
    private List<EmployeeRole> employeeRoleList;

    public Employee() {
    }

    public Employee(Long id) {
        this.id = id;
    }

    public Employee(Long id, String firstName, String lastName, String email, long phoneNumber, Character isDelete) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isDelete = isDelete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Character getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Character isDelete) {
        this.isDelete = isDelete;
    }

    public LeaveRequest getLeaveRequest() {
        return leaveRequest;
    }

    public void setLeaveRequest(LeaveRequest leaveRequest) {
        this.leaveRequest = leaveRequest;
    }

    @XmlTransient
    public List<EmployeeJob> getEmployeeJobList() {
        return employeeJobList;
    }

    public void setEmployeeJobList(List<EmployeeJob> employeeJobList) {
        this.employeeJobList = employeeJobList;
    }

    @XmlTransient
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public AnnualLeave getAnnualLeave() {
        return annualLeave;
    }

    public void setAnnualLeave(AnnualLeave annualLeave) {
        this.annualLeave = annualLeave;
    }

    @XmlTransient
    public List<EmployeeRole> getEmployeeRoleList() {
        return employeeRoleList;
    }

    public void setEmployeeRoleList(List<EmployeeRole> employeeRoleList) {
        this.employeeRoleList = employeeRoleList;
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
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Employee[ id=" + id + " ]";
    }
    
}
