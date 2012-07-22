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

import static hdyr.util.Log.*;
import java.util.concurrent.LinkedBlockingQueue;
import hdyr.util.Math;

/**
 * A Link is a unidirectional connection from one router to another. It is
 * characterized by a 'LineType' object. It should be seen as a real line,
 * taking, containing and emitting packets.
 *
 * @author Felix Wiemuth
 */
public class Link extends SimObject implements RouterOutPortInterface {

    private LineType type;
    private LinkedBlockingQueue<SimPacket> inQueue; //routers output queue for this link
    private LinkedBlockingQueue<LinePacket> packets; //packets on the line
    private Router dest; //router where to insert packets
    private RouterInPort destInPort; //router input port of 'dest'
    private int bandwidthAvailable; //indicate DATAUNITS that can be inserted into the line in the current step

    public Link(LineType type, Router dest, String name, SimulationInfo info) {
        super(name, info);
        this.type = type;
        this.dest = dest;
        this.destInPort = dest.newInPort();
        inQueue = new LinkedBlockingQueue<SimPacket>();
        packets = new LinkedBlockingQueue<LinePacket>();
        bandwidthAvailable = type.getBandwidth();
    }

    /**
     * Insert packet p into the output queue of the router for this port.
     *
     * @param p
     */
    public void insert(SimPacket p) {
        inQueue.add(p);
    }

//    public int getInQueueSize() {
//        return inQueue.size();
//    }
    /**
     * Do simulation step. This must be called in the specified order with other
     * 'simulateStep()' methods.
     */
    public void simulateStep() {
        //1. take new packets
        while (!inQueue.isEmpty() && bandwidthAvailable >= inQueue.peek().packet().getSize()) {
            SimPacket p = inQueue.poll();
            bandwidthAvailable -= p.packet().getSize();
            int timeToLeave = info().getTime() + type.getDelay() + Math.ceilDiv(p.packet().getSize(), type.getBandwidth());
            //add the packet
            packets.add(new LinePacket(p, timeToLeave));
            log(this, "Packet " + p.name() + " is now (completely) on the line!");
        }

        //2. update 'bandwidthAvailable' for next step
        if (!inQueue.isEmpty()) {
            //packet in real is inserted partially into the queue
            //for simulation: add it in the next step
            //--> transfer bandwidth left to next step
            bandwidthAvailable += type.getBandwidth();
        } else {
            bandwidthAvailable = type.getBandwidth();
        }

        //2. let packets emerge
        while (!packets.isEmpty() && packets.peek().getTimeToLeave() == info().getTime()) {
            log(this, "Packet " + packets.peek().getPacket().name() + " emerged at router " + dest.logname());
            destInPort.insert(packets.poll().getPacket());
        }
    }

    public Router getDest() {
        return dest;
    }

    public void printQueue() {
        StringBuilder sb = new StringBuilder("Line: ");
//        int last = 0;
//        int ttl = 0;
//        for (LinePacket p : packets) {
//            ttl = p.getTimeToLeave();
//            if (ttl < 0) {
//                ttl = 0;
//            }
//            last += ttl;
//            sb.append(last);
//            sb.append(" ");
//        }
        for (LinePacket p : packets) {
            sb.append(p.getTimeToLeave());
            sb.append(" ");
        }
        log(sb.toString());
    }

    @Override
    public int getQueueSize() {
        return inQueue.size();
    }
}
