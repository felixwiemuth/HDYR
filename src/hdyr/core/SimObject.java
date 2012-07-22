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
public abstract class SimObject {
    private String name;
    private final SimulationInfo info;
    
    public SimObject(String name, SimulationInfo info) {
        this.name = name;
        this.info = info;
    }
    
    //public String id();
    public String logname() {
        return name;
    }
    
    public SimulationInfo info() {
        return info;
    }
}
