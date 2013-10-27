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
 *
 * @author Felix Wiemuth
 */
public interface Director {

    /**
     * Get the current simulation time.
     *
     * @return TIMEUNITS passed from the beginning of the simulation
     */
    public int getTime();

    /**
     * Obtain a new packet ID.
     *
     * @return
     */
    public String getNewPacketID();

    /**
     * Add entry to main log.
     *
     * @param s
     */
    public void log(String s);
}
