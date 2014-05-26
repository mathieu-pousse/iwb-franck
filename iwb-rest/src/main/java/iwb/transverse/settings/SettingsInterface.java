package iwb.transverse.settings;

import restx.config.Settings;
import restx.config.SettingsKey;

@Settings
public interface SettingsInterface {
	@SettingsKey(key = "img.location", doc= "Url for web client to load images")
    String getImageLocation();
	@SettingsKey(key = "img.store.location", doc= "Location where images are stored")
    String getImageStoreLocation();
}
