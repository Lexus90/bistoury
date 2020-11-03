package qunar.tc.bistoury.commands.arthas.telnet;

import qunar.tc.bistoury.commands.arthas.TelnetConstants;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TelnetPort {
    private final static AtomicInteger start_port = new AtomicInteger(TelnetConstants.TELNET_CONNECTION_PORT + 1);

    private final static Map<Integer, Integer> pidPorts = new ConcurrentHashMap<>();

    public static Integer getPortByPid(Integer pid) {
        if (pidPorts.containsKey(pid)) {
            System.out.println("pid = " + pid + ", port = " + pidPorts.get(pid));
            return pidPorts.get(pid);
        }
        Integer port = start_port.incrementAndGet();
        pidPorts.put(pid, port);
        System.out.println("pid = " + pid + ", port = " + port);
        return port;
    }

    public static Map<Integer, Integer> getPidPorts() {
        return pidPorts;
    }
}
