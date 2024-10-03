package by.tms.instaclone66.service;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

public class FhotoService {
    private static FhotoService instance;

    private FhotoService() {

    }

    public static FhotoService getInstance() {
        if (instance == null) {
            instance = new FhotoService();
        }
        return instance;
    }

    public byte[] convertAvatar(Part avatar) throws IOException {
        InputStream fileContent = avatar.getInputStream();
        return fileContent.readAllBytes();
    }
}
