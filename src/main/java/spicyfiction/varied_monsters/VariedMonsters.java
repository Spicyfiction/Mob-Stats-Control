package spicyfiction.varied_monsters;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class VariedMonsters implements ModInitializer {
    public static final String MOD_ID = "varied_monsters";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Config configFile = new Config(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Varied Monsters initialized.");

    }

}
