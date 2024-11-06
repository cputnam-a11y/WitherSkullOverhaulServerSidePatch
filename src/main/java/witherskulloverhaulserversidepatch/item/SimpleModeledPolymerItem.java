package witherskulloverhaulserversidepatch.item;

import dev.rkarmaa.witherskulloverhaul.WitherSkullOverhaul;
import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Optional;

public class SimpleModeledPolymerItem extends SimplePolymerItem {
    public final static HashMap<SimplePolymerItem, PolymerModelData> MODEL_DATA = new HashMap<>();
    private final Item polymerItem;

    public SimpleModeledPolymerItem(Settings settings, Item polymerItem) {
        super(settings, polymerItem);
        this.polymerItem = polymerItem;
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return polymerItem;
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return Optional.ofNullable(
                        MODEL_DATA.get(this)
                )
                .map(PolymerModelData::value)
                .orElse(-1);
    }

    public SimpleModeledPolymerItem registered(String id) {
        return registered(id, "item");
    }

    public SimpleModeledPolymerItem registered(String id, String modelPath) {
        MODEL_DATA.put(this,
                PolymerResourcePackUtils.requestModel(
                        this.polymerItem,
                        Identifier.of(
                                WitherSkullOverhaul.MOD_ID,
                                "%s/%s".formatted(modelPath, id)
                        )
                )
        );
        return this;
    }

}
