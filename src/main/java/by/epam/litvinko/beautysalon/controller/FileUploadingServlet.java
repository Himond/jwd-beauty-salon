package by.epam.litvinko.beautysalon.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@WebServlet(urlPatterns = {"/upload"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 25)
public class FileUploadingServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Servlet upload");
        response.setContentType("application/x-www-form-urlencoded");
        String applicationDir = request.getServletContext().getRealPath("");
        System.out.println(applicationDir);
        String uploadFileDir = applicationDir + File.separator + UPLOAD_DIR + File.separator;
        System.out.println(uploadFileDir);
        File fileSaveDir = new File(uploadFileDir);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        System.out.println(request.getParts());
        Part part = request.getPart("image");
        System.out.println(part);
        try (InputStream inputStream = part.getInputStream()){
            String submittedFileName = part.getSubmittedFileName();

            Path imagePath = new File("c:\\tmp\\" + submittedFileName).toPath();
            System.out.println(imagePath);
            long bytes = Files.copy(
                    inputStream,
                    imagePath,
                    StandardCopyOption.REPLACE_EXISTING);
            request.setAttribute("upload_result", " upload successfully " + bytes);
        } catch (IOException e) {
            System.out.println("Error");
            request.setAttribute("upload_result", " upload failed ");
        }
        request.getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
    }

}
