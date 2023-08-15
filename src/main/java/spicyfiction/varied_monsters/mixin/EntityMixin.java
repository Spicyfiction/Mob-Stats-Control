package spicyfiction.varied_monsters.mixin;



import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityDispatcher;
import net.minecraft.core.entity.EntityLiving;
import org.spongepowered.asm.mixin.Mixin;
import spicyfiction.varied_monsters.EntityStatChanger;
import spicyfiction.varied_monsters.EntityStats;
import spicyfiction.varied_monsters.Modifiers;
import spicyfiction.varied_monsters.VariedMonsters;
import spicyfiction.varied_monsters.interfaces.IEntityMixin;
import spicyfiction.varied_monsters.mixin.accessor.EntityLivingAccessor;
import spicyfiction.varied_monsters.mixin.accessor.EntityMonsterAccessor;

@Mixin(value = Entity.class, remap = false)
public abstract class EntityMixin implements IEntityMixin {

    @Override
    public void changeStats() {

        Entity thisMob = (Entity)(Object) this;
        EntityStatChanger entityStatChanger = new EntityStatChanger((Entity)(Object) this);
        String whoAmI = EntityDispatcher.getEntityString(thisMob);

        Modifiers defaultM = new Modifiers();
        EntityStats defaultS = new EntityStats(defaultM,defaultM,defaultM);

        Modifiers healthModifier = VariedMonsters.configFile.readFromConfig(whoAmI,defaultS).health;
        Modifiers damageModifier = VariedMonsters.configFile.readFromConfig(whoAmI,defaultS).attackStrength;
        Modifiers speedModifier = VariedMonsters.configFile.readFromConfig(whoAmI,defaultS).moveSpeed;

        entityStatChanger.setHealth(healthModifier).setDamage(damageModifier).setSpeed(speedModifier);



    }



}
