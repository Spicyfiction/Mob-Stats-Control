package spicyfiction.varied_monsters.mixin;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import spicyfiction.varied_monsters.interfaces.IEntityMixin;

@Mixin(value = World.class, remap = false)
public class WorldMixin {

    @Inject(method = "entityJoinedWorld", at = @At("RETURN"),remap = false)
    public void newStats(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        ((IEntityMixin) entity).changeStats();
    }


}
