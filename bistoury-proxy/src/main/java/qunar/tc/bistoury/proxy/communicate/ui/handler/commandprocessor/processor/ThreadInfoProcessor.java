/*
 * Copyright (C) 2019 Qunar, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package qunar.tc.bistoury.proxy.communicate.ui.handler.commandprocessor.processor;

import com.google.common.collect.ImmutableSet;
import org.springframework.stereotype.Service;
import qunar.tc.bistoury.common.BistouryConstants;
import qunar.tc.bistoury.remoting.protocol.RequestData;
import qunar.tc.bistoury.proxy.communicate.ui.handler.commandprocessor.AbstractCommand;
import qunar.tc.bistoury.remoting.command.ThreadCommand;

import java.util.Set;

import static qunar.tc.bistoury.remoting.protocol.CommandCode.REQ_TYPE_HOST_THREAD;

/**
 * @author leix.xie
 * @date 2019/5/22 16:57
 * @describe
 */
@Service
public class ThreadInfoProcessor extends AbstractCommand<ThreadCommand> {

    @Override
    protected ThreadCommand prepareCommand(RequestData<ThreadCommand> data, String agentId) {
        ThreadCommand command = data.getCommand();
        command.setAppId(data.getApp());
        command.setPid(BistouryConstants.FILL_PID);
        return command;
    }

    @Override
    public Set<Integer> getCodes() {
        return ImmutableSet.of(REQ_TYPE_HOST_THREAD.getCode());
    }

    @Override
    public int getMinAgentVersion() {
        return -1;
    }

    @Override
    public boolean supportMulti() {
        return false;
    }

}
