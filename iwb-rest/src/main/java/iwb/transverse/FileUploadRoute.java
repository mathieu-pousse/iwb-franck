package iwb.transverse;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import iwb.transverse.PartsReader.FilePart;
import iwb.transverse.PartsReader.Part;
import iwb.transverse.PartsReader.PartListener;
import iwb.transverse.PartsReader.TextPart;
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
	
    public FileUploadRoute() {
        super("upload", new StdRestxRequestMatcher("POST", "/upload"));
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
                    
                    FileOutputStream fos = new FileOutputStream (new File("/home/franck/Desktop/"+filename)); 
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
    }

	

}
