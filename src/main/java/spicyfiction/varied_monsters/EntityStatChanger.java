package spicyfiction.varied_monsters;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.monster.EntityMonster;
import spicyfiction.varied_monsters.mixin.accessor.EntityLivingAccessor;
import spicyfiction.varied_monsters.mixin.accessor.EntityMonsterAccessor;

public class EntityStatChanger {

    Entity entity;

    public EntityStatChanger(Entity entity) {
        this.entity = entity;
    }

    public EntityStatChanger setHealth(Modifiers modifier) {

        Entity temp = this.entity;
        if(temp instanceof EntityLiving) {
            ((EntityLiving) temp).health = (int)Math.floor(modifier.Modify(((EntityLiving) temp).health));
        }
        return this;
    }

    public EntityStatChanger setDamage(Modifiers modifier) {
        Entity temp = this.entity;
        if(temp instanceof EntityMonster) {
            ((EntityMonsterAccessor) temp).setAttackStrength((int)Math.floor(modifier.Modify(((EntityMonsterAccessor) temp).getAttackStrength())));
        }
        return this;
    }

    public EntityStatChanger setSpeed(Modifiers modifier) {
        Entity temp = this.entity;
        if(temp instanceof EntityLiving) {
            ((EntityLivingAccessor) temp).setMoveSpeed(modifier.Modify(((EntityLivingAccessor) temp).getMoveSpeed()));
        }
        return this;
    }


}
