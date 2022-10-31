package ua.goit.jdbc.model.dao;

import java.math.BigDecimal;

public class DeveloperDao {
    private Integer devId;
    private String devName;
    private String devGen;
    private Integer devAge;
    private Integer companyId;
    private BigDecimal salary;

    public DeveloperDao() {
    }

    public DeveloperDao(Integer devId, String devName, String devGen,
                        Integer devAge, Integer companyId, BigDecimal salary) {
        this.devId = devId;
        this.devName = devName;
        this.devGen = devGen;
        this.devAge = devAge;
        this.companyId = companyId;
        this.salary = salary;
    }

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getDevGen() {
        return devGen;
    }

    public void setDevGen(String devGen) {
        this.devGen = devGen;
    }

    public Integer getDevAge() {
        return devAge;
    }

    public void setDevAge(Integer devAge) {
        this.devAge = devAge;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "DeveloperDao{" +
                "devId=" + devId +
                ", devName='" + devName + '\'' +
                ", devGen='" + devGen + '\'' +
                ", devAge=" + devAge +
                ", companyId=" + companyId +
                ", salary=" + salary +
                '}';
    }
}
