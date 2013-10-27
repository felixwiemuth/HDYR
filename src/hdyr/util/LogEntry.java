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
package hdyr.util;

import hdyr.core.SimObject;

/**
 *
 * @author Felix Wiemuth
 */
public class LogEntry {

    private SimObject source; //simulation object that made the entry
    private int time; //corresponding simulation time
    private String message;

    public LogEntry(SimObject source, int time, String message) {
        this.source = source;
        this.time = time;
        this.message = message;
    }

    public int time() {
        return time;
    }

    public String msg() {
        return message;
    }

    /**
     * Get formatted log entry with time and message.
     *
     * @return
     */
    public String format() {
        return "[" + time + "] " + source.type() + " " + source.logname() + ": " + message;
    }
}
