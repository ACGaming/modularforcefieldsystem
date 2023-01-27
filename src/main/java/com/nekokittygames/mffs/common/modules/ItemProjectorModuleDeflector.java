/*  
    Copyright (C) 2012 Thunderdark

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
    Contributors:
    Thunderdark - initial implementation
 */

package com.nekokittygames.mffs.common.modules;

import com.nekokittygames.mffs.api.PointXYZ;
import com.nekokittygames.mffs.common.IModularProjector;
import com.nekokittygames.mffs.common.IModularProjector.Slots;
import com.nekokittygames.mffs.common.options.*;
import com.nekokittygames.mffs.libs.LibItemNames;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;

import java.util.Set;

public class ItemProjectorModuleDeflector extends ModuleBase {
	public ItemProjectorModuleDeflector() {
		super(LibItemNames.MODULE_DEFLECTOR);
		this.setForceFieldModuleType(5);
	}


	@Override
	public boolean supportsDistance() {
		return true;
	}

	@Override
	public boolean supportsStrength() {
		return false;
	}

	@Override
	public boolean supportsMatrix() {
		return true;
	}

	@Override
	public void calculateField(IModularProjector projector, Set<PointXYZ> ffLocs) {

		int tpx = 0;
		int tpy = 0;
		int tpz = 0;

		for (int x1 = -projector.countItemsInSlot(Slots.FocusLeft); x1 < projector
				.countItemsInSlot(Slots.FocusRight) + 1; x1++) {
			for (int z1 = -projector.countItemsInSlot(Slots.FocusUp); z1 < projector
					.countItemsInSlot(Slots.FocusDown) + 1; z1++) {
				switch (projector.getSide()) {
					case DOWN:
						tpy = -projector.countItemsInSlot(Slots.Distance) - 1;
						tpx = x1;
						tpz = z1;
						break;

					case UP:
						tpy = projector.countItemsInSlot(Slots.Distance) + 1;
						tpx = x1;
						tpz = z1;
						break;

					case NORTH:
						tpz = -projector.countItemsInSlot(Slots.Distance) - 1;
						tpy = -z1;
						tpx = -x1;
						break;

					case SOUTH:
						tpz = projector.countItemsInSlot(Slots.Distance) + 1;
						tpy = -z1;
						tpx = x1;
						break;

					case WEST:
						tpx = -projector.countItemsInSlot(Slots.Distance) - 1;
						tpy = -z1;
						tpz = x1;
						break;
					case EAST:
						tpx = projector.countItemsInSlot(Slots.Distance) + 1;
						tpy = -z1;
						tpz = -x1;
						break;
				}

				ffLocs.add(new PointXYZ(new BlockPos(tpx, tpy, tpz), 0));
			}
		}

	}

	public static boolean supportsOption(ItemProjectorOptionBase item) {

		if (item instanceof ItemProjectorOptionBlockBreaker)
			return true;
		if (item instanceof ItemProjectorOptionCamoflage)
			return true;
		if (item instanceof ItemProjectorOptionTouchDamage)
			return true;
		return item instanceof ItemProjectorOptionLight;

	}

	@Override
	public boolean supportsOption(Item item) {

		if (item instanceof ItemProjectorOptionBlockBreaker)
			return true;
		if (item instanceof ItemProjectorOptionCamoflage)
			return true;
		if (item instanceof ItemProjectorOptionTouchDamage)
			return true;
		return item instanceof ItemProjectorOptionLight;
	}

}