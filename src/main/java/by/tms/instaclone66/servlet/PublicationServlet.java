package by.tms.instaclone66.servlet;

import by.tms.instaclone66.entity.Publication;
import by.tms.instaclone66.entity.User;
import by.tms.instaclone66.service.FhotoService;
import by.tms.instaclone66.service.PublicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(value = "/add_publication")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class PublicationServlet extends HttpServlet {
    private final PublicationService publicationService = PublicationService.getInstance();
    private final FhotoService fhotoService = FhotoService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("author");
        req.setAttribute("posts", publicationService.getPostsByAuthorId(user));
        req.getServletContext().getRequestDispatcher("/pages/publication/add_publication.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part contentPart = req.getPart("content");
        byte[] content = fhotoService.convertAvatar(contentPart); // Изменено на byte[]
        String description = req.getParameter("description");
        LocalDate postOfDate = LocalDate.now();
        User user = (User) req.getSession().getAttribute("author");
        Publication publication = new Publication(user, content, description, postOfDate); // Изменено на byte[]
        publicationService.create(publication);
        doGet(req, resp);
    }
}
