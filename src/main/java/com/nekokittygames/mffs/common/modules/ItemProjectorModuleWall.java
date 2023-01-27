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

import java.util.Set;

import com.nekokittygames.mffs.api.PointXYZ;
import com.nekokittygames.mffs.common.IModularProjector;
import com.nekokittygames.mffs.common.IModularProjector.Slots;
import com.nekokittygames.mffs.common.options.*;
import com.nekokittygames.mffs.libs.LibItemNames;
import com.nekokittygames.mffs.libs.LibMisc;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class ItemProjectorModuleWall extends ModuleBase {
	public ItemProjectorModuleWall() {
		super(LibItemNames.MODULE_WALL);
		this.setForceFieldModuleType(1);
	}
	public ItemProjectorModuleWall(final String itemName)
	{
		super(itemName);
		this.setForceFieldModuleType(1);
	}

	@Override
	public boolean supportsDistance() {
		return true;
	}

	@Override
	public boolean supportsStrength() {
		return true;
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
			for (int z1 = -projector.countItemsInSlot(Slots.FocusDown); z1 < projector
					.countItemsInSlot(Slots.FocusUp) + 1; z1++) {
				for (int y1 = 1; y1 < projector
						.countItemsInSlot(Slots.Strength) + 1 + 1; y1++) {
					switch (projector.getSide()) {
						case UP:
							tpy = -y1 - projector.countItemsInSlot(Slots.Distance);
							tpx = x1;
							tpz = -z1;
							break;

						case DOWN:
							tpy = y1 + projector.countItemsInSlot(Slots.Distance);
							tpx = x1;
							tpz = -z1;
							break;

						case NORTH:
							tpz = -y1 - projector.countItemsInSlot(Slots.Distance);
							tpx = -x1;
							tpy = z1;
							break;

						case SOUTH:
							tpz = y1 + projector.countItemsInSlot(Slots.Distance);
							tpx = x1;
							tpy = z1;
							break;

						case WEST:
							tpx = -y1 - projector.countItemsInSlot(Slots.Distance);
							tpz = x1;
							tpy = z1;
							break;
						case EAST:
							tpx = y1 + projector.countItemsInSlot(Slots.Distance);
							tpz = -x1;
							tpy = z1;
							break;
					}

					if ((projector.getSide() == EnumFacing.UP ||
							projector.getSide() == EnumFacing.DOWN) &&
							(tpx == 0 || tpz == 0) ||
							(projector.getSide() == EnumFacing.NORTH ||
									projector.getSide() == EnumFacing.SOUTH) &&
									(tpx == 0 || tpy == 0) ||
							(projector.getSide() == EnumFacing.WEST ||
									projector.getSide() == EnumFacing.EAST) &&
									(tpz == 0 || tpy == 0))

					{
						ffLocs.add(new PointXYZ(new BlockPos(tpx, tpy, tpz), 0));
					}
				}
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