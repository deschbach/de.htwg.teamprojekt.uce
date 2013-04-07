/*
 * Copyright (c) 2012 Alexander Diener,
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.fhkn.in.uce.relaying;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.fhkn.in.uce.plugininterface.NATTraversalTechniqueMetaData;
import de.fhkn.in.uce.plugininterface.message.NATTraversalTechniqueAttribute;
import de.fhkn.in.uce.relaying.message.RelayingAttribute;

public final class RelayingMetaDataTest {
    private NATTraversalTechniqueMetaData metaData;

    @Before
    public void setUp() {
        this.metaData = new Relaying().getMetaData();
    }

    @Test
    public void testGetTraversaledNatBehavior() throws Exception {
        final int expectedResult = 625;
        final int actualResult = this.metaData.getTraversaledNATSituations().size();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetEncoded() {
        NATTraversalTechniqueAttribute attr = new RelayingAttribute();
        assertEquals(attr, this.metaData.getAttribute());
    }
}
