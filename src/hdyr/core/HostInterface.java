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
 * The interface of a host for the transport protocol to use.
 *
 * @author Felix Wiemuth
 */
public interface HostInterface {

    /**
     * Look at the first packet of the queue of packets to be sent.
     *
     * @return
     */
    public Packet peek();

    /**
     * Send the first packet of the queue of packets to be sent to the connected
     * router.
     *
     * @return <code>false</code> if the queue of packets to send is empty;
     *         <code>true</code> otherwise
     */
    public boolean push();
}
