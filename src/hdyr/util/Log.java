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
 * Functions to pass debug information to standard output.
 * @author Felix Wiemuth
 */
public class Log {

//    public static void log(SimObject s, String msg) {
//        //Logger.getLogger(s.getClass().getName()).log(Level.INFO, msg);
//        System.out.println("[" + s.info().getTime() + "] " + s.logname() + ": " + msg);
//    }
    public static void log(Object o) {
        System.out.println(o);
    }

    public static void err(Object o) {
        log("[ERROR] " + o);
    }

    public static void warn(Object o) {
        log("[WARNING] " + o);
    }

    public static void info(Object o) {
        log("[INFO] " + o);
    }
}
