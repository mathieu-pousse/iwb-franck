package iwb.transverse;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class FileUploadRoute extends StdRoute {
	
	
	private String fileNameCreated;
	
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
                    
                    String ext = FilenameUtils.getExtension(filename);
                    String timestampName = new SimpleDateFormat("yyyyMMddhhmmssS'."+ext+"'").format(new Date());
                    fileNameCreated =  timestampName;
                    FileOutputStream fos = new FileOutputStream (new File("/home/franck/Desktop/workspace/iwb/iwb-rest/src/main/resources/iwb/transport/img/iwb-imgs/"+timestampName)); 
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
        resp.getWriter().write(fileNameCreated);
    }

	

}
