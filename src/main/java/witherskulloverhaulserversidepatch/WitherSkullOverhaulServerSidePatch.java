package witherskulloverhaulserversidepatch;

import dev.rkarmaa.witherskulloverhaul.WitherSkullOverhaul;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import io.netty.buffer.ByteBuf;
import net.fabricmc.api.ModInitializer;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WitherSkullOverhaulServerSidePatch implements ModInitializer {
    public static final String MOD_ID = "witherskulloverhaulserversidepatch";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets(WitherSkullOverhaul.MOD_ID);
        PolymerResourcePackUtils.markAsRequired();
        LOGGER.info("Hello Fabric world!");
    }
}