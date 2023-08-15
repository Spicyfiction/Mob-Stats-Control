package spicyfiction.varied_monsters.interfaces;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

public interface IEntityMixin {

    Random random = new Random();

    void changeStats();
}
