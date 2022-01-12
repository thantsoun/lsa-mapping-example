package cern.lsa.mapping.example.domain;

public abstract class SubContextImpl extends ContextImpl implements SubContext {

    private int startTime;
    private String user;

    protected SubContextImpl(long id, String name) {
        super(id, name);
    }

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