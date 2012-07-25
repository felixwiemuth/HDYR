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

import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Felix Wiemuth
 */
public class RouterInPort implements RouterInPortInterface {

    private Router router;
    private LinkedBlockingQueue<SimPacket> inQueue = new LinkedBlockingQueue<SimPacket>();

    public RouterInPort(Router router) {
        this.router = router;
    }

    @Override
    public boolean isEmpty() {
        return inQueue.isEmpty();
    }
    
    @Override
    public Packet peek() {
        if (inQueue.isEmpty()) {
            return null;
        }
        return inQueue.peek().packet();
    }

    @Override
    public int getQueueSize() {
        return inQueue.size();
    }

    public void insert(SimPacket p) {
        inQueue.add(p);
    }

    //TODO more to do?
    public void insert(Packet p) {
        inQueue.add(new SimPacket(p, "P" + router.info().getPacketID()));
    }

    public SimPacket peekSimPacket() {
        return inQueue.peek();
    }

    public SimPacket poll() {
        return inQueue.poll();
    }
}
