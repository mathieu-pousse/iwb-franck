package iwb.transverse;

import iwb.service.helpers.ImageUrlHelper;
import iwb.transverse.PartsReader.FilePart;
import iwb.transverse.PartsReader.Part;
import iwb.transverse.PartsReader.PartListener;
import iwb.transverse.settings.SettingsInterface;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Named;

import org.apache.commons.io.FilenameUtils;

import restx.RestxContext;
import restx.RestxRequest;
import restx.RestxRequestMatch;
import restx.RestxResponse;
import restx.StdRestxRequestMatcher;
import restx.StdRoute;
import restx.factory.Component;
import restx.http.HttpStatus;


@Component
public class FileUploadRoute extends StdRoute {
	
	
	private String fileNameCreated;
	private String location;
	private ImageUrlHelper imageHelper;
	
    public FileUploadRoute(SettingsInterface settings, @Named("imageHelper") ImageUrlHelper imageHelper) {
        super("upload", new StdRestxRequestMatcher("POST", "/upload"));
        this.location = settings.getImageStoreLocation();
        this.imageHelper = imageHelper;
    }
    

    @Override
    public void handle(RestxRequestMatch match, RestxRequest req, RestxResponse resp, RestxContext ctx) throws IOException {
        new PartsReader(req).readParts(new PartListener() {
            public void onPart(Part part) throws IOException {
                if (part instanceof FilePart) {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    FilePart filePart = (FilePart) part;
                    filePart.readStreamTo(outputStream);
                    String filename = filePart.getFilename();
                    
                    String ext = FilenameUtils.getExtension(filename);
                    String timestampName = new SimpleDateFormat("yyyyMMddhhmmssS'."+ext+"'").format(new Date());
                    fileNameCreated =  timestampName;
                    String absolutePath = this.getClass().getResource("").getPath()+"../../ui/"+location;
                    FileOutputStream fos = new FileOutputStream (new File(absolutePath+timestampName)); 
                    
                    try {
                         outputStream.writeTo(fos);
                    }catch ( IOException e ) {
                    	e.printStackTrace();
                    }finally{
                    	fos.close();
                    }
                }
            }
        }); 
        resp.setStatus(HttpStatus.OK);
        resp.getWriter().write(imageHelper.addBasePathUrl(fileNameCreated));
    }

	

}
