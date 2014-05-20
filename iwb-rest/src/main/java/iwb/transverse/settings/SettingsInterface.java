package iwb.transverse.settings;

import restx.config.Settings;
import restx.config.SettingsKey;

@Settings
public interface SettingsInterface {
	@SettingsKey(key = "img.location", doc= "Location where images are stored")
    String imgLocation();
}
