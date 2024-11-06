package witherskulloverhaulserversidepatch.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.rkarmaa.witherskulloverhaul.item.ModItems;
import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import witherskulloverhaulserversidepatch.WitherSkullOverhaulServerSidePatch;
import witherskulloverhaulserversidepatch.item.SimpleModeledPolymerItem;

@Mixin(ModItems.class)
public class ModItemsMixin {
    @Redirect(method = "createItem", at = @At(value = "NEW", target = "(Lnet/minecraft/item/Item$Settings;)Lnet/minecraft/item/Item;"))
    private static Item createItem(Item.Settings settings, @Local(argsOnly = true) String name) {
        return new SimpleModeledPolymerItem(
                settings,
                Items.ALLIUM
        ).registered(name);
    }

    @Inject(method = "registerModItems", at = @At("TAIL"), remap = false)
    private static void registerModItems(CallbackInfo ci) {
        PolymerItemGroupUtils.registerPolymerItemGroup(
                Identifier.of(WitherSkullOverhaulServerSidePatch.MOD_ID, "wither-skull-overhaul"),
                PolymerItemGroupUtils.builder()
                        .displayName(Text.literal("Wither Skull Overhaul"))
                        .icon(() -> new ItemStack(Items.WITHER_SKELETON_SKULL))
                        .entries((context, entries) -> entries.add(ModItems.WITHER_SKULL_FRAGMENT)).build()
        );
    }
}
