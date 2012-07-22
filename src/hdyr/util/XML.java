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

import java.io.File;
import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import static hdyr.util.Log.log;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felix Wiemuth
 */
public class XML {
    
    public static class IncorrectValueException extends Exception {

        public IncorrectValueException() {
        }

        public IncorrectValueException(String msg) {
            super(msg);
        }
    }

    public static class MissingValueException extends Exception {

        public MissingValueException() {
        }

        public MissingValueException(String msg) {
            super(msg);
        }
    }

    public static class WrongElementException extends Exception {

        public WrongElementException() {
        }
    }

    public static Element getRoot(String filename) throws LoadXMLException {
        File file = new File(filename);
        SAXBuilder sax = new SAXBuilder();
        try {
            Document config = sax.build(file);
            return config.getRootElement();
        } catch (JDOMException ex) {
            log("Cannot parse XML: " + ex.getMessage());
            throw new LoadXMLException();
        } catch (IOException ex) {
            log("Cannot load file: " + ex.getLocalizedMessage());
            throw new LoadXMLException();
        }
    }
    
    public static int getIntVal(Element e, String attname) throws MissingValueException, IncorrectValueException{
        String val = e.getAttributeValue(attname);
        if (val == null)
            throw new MissingValueException(attname);
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException ex) {
            throw new IncorrectValueException(attname);
        }
    }

    public static class LoadXMLException extends Exception {

        public LoadXMLException() {
        }
    }
}
