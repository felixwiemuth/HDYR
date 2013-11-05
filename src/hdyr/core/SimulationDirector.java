/*
 * Copyright (C) 2012 - 2013 Felix Wiemuth
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

import hdyr.analyse.SimulationWatch;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manage simulation: time, IDs, Logging, ...
 *
 * @author Felix Wiemuth
 */
public class SimulationDirector implements Director { //TODO split into interface: SimInfo, class: SimHandlerF / Director

    private int time = 0;
    private int nextPacketID = 0;
    private boolean loggingDefault_toConsole = true;
    private boolean loggingDefault_toMain = true;
    //All simulation objects
    private List<Host> hosts = new ArrayList<>();
    private ArrayList<Router> routers = new ArrayList<>();
    private List<Link> links = new ArrayList<>();

    public void simulate(int time) {
        for (int i = 0; i < time; i++) {
            step();
        }
    }

    public void step() {
        System.out.println("TIME: " + time); //DEBUG
        for (Host h : hosts) {
            h.step();
        }
        for (Router r : routers) {
            r.step();
        }
        for (Link l : links) {
            l.step();
        }
        time++;
    }

    public void addHost(Host host) {
        hosts.add(host);
        host.logger().setConsoleLogging(loggingDefault_toConsole);
        host.logger().setMainLogging(loggingDefault_toMain);
    }

    public void addRouter(Router router) {
        routers.add(router);
        router.logger().setConsoleLogging(loggingDefault_toConsole);
        router.logger().setMainLogging(loggingDefault_toMain);
    }

    //used by 'Router'
    public void addLink(Link link) {
        links.add(link);
        link.logger().setConsoleLogging(loggingDefault_toConsole);
        link.logger().setMainLogging(loggingDefault_toMain);
    }

    public void addWatch(Class<? extends SimulationWatch> watchType) {
        try {
            addWatch(hosts, (Class<SimulationWatch<Host>>) watchType, Host.class);
        } catch (ClassCastException ex) {
            try {
                addWatch(routers, (Class<SimulationWatch<Router>>) watchType, Router.class);
            } catch (ClassCastException ex2) {
                try {
                    addWatch(links, (Class<SimulationWatch<Link>>) watchType, Link.class);
                } catch (ClassCastException ex3) {
                    throw ex3; //serious problem
                }
            }
        }
    }

//    public void addHostWatch(Class<SimulationWatch<Host>> watch) {
//        addWatch(hosts, watch);
//    }
//
//    /**
//     * Add a RouterWatch to be activated at all routers.
//     *
//     * @param watch
//     */
//    public void addRouterWatch(Class<SimulationWatch<Router>> watch) {
//        addWatch(routers, watch);
//    }
//
//    public void addLinkWatch(Class<SimulationWatch<Link>> watch) {
//        addWatch(links, watch);
//    }
    /**
     * General method to add watches. The interface provides a seperate method
     * per watch type to ensure only applicable watches are added.
     *
     * @param simObjects
     * @param watch
     */
    private void addWatch(List<? extends SimObject> simObjects, Class<? extends SimulationWatch> watchType, Class T) {
        //TODO test
        for (SimObject simObject : simObjects) {
            try {
                simObject.addWatch(watchType.getConstructor(T).newInstance(simObject));
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                Logger.getLogger(SimulationDirector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
