package net.newgaea.mffs.common.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.newgaea.mffs.MFFS;
import net.newgaea.mffs.common.inventory.GeneratorContainer;
import net.newgaea.mffs.common.libs.LibContainer;

import static net.newgaea.mffs.common.libs.LibMisc.MOD_ID;

public class MFFSContainer {
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MOD_ID);

    public static void init()
    {
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


    public static final RegistryObject<ContainerType<GeneratorContainer>> GENERATOR=CONTAINERS.register(LibContainer.GENERATOR,() ->
            IForgeContainerType.create(
                    ((windowId, inv, data) -> new GeneratorContainer(windowId, MFFS.proxy.getClientWorld(),data.readBlockPos(),inv,MFFS.proxy.getClientPlayer()))
            ));

}