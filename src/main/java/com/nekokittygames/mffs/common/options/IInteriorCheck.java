package com.nekokittygames.mffs.common.options;

import com.nekokittygames.mffs.api.PointXYZ;
import com.nekokittygames.mffs.common.tileentity.TileEntityProjector;
import net.minecraft.world.World;

public interface IInteriorCheck {

	void checkInteriorBlock(PointXYZ png, World world,
			TileEntityProjector Projector);

}
