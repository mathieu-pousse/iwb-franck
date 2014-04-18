package iwb.transverse.settings;

import restx.config.Settings;
import restx.config.SettingsKey;

@Settings
public interface SettingsInterface {
	@SettingsKey(key = "example.key1", defaultValue = "MyValue1",
            doc = "This is an example key 1")
    String key1();
	@SettingsKey(key = "restx.admin.password")
    String adminPassword();
}
