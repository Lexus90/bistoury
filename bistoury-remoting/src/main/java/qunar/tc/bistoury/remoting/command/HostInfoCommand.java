package qunar.tc.bistoury.remoting.command;

public class HostInfoCommand extends BaseCommand {
    private String pid;

    @Override
    public String getPid() {
        return pid;
    }

    @Override
    public void setPid(String pid) {
        this.pid = pid;
    }
}
