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

import hdyr.util.Logger;

/**
 *
 * @author Felix Wiemuth
 */
public abstract class SimObject {

    private String name;
    private final Director director;
    private Logger logger = new Logger(this); //logs the activity of the respective simulation object

    public SimObject(String name, Director director) {
        this.name = name;
        this.director = director;
    }

    public Logger logger() {
        return logger;
    }

    /**
     * Get the display name of the type of this SimObject.
     *
     * @return
     */
    public abstract String type();

    //public String id();
    public String logname() {
        return name;
    }

    public Director director() {
        return director;
    }
}
