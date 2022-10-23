package ua.goit.jdbc.model.dao;

public class CustomerDao {
    private Integer custId;
    private Integer custName;
    private String custLocation;

    public CustomerDao(Integer custId, Integer custName, String custLocation) {
        this.custId = custId;
        this.custName = custName;
        this.custLocation = custLocation;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Integer getCustName() {
        return custName;
    }

    public void setCustName(Integer custName) {
        this.custName = custName;
    }

    public String getCustLocation() {
        return custLocation;
    }

    public void setCustLocation(String custLocation) {
        this.custLocation = custLocation;
    }

    @Override
    public String toString() {
        return "CustomerDao{" +
                "custId=" + custId +
                ", custName=" + custName +
                ", custLocation='" + custLocation + '\'' +
                '}';
    }
}
