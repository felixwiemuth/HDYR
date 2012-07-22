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
public abstract class RoutingAlgorithm {

    private RouterInterface router;

    public void setRouter(RouterInterface router) {
        this.router = router;
    }

//    /**
//     * This is called when a packet arrives at the router.
//     * Hence the routing algorithm can rely on the fact
//     * that a packet is available for processing.
//     */
//    public abstract void onPacketArrive();
    /**
     * This is called before the algorithm is used the first time,
     * but after parent field 'router' is initialized.
     */
    public void init() {
    }

    public RouterInterface router() {
        return router;
    }

    /**
     * This is called every simulation step.
     * If a packet arrived in this step,
     * 'onPacketArrive()' is called before
     * and 'onSimulationStep()' is always the last method being called.
     */
    public abstract void onSimulationStep();
}
