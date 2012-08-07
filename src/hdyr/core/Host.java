/*
 * Copyright (C) 2012 Felix Wiemuth
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package hdyr.core;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A 'Host' represents a transport layer or a collection
 * of those on a LAN, connected to one router.
 * The router is set when one is created using this host.
 * @author Felix Wiemuth
 */
public class Host extends SimObject implements HostInterface {

    private TransportProtocol protocol;
    private Queue<SimPacket> fromApplication = new LinkedList<SimPacket>();
    private Queue<SimPacket> packetsReceived = new LinkedList<SimPacket>();
    private Router router; //the router this host is connected to

    public Host(Router router, TransportProtocol protocol, String name, SimulationInfo info) throws Exception {
        super(name, info);
        this.protocol = protocol;
        this.router = router;
        if (!router.setLAN(this)) {
            throw new Exception("Cannot attach host, router already has one!");
        }
        protocol.setHost(this);
    }
    
    public void insertFromApplication(Packet p) {
        fromApplication.add(new SimPacket(p, Integer.toString(info().getPacketID())));
        protocol.onPacketFromApplication(p);
    }

    public void insertFromRouter(SimPacket p) {
        packetsReceived.add(p);
        protocol.onPacketReceived(p.packet());
    }
    
    public void simulateStep() {
        protocol.onSimulationStep();
    }

    @Override
    public Packet peek() {
        return fromApplication.peek().packet();
    }

    @Override
    public boolean push() {
        if (fromApplication.isEmpty()) {
            return false;
        }
        router.insertFromLAN(fromApplication.poll());
        return true;
    }
}
