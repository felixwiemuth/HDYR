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
import java.util.LinkedList;

/**
 * A logging utility to log simulation activity.
 * @author Felix Wiemuth
 */
public class Logger {

    private SimObject source; //simulation object that loggs with this instance
    private boolean toConsole = false; //also log to console
    private boolean toMain = false; //also pass to main logger
    private LinkedList<String> log = new LinkedList<String>();
    
    public Logger(SimObject source) {
        this.source = source;
    }

    public void log(Object o) {
        String entry = String.valueOf(o);
        log.add(entry);
        if (toConsole) {
            System.out.println(source.logname() + ": " + entry);
        }
        if (toMain) {
            source.info().log(entry);
        }
    }

    public void setConsoleLogging(boolean toConsole) {
        this.toConsole = toConsole;
    }

    public void setMainLogging(boolean toMain) {
        this.toMain = toMain;
    }

    public LinkedList<String> getLog() {
        return log;
    }
}
