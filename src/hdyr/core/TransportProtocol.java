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

/**
 * This describes how hosts communicate with each other.
 * It simulates the transport layer protocol.
 * The simulation mainly focuses on the network so
 * the transport protocol does not necessarily have to be
 * sophisticated.
 * @author Felix Wiemuth
 */
public abstract class TransportProtocol {

    private HostInterface host;

    public void setHost(HostInterface host) {
        this.host = host;
    }

    public HostInterface host() {
        return host;
    }

    /**
     * This is event is executed when a packet was received from the
     * connected router.
     * The content can be checked for acknowledgements etc.
     * @param packet 
     */
    public abstract void onPacketReceived(Packet packet);

    /**
     * This event is executed when a new packet to send comes in
     * from the application (simulation).
     * This is only for information, the protocol can look at
     * the first packet to be sent using the 'HostInterface'.
     * @param packet 
     */
    public abstract void onPacketFromApplication(Packet packet);

    /**
     * This is called every simulation step.
     */
    public abstract void onSimulationStep();
}
