package spicyfiction.varied_monsters.mixin.accessor;


import net.minecraft.core.entity.monster.EntityMonster;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = EntityMonster.class, remap = false)
public interface EntityMonsterAccessor {

    @Accessor int getAttackStrength();

    @Accessor("attackStrength")
    void setAttackStrength(int newValue);


}
