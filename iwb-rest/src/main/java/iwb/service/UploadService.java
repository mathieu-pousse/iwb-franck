package iwb.service;

import iwb.service.helpers.PartsReader.FilePart;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface UploadService {
	public String saveFile(FilePart filePart, ByteArrayOutputStream outputStream) throws IOException;
}
