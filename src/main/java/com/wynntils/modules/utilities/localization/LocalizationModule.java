package com.wynntils.modules.utilities.localization;

import com.wynntils.modules.utilities.enums.LanguageType;
import org.apache.logging.log4j.LogManager;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class LocalizationModule {

    private static HashMap<LanguageType, HashMap<String, String>> langConfig = new HashMap<>();

    public static void loadConfig() {
        for(LanguageType type : LanguageType.values()) {
            HashMap<String, String> hash = new HashMap<>();
            InputStream is = null;
            try {
                is = LocalizationModule.class.getResourceAsStream("/assets/wynntils/lang/" + type.getFilename());
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8), 4096);
                String str;

                while((str = reader.readLine()) != null) {
                    if(str.startsWith("config")) {
                        String[] split = str.split("=");
                        hash.put(split[0], split[1]);
                    }
                }

                langConfig.put(type, hash);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getConfig(LanguageType lang, String key) {
        String var = langConfig.get(lang).get(key.toLowerCase());
        if(lang != LanguageType.English && var == null) {
            var = langConfig.get(LanguageType.English).get(key.toLowerCase());
            LogManager.getFormatterLogger("wynntils_test").info(key + " > " + var);
        }
        return var;
    }
}
