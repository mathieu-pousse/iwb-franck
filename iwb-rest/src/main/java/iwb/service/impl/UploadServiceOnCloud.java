package iwb.service.impl;

import iwb.service.UploadService;
import iwb.service.helpers.ImageUrlHelper;
import iwb.service.helpers.PartsReader.FilePart;
import iwb.transverse.settings.SettingsInterface;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.inject.Named;

import org.apache.commons.io.FilenameUtils;

import restx.factory.Component;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.ParentReference;

@Component  @Named("uploadServiceOnCloud")
public class UploadServiceOnCloud implements UploadService{
	
	/** Email of the Service Account */
	private final String SERVICE_ACCOUNT_ID;

	/** Path to the Service Account's Private Key file */
	private final String SERVICE_ACCOUNT_PKCS12_FILE_PATH;
	
	private final String FOLDER_ID;
	
	private ImageUrlHelper imageHelper;
	
	
	public UploadServiceOnCloud(SettingsInterface settings, @Named("imageHelper") ImageUrlHelper imgHelper){
		SERVICE_ACCOUNT_ID = settings.getGoogleServiceAccountID();
		SERVICE_ACCOUNT_PKCS12_FILE_PATH = settings.getGoogleKeyFileLocation();
		FOLDER_ID = settings.getGoogleFolderID();
		imageHelper = imgHelper;
	}
	
	@Override
	public String saveFile(FilePart filePart, ByteArrayOutputStream outputStream) throws IOException{
        
		String timestampName = new SimpleDateFormat("yyyyMMddhhmmssS").format(new Date());
        String googleFileID = "";
        
        Drive service;
        
		try {
			service = getDriveService();
			ParentReference newParent = new ParentReference();
	        newParent.setId(FOLDER_ID);
	        
		    File body = new File();
		    body.setTitle(timestampName);
		    body.setDescription("");
		    body.setMimeType("image/jpg");
		    body.setParents(Arrays.asList(newParent));
		    
		    AbstractInputStreamContent mediaContent = new ByteArrayContent("image/jpg", outputStream.toByteArray());
		    
		    File file = service.files().insert(body, mediaContent).execute();
		    googleFileID = file.getId();
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		return imageHelper.addBasePathUrl(googleFileID);
	}
	
	 public Drive getDriveService() throws GeneralSecurityException, IOException, URISyntaxException {
		 
		  HttpTransport httpTransport = new NetHttpTransport();
		  JacksonFactory jsonFactory = new JacksonFactory();
		  GoogleCredential credential = new GoogleCredential.Builder()
		      .setTransport(httpTransport)
		      .setJsonFactory(jsonFactory)
		      .setServiceAccountId(SERVICE_ACCOUNT_ID)
		      .setServiceAccountScopes(DriveScopes.all())
		      .setServiceAccountPrivateKeyFromP12File(
		          new java.io.File(Thread.currentThread().getContextClassLoader().getResource(SERVICE_ACCOUNT_PKCS12_FILE_PATH).toURI()))      
		      .build();
		  Drive service = new Drive.Builder(httpTransport, jsonFactory, null)
		      .setHttpRequestInitializer(credential).build();
		  return service;
	 }

}
