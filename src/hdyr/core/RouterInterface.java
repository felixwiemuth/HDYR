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

    //TODO throws exception
    public void push(RouterInPortInterface srcPort, RouterOutPortInterface destPort);
}
