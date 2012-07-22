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

import java.util.ArrayList;
import static hdyr.util.Log.*;

/**
 *
 * @author Felix Wiemuth
 */
public class Router extends SimObject implements RouterInterface {

    private int buffersize; //buffer size in DATAUNIT - this is an "accumulated" buffer, so all buffers share this memory
    private int buffer; //available buffer
    private RoutingAlgorithm alg;
    //ports
    //private ArrayList<Link> inPorts; //doesn´t have to know??
    private ArrayList<Link> outPorts;
    //port queues
    //private LinkedBlockingQueue<Packet> inQueue; //packets inserted by link object
    private ArrayList<RouterInPort> inPorts;

    public Router(int buffersize, String name, RoutingAlgorithm alg, SimulationInfo info) {
        super(name, info);
        this.buffersize = buffersize;
        this.alg = alg;
        alg.setRouter(this);
        buffer = buffersize;
        outPorts = new ArrayList<Link>();
        inPorts = new ArrayList<RouterInPort>();
        alg.init();
    }
    
    public RouterInPort newInPort() {
        inPorts.add(new RouterInPort(this));
        return inPorts.get(inPorts.size()-1);
    }

    public void addLink(LineType type, Router dest) {
        outPorts.add(new Link(type, dest, logname() + "-" + dest.logname(), info()));
    }
    
    //TODO make Host.connect(LineType type, Router router) -- NOTE: HOST ^= LAN
    public void addInPort() {
        inPorts.add(new RouterInPort(this));
    }

    

    /**
     * Update 'buffer' to reflect the currently available buffer
     */
    public void updateBuffer() {
        buffer = buffersize;
        for (RouterInPort in : inPorts) {
            buffer -= in.getQueueSize();
        }
        for (Link link : outPorts) {
            buffer -= link.getQueueSize();
        }
    }

//    public Packet take(Link link) {
//        
//    }
    public void simulateStep() {
        alg.onSimulationStep();
    }

//    @Override
//    public int getHighestPort() {
//        return outPorts.size() - 1;
//    }
//
//    @Override
//    public Packet peek() {
//        return inQueue.peek();
//    }
//
//    @Override
//    public void push(int port) throws IndexOutOfBoundsException {
//        if (port < 0 || port > getHighestPort())
//            throw new IndexOutOfBoundsException("There is no port " + port + " at router " + logname() + "!");
//        log(this, "Packet inserted into outgoing queue to router " + outPorts.get(port).getDest().logname() + ": " + inQueue.peek().logname());
//        outPorts.get(port).insert(inQueue.poll());
//    }

    @Override
    public ArrayList<RouterInPort> inPorts() {
        return inPorts;
    }
    
    @Override
    public ArrayList<Link> outPorts() {
        return outPorts;
    }
    
    
    

    @Override
    public void push(RouterInPortInterface srcPort, RouterOutPortInterface destPort) {
        RouterInPort src = (RouterInPort) srcPort;
        Link dest = (Link) destPort;
        log(this, "Packet inserted into outgoing queue to router " + dest.getDest().logname() + ": " + src.peekSimPacket().name());
        dest.insert( src.poll() );
    }
    
    
}
