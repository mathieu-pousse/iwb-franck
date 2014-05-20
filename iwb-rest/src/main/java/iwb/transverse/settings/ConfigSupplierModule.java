package iwb.transverse.settings;


import restx.config.ConfigLoader;
import restx.config.ConfigSupplier;
import restx.factory.Module;
import restx.factory.Provides;

@Module
public class ConfigSupplierModule {
	@Provides
    public ConfigSupplier myConfigSupplier(ConfigLoader configLoader) {
        // Load settings.properties in samplest.settings package as a set of config entries
		{  
			return configLoader.fromResource("settings/iwb-settings_dev");
		}
	}
}