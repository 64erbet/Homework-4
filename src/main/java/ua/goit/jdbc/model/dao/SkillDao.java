package ua.goit.jdbc.model.dao;

public class SkillDao {
    private Integer skillId;
    private String skillName;
    private String skillLavel;

    public SkillDao(Integer skillId, String skillName, String skillLavel) {
        this.skillId = skillId;
        this.skillName = skillName;
        this.skillLavel = skillLavel;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillLavel() {
        return skillLavel;
    }

    public void setSkillLavel(String skillLavel) {
        this.skillLavel = skillLavel;
    }

    @Override
    public String toString() {
        return "SkillDao{" +
                "skillId=" + skillId +
                ", skillName='" + skillName + '\'' +
                ", skillLavel='" + skillLavel + '\'' +
                '}';
    }
}
