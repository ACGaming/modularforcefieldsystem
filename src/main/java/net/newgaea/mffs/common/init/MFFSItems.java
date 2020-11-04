package net.newgaea.mffs.common.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.newgaea.mffs.common.libs.LibBlocks;
import net.newgaea.mffs.common.libs.LibItems;
import net.newgaea.mffs.common.libs.LibMisc;
import net.newgaea.mffs.common.misc.ItemGroupMFFS;

public class MFFSItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LibMisc.MOD_ID);

    public static void init()
    {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<Item> MONAZIT_ORE = ITEMS.register(LibBlocks.MONAZIT_ORE,() ->
            new BlockItem(MFFSBlocks.MONAZIT_ORE.get(),getDefaultProperties())
    );

    public static final RegistryObject<Item> GENERATOR = ITEMS.register(LibBlocks.GENERATOR, () ->
            new BlockItem(MFFSBlocks.GENERATOR.get(),getDefaultProperties())
    );

    public static final RegistryObject<Item> MONAZIT_CRYSTAL = ITEMS.register(LibItems.MONAZIT_CRYSTAL, () ->
            new Item(getDefaultProperties().setNoRepair()));

    protected static Item.Properties getDefaultProperties() {
        return new Item.Properties().group(ItemGroupMFFS.GetInstance()).maxStackSize(64);
    }
}