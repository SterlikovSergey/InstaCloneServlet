package by.tms.instaclone66.servlet;

import by.tms.instaclone66.entity.User;
import by.tms.instaclone66.service.AuthorService;
import by.tms.instaclone66.service.FhotoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Optional;

@WebServlet(value = "/register")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class RegistrationServlet extends HttpServlet {

    private final AuthorService authorService = AuthorService.getInstance();
    private final FhotoService fhotoService = FhotoService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("author") != null) {
            User author = (User) req.getSession().getAttribute("author");
            Optional<User> findAuthorByEmail = authorService.getAuthorByEmail(author.getEmail());
            if (findAuthorByEmail.isPresent()) {
                User currentUser = findAuthorByEmail.get();
                // Преобразование avatar из byte[] в Base64 строку
                String avatarBase64 = Base64.getEncoder().encodeToString(currentUser.getAvatar());
                req.setAttribute("dataAvatar", avatarBase64);
                req.getServletContext().getRequestDispatcher("/pages/register/register.jsp").forward(req, resp);
                return;
            }
        }
        req.getServletContext().getRequestDispatcher("/pages/register/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String email = req.getParameter("email").toLowerCase();
        String password = req.getParameter("password");
        Part avatarPart = req.getPart("avatar");
        byte[] avatar = fhotoService.convertAvatar(avatarPart); // Изменено на byte[]
        String bio = req.getParameter("bio");
        LocalDate registrationOfDate = LocalDate.now();

        User user = new User(userName, email, password, avatar, bio, registrationOfDate); // Изменено на byte[]

        Optional<User> findAuthorByEmail = authorService.getAuthorByEmail(email);
        if (findAuthorByEmail.isPresent()) {
            req.setAttribute("NOTIFICATION", "choose a different email address".toUpperCase());
            req.getServletContext().getRequestDispatcher("/pages/register/register.jsp").forward(req, resp);
        } else {
            authorService.create(user);
            Optional<User> registeredUser = authorService.getAuthorByEmail(email);
            if (registeredUser.isPresent()) {
                User currentAuthor = registeredUser.get();
                req.getSession().setAttribute("author", currentAuthor);
                req.setAttribute("NOTIFICATION", "registration was successful".toUpperCase());
                req.getServletContext().getRequestDispatcher("/pages/register/register.jsp").forward(req, resp);
            }
        }
    }
}


