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

/**
 * The interface of a router for the routing algorithm to use.
 *
 * @author Felix Wiemuth
 */
public interface RouterInterface {

    public ArrayList<? extends RouterInPortInterface> inPorts();

    public ArrayList<? extends Link> outPorts();

    /**
     * Get available buffer in DATAUNITS.
     * @return 
     */
    public int bufferAvailable();

    /**
     * Get size of buffer in DATAUNITS.
     * @return 
     */
    public int bufferSize();

    /**
     * Ask for allocation of 'n' DATAUNITS bufferspace.
     * @param n DATAUNITS of buffer needed
     * @return true - enough buffer available, it is now allocated
     *         false - not enough buffer available: memory must not be used!
     */
    public boolean useBuffer(int n);

    /**
     * Indicate that 'n' DATAUNITS of bufferspace are not used anymore.
     * @param n DATAUNTIS of buffer to be freed
     */
    public void freeBuffer(int n);

    /**
     * Move the first packet on the queue of 'srcPort' to the
     * 'destPort' queue.
     * @param srcPort
     * @param destPort
     * @return false - if the queue of 'srcPort' is empty
     *         true - otherwise
     */
    public boolean push(RouterInPortInterface srcPort, RouterOutPortInterface destPort);

    /**
     * Move the first packet on the queue of 'srcPort' to the
     * LAN that is connected to the router (if one).
     * @param srcPort
     * @param lan
     * @return false - if the queue of 'srcPort' is empty or no LAN connected
     *         true - otherwise
     */
    public boolean pushToLAN(RouterInPortInterface srcPort);

    /**
     * Move the first packet on the queue that contains the packet from the
     * connected LAN to the 'destPort' queue.
     * @param destPort
     * @return false - if the queue for the packets of the LAN is empty
     *         true - otherwise
     */
    public boolean pushFromLAN(RouterOutPortInterface destPort);

    /**
     * Discard the first packet of the queue of 'inPort'.
     * @param inPort 
     */
    public void discard(RouterInPortInterface inPort);
}
