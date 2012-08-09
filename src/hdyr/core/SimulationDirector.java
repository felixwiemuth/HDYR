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
import java.util.List;

/**
 * Manage simulation: time, IDs, Logging, ...
 * @author Felix Wiemuth
 */
public class SimulationDirector implements Director { //TODO split into interface: SimInfo, class: SimHandlerF / Director

    private int time = 0;
    private int nextPacketID = 0;
    //All simulation objects
    private List<Host> hosts = new ArrayList<Host>();
    private ArrayList<Router> routers = new ArrayList<Router>();
    private List<Link> links = new ArrayList<Link>();

    public void simulate(int time) {
        for (int i = 0; i < time; i++) {
            step();
        }
    }
    
    public void step() {
        System.out.println("TIME: " + time); //DEBUG
        for (Host h : hosts) {
            h.simulateStep();
        }
        for (Router r : routers) {
            r.simulateStep();
        }
        for (Link l : links) {
            l.simulateStep();
        }
        time++;
    }
    
    public void addHost(Host host) {
        hosts.add(host);
    }
    
    public void addRouter(Router router) {
        routers.add(router);
    }
    
    //used by 'Router'
    public void addLink(Link link) {
        links.add(link);
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public String getNewPacketID() {
        return "P" + nextPacketID++;
    }

    @Override
    public void log(String s) {
        //TODO implement
    }
}
