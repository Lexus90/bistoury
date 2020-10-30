package qunar.tc.bistoury.agent.common.pid.impl;

import com.google.common.base.Strings;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.io.ByteStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import qunar.tc.bistoury.agent.common.ClosableProcess;
import qunar.tc.bistoury.agent.common.ClosableProcesses;
import qunar.tc.bistoury.agent.common.pid.PidHandler;
import qunar.tc.bistoury.agent.common.pid.bean.PsInfo;
import qunar.tc.bistoury.clientside.common.meta.MetaStore;
import qunar.tc.bistoury.clientside.common.meta.MetaStores;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PidByPsAppIdHandler extends AbstractPidHandler implements PidHandler {

    private static final Logger logger = LoggerFactory.getLogger(PidByPsAppIdHandler.class);

    private static final MetaStore META_STORE = MetaStores.getMetaStore();

    private static final String TOMCAT_USER = META_STORE.getStringProperty("tomcat.user", "tomcat");
    private static final String TOMCAT_COMMAND = META_STORE.getStringProperty("tomcat.command", "/home/java/default/bin/java");

    private static final int USER_INDEX = 0;
    private static final int PID_INDEX = 1;
    private static final int COMMAND_INDEX = 10;

    @Override
    public int priority() {
        return Priority.FROM_PS_APP_ID_PRIORITY;
    }

    @Override
    protected int doGetPid() {
        return doGetPid(null);
    }

    @Override
    protected int doGetPid(String appId) {
        String psInfo = getPsInfo(appId);
        if (!Strings.isNullOrEmpty(psInfo)) {
            ArrayListMultimap<String, PsInfo> multimap = parsePsInfo(psInfo);

            Map.Entry<String, PsInfo> javaEntry = multimap.entries().stream().filter(e -> e.getKey().contains("java")).findFirst().get();
            if (Objects.nonNull(javaEntry)) {
                return javaEntry.getValue().getPid();
            }
        }
        return -1;
    }

    private static ArrayListMultimap<String, PsInfo> parsePsInfo(final String psInfo) {
        ArrayListMultimap<String, PsInfo> multimap = ArrayListMultimap.create();
        String all = psInfo.replaceAll("[( )\t]+", " ");
        String[] lines = all.split("[\n\r(\r\n)]");
        for (String line : lines) {
            if (Strings.isNullOrEmpty(line)) {
                continue;
            }
            String[] pieces = line.split(" ");
            final String user = pieces[USER_INDEX];
            final int pid = Integer.parseInt(pieces[PID_INDEX]);
            final String command = pieces[COMMAND_INDEX];
            final String[] params = Arrays.copyOfRange(pieces, COMMAND_INDEX + 1, pieces.length);
            PsInfo process = new PsInfo(user, pid, command, params);
            multimap.put(command, process);
        }
        return multimap;
    }

    private static String getPsInfo(String appId) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (ClosableProcess process = ClosableProcesses.wrap(new ProcessBuilder("/bin/sh", "-c", "ps aux | grep java | grep Dhll.app.id=" + appId).redirectErrorStream(true).start());
             InputStream inputStream = process.getInputStream()) {
            ByteStreams.copy(inputStream, outputStream);
            return outputStream.toString("utf8");
        } catch (Exception e) {
            logger.error("execute ps aux|grep java error", e);
            return null;
        }
    }

}
