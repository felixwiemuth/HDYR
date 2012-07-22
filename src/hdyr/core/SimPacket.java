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
 * The packet type used by the simulation.
 * It encapsulates a 'Packet' object which is the real packet
 * the routing algorithm deals with etc.
 * @author Felix Wiemuth
 */
public class SimPacket {

    private Packet packet; //the real packet to be transported
    private String id; //name

    public SimPacket(Packet packet, String id) {
        //super("", info); //TODO name?
        this.id = id;
        this.packet = packet;
    }

    public Packet packet() {
        return packet;
    }

    public String name() {
        return id;
    }
}
