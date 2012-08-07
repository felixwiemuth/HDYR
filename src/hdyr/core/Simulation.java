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

import static hdyr.util.Log.err;
import hdyr.util.XML;
import hdyr.util.XML.IncorrectValueException;
import hdyr.util.XML.LoadXMLException;
import hdyr.util.XML.MissingValueException;
import hdyr.util.XML.WrongElementException;
import java.util.ArrayList;
import org.jdom2.Element;

/**
 *
 * @author Felix Wiemuth
 */
public class Simulation {
    //TODO: blocks may start simultaneously and emit packets alternately

    private ArrayList<SimPacketBlock> blocks = new ArrayList<SimPacketBlock>();
    private String name;
    private Integer time; //simulation time in TIMEUNITS

    public Simulation(String filename) throws LoadXMLException {
        Element root = XML.getRoot(filename);

        for (Element sim : root.getChildren("simulation")) {
            for (Element p : sim.getChildren()) {
                try {
                    blocks.add(new SimPacketBlock(p));
                } catch (WrongElementException ex) {
                    err("Unexpected XML Element: " + p.getName());
                } catch (MissingValueException ex) {
                    err("Value " + ex.getMessage() + " is missing!");
                } catch (IncorrectValueException ex) {
                    err("Value " + ex.getMessage() + " is incorrect!");
                }
            }

        }

    }

//    public Simulation(String name) {
//        this.name = name;
//    }
    public void addPacket(SimPacketBlock p) {
        blocks.add(p);
    }
}
