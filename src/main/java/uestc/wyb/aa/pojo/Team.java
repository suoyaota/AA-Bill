package uestc.wyb.aa.pojo;

public class Team {
    private Long id;
    private Long leaderID;
    private String teamName;

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", leaderID=" + leaderID +
                ", teamName='" + teamName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public Long getLeaderID() {
        return leaderID;
    }
    public void setLeaderID(long leaderID) {
        this.leaderID = leaderID;
    }
}
