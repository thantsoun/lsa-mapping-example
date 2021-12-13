package cern.lsa.mapping.example.dto;

public abstract class SubContextDto extends ContextDto {

    private int startTime;
    private String user;

    public int getStartTime() {
        return this.startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return this.user;
    }
}