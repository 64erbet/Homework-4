package ua.goit.jdbc.model.dao;

public class CompanyDao {
    private Integer compId;
    private String compName;
    private String compLocation;

    public CompanyDao() {

    }

    public CompanyDao(Integer compId, String compName, String compLocation) {
        this.compId = compId;
        this.compName = compName;
        this.compLocation = compLocation;
    }

    public Integer getCompId() {
        return compId;
    }

    public void setCompId(Integer compId) {
        this.compId = compId;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompLocation() {
        return compLocation;
    }

    public void setCompLocation(String compLocation) {
        this.compLocation = compLocation;
    }

    @Override
    public String toString() {
        return "CompanyDao {" +
                "\n   compId = " + compId +
                ",\n   compName = '" + compName + '\'' +
                ",\n   compLocation = '" + compLocation + '\'' +
                "\n}";
    }
}
