package ua.goit.jdbc.model.dao;

import java.time.LocalDate;

public class ProjectDao {
    private Integer projectId;
    private String projectNameDAO;
    private Integer customerIdDAO;
    private LocalDate createDateDAO;
    private LocalDate reportDateDAO;

    public ProjectDao(){

    };

    public ProjectDao(Integer projectId, String projectNameDAO,
                      Integer customerIdDAO, LocalDate createDateDAO,
                      LocalDate reportDateDAO) {
        this.projectId = projectId;
        this.projectNameDAO = projectNameDAO;
        this.customerIdDAO = customerIdDAO;
        this.createDateDAO = createDateDAO;
        this.reportDateDAO = reportDateDAO;
    }

    public String getProjectNameDAO() {
        return projectNameDAO;
    }

    public void setProjectNameDAO(String projectNameDAO) {
        this.projectNameDAO = projectNameDAO;
    }

    public Integer getCustomerIdDAO() {
        return customerIdDAO;
    }

    public void setCustomerIdDAO(Integer customerIdDAO) {
        this.customerIdDAO = customerIdDAO;
    }

    public LocalDate getCreateDateDAO() {
        return createDateDAO;
    }

    public void setCreateDateDAO(LocalDate createDateDAO) {
        this.createDateDAO = createDateDAO;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public LocalDate getReportDateDAO() {
        return reportDateDAO;
    }

    public void setReportDateDAO(LocalDate reportDateDAO) {
        this.reportDateDAO = reportDateDAO;
    }

    @Override
    public String toString() {
        return "ProjectDaoWithId {" +
                "\n   projectId = " + projectId +
                ",\n   projectNameDAO = '" + projectNameDAO + '\'' +
                ",\n   customerIdDAO = " + customerIdDAO +
                ",\n   createDateDAO = " + createDateDAO +
                ",\n   reportDateDAO = " + reportDateDAO +
                "\n}";
    }
}

