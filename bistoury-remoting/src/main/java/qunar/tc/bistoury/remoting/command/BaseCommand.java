package qunar.tc.bistoury.remoting.command;

public abstract class BaseCommand {
    private String appId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    abstract public String getPid();

    abstract public void setPid(String pId);
}
