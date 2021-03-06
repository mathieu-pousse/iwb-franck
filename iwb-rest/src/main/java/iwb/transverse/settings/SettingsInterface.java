package iwb.transverse.settings;

import restx.config.Settings;
import restx.config.SettingsKey;

@Settings
public interface SettingsInterface {
	@SettingsKey(key = "img.location", defaultValue="https://drive.google.com/uc?export=download&id=", doc= "Url for web client to load images")
    String getImageLocation();
	@SettingsKey(key = "img.store.location", doc= "Location where images are stored")
    String getImageStoreLocation();
	@SettingsKey(key = "drive.google.folderID", defaultValue="0Bw93kkv3dJx1NkVXVTBYdC0zREk", doc= "Parent folder on google drive")
    String getGoogleFolderID();
	@SettingsKey(key = "drive.google.serviceAccountID", defaultValue="402934876321-ajodcjecivi3j96cf149e60n6bnrifdc@developer.gserviceaccount.com",doc= "Google Drive service account ID")
    String getGoogleServiceAccountID();
	@SettingsKey(key = "drive.google.keyFile.location", defaultValue="90681d5c7c7c7a452455f7e23adddbd7251092a8-privatekey.p12", doc= "Key File location generated by Google web console")
    String getGoogleKeyFileLocation();
}
