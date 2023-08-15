package spicyfiction.varied_monsters.mixin.accessor;


import net.minecraft.core.entity.EntityLiving;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = EntityLiving.class, remap = false)
public interface EntityLivingAccessor {

    @Accessor float getMoveSpeed();

    @Accessor("moveSpeed")
    void setMoveSpeed(float newValue);


}
