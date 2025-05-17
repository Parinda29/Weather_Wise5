package com.weatherwise.util;


import java.io.File;
import java.io.IOException;

import jakarta.servlet.http.Part;

public class ImageUtil {

    // Extracts file name from the uploaded Part (form file input)
    public String getImageNameFromPart(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String content : contentDisp.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return "default.png"; // fallback
    }

    
    public boolean uploadImage(Part part, String rootPath, String folderName) {
        try {
            String fileName = getImageNameFromPart(part);

            String uploadPath = rootPath + File.separator + "image" + File.separator + folderName;

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); 
            }

            // Save the file
            part.write(uploadPath + File.separator + fileName);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}