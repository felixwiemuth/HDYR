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

import hdyr.util.XML;
import hdyr.util.XML.IncorrectValueException;
import hdyr.util.XML.MissingValueException;
import hdyr.util.XML.WrongElementException;
import org.jdom2.Element;

/**
 *
 * @author Felix Wiemuth
 */
public class SimPacketBlock {

    private int time;
    private boolean timeRelative; //'true': time relative to previous packet entry
    private int n; //amount
    private int d; //delay
    private String src; //source host
    private String dest; //destination host
    private String name; //optional name of the packets

    public SimPacketBlock(Element e) throws WrongElementException, MissingValueException, IncorrectValueException {
        if (!e.getName().equals("p")) {
            throw new WrongElementException();
        }

        //get time and relative
        try {
            time = XML.getIntVal(e, "t");
            timeRelative = false;
        } catch (MissingValueException ex) {
            //"t" not given - "td" must be there
            //If not, 'MissingValueException' is thrown which cancels constructor
            time = XML.getIntVal(e, "td");
            timeRelative = true;
        }

        //get amount
        try {
            n = XML.getIntVal(e, "n");
        } catch (MissingValueException ex) {
            n = 1; //default
        }

        //get delay
        try {
            d = XML.getIntVal(e, "d");
        } catch (MissingValueException ex) {
            d = 0; //default
        }

        //get source and destination
        src = e.getAttributeValue("src");
        if (src == null) {
            throw new MissingValueException("src");
        }
        dest = e.getAttributeValue("dest");
        if (dest == null) {
            throw new MissingValueException("dest");
        }

        //get name (optional)
        name = e.getAttributeValue("name");
    }
}
