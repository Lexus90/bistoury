package qunar.tc.bistoury.remoting.command;

public class ArthasCommand extends BaseCommand {
    private String pid;

    private String command;

    @Override
    public String getPid() {
        return pid;
    }

    @Override
    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
