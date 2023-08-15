package spicyfiction.varied_monsters;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.entity.EntityDispatcher;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {

    public String modId;
    private final File configFile;

    public Config(String modId) {
        Path configDir = FabricLoader.getInstance().getConfigDir();
        this.modId = modId;
        this.configFile = new File(configDir + "/" + modId + ".cfg");
        if(!configFile.exists()) {
            writeConfig();
        }
    }

    private void writeConfig() {
        try {
            File configDir = FabricLoader.getInstance().getConfigDir().toFile();
            configDir.mkdir();
            BufferedWriter configWriter = new BufferedWriter(new FileWriter(configFile));
            String newLine = System.getProperty("line.separator");
            configWriter.write("//" + modId + " configuration file, mobs use default values until you change them" + newLine +
                    "// The valid format for modifiers is 'modifierType;value' " + newLine +
                    "// The modifier types available are : " + newLine +
                            "//  -set" + newLine +
                            "//  -add" + newLine +
                            "//  -multiply" + newLine +
                            "//  -divide" + newLine +
                            "//  -default" + newLine +
                    "// The value must be a float" + newLine +
                    "// No need to add a value when you use the 'default' modifier, the default value for that mob is used instead" + newLine +
                    "// note that some values wont affect certain mobs due to them being coded differently ( IE:strength doesnt affect slimes)" + newLine
                    );
            for(String mob : EntityDispatcher.classToStringMapping.values()) {
                configWriter.write(
                        "E:" + mob + newLine +
                                    "M:health=default" + newLine +
                                    "M:strength=default" + newLine +
                                    "M:speed=default" + newLine
                );
            }

            configWriter.close();
            VariedMonsters.LOGGER.info("Config file was missing, creating new one");
        } catch(Exception e) {
            VariedMonsters.LOGGER.error("Failed to create config for "+modId+"!");
            e.printStackTrace();
        }
    }


    public EntityStats readFromConfig(String key, EntityStats defaultStats) {
        if(!configFile.exists()) {
            writeConfig();
        }
        try {
            EntityStats entityStats;
            Modifiers healthModifier = new Modifiers();
            Modifiers strengthModifier = new Modifiers();
            Modifiers speedModifier = new Modifiers();
            boolean gatheringModifiers = false;
            for(String s : Files.readAllLines(configFile.toPath())) {

                if(s.startsWith("E")) {
                    if (gatheringModifiers) {
                        gatheringModifiers = false;
                    }
                    String[] splitLine = s.split(":");
                    String[] splitIdStat = splitLine[1].split("=");
                    if (splitIdStat[0].equalsIgnoreCase(key)) {
                        gatheringModifiers = true;
                    }
                }
                if(gatheringModifiers && s.startsWith("M")) {
                    String[] splitIdStat = s.split(":");
                    String[] splitStatModif = splitIdStat[1].split("=");
                    String[] splitModif = splitStatModif[1].split(";");
                    if(!splitModif[0].equalsIgnoreCase("default")) {
                        switch (splitStatModif[0]) {
                            case "health":
                                healthModifier = new Modifiers(splitModif[0], Float.parseFloat(splitModif[1]));
                            case "strength":
                                strengthModifier = new Modifiers(splitModif[0], Float.parseFloat(splitModif[1]));
                            case "speed":
                                speedModifier = new Modifiers(splitModif[0], Float.parseFloat(splitModif[1]));
                        }
                    }
                }

            }
            entityStats = new EntityStats(healthModifier,strengthModifier,speedModifier);
            return entityStats;
        } catch(Exception e) {
            VariedMonsters.LOGGER.error("Failed to read config for "+modId+"!");
            e.printStackTrace();
        }
        VariedMonsters.LOGGER.info("("+modId+") "+"No value defined, returning default: "+ defaultStats);
        return defaultStats;
    }

}
