package iwb.transport;

import iwb.service.UploadService;
import iwb.service.helpers.PartsReader;
import iwb.service.helpers.PartsReader.FilePart;
import iwb.service.helpers.PartsReader.Part;
import iwb.service.helpers.PartsReader.PartListener;
import iwb.transverse.settings.SettingsInterface;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.inject.Named;

import restx.RestxContext;
import restx.RestxRequest;
import restx.RestxRequestMatch;
import restx.RestxResponse;
import restx.StdRestxRequestMatcher;
import restx.StdRoute;
import restx.factory.Component;
import restx.http.HttpStatus;

@Component
public class UploadResource extends StdRoute{
	private String fileNameCreated;
	private UploadService uploadService;
	
    public UploadResource(SettingsInterface settings, @Named("uploadServiceOnCloud") UploadService uploadService) {
        super("upload", new StdRestxRequestMatcher("POST", "/upload"));
        this.uploadService = uploadService;
    }
    

    @Override
    public void handle(RestxRequestMatch match, RestxRequest req, RestxResponse resp, RestxContext ctx) throws IOException {
        new PartsReader(req).readParts(new PartListener() {
            public void onPart(Part part) throws IOException {
                if (part instanceof FilePart) {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    FilePart filePart = (FilePart) part;
                    filePart.readStreamTo(outputStream);
                    
                    fileNameCreated = uploadService.saveFile(filePart, outputStream);
                }
            }
        }); 
        if(fileNameCreated == null || fileNameCreated.isEmpty()){
        	resp.setStatus(HttpStatus.BAD_REQUEST);
        }else{
        	resp.setStatus(HttpStatus.OK);
            resp.getWriter().write(fileNameCreated);
        }
    }

}
