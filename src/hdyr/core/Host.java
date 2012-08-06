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
 * A 'Host' represents a transport layer or a collection
 * of those on a LAN, connected to one router.
 * The router is set when one is created using this host.
 * @author Felix Wiemuth
 */
public class Host {

    private Router router; //the router this host is connected to

    public void insertFromRouter(SimPacket p) {
    }

    public boolean toRouter(SimPacket p) {
        return router.insertFromLAN(p);
    }

    public void setRouter(Router router) {
        this.router = router;
    }
}
