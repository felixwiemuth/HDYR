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
 * A network layer packet to be routed.
 * It is loosely based on the IPv6 packet format but simplified.
 * Extension headers can be realized by extending this class.
 * @author Felix Wiemuth
 */
public class Packet {

    private int size; //size of packet in DATAUNITS
    private String src; //source address
    private String dest; //destination address

    public Packet(int size, String src, String dest) {
        this.size = size;
        this.src = src;
        this.dest = dest;
    }

    /**
     * Get packet size in DATAUNIT.
     * @return 
     */
    public int getSize() {
        return size;
    }

    /**
     * Get source address.
     * @return 
     */
    public String getSrc() {
        return src;
    }

    /**
     * Get destination address.
     * @return 
     */
    public String getDest() {
        return dest;
    }
}
