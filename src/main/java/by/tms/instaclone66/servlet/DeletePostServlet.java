package by.tms.instaclone66.servlet;

import by.tms.instaclone66.entity.User;
import by.tms.instaclone66.service.PostService;
import by.tms.instaclone66.service.PublicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/del-publication")
public class DeletePostServlet extends HttpServlet {
    private final PostService postService = PostService.getInstance();
    private final PublicationService publicationService = PublicationService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User authorDto = (User) req.getSession().getAttribute("author");
        req.setAttribute("posts", publicationService.getPostsByAuthorId(authorDto));
        req.getServletContext().getRequestDispatcher("/pages/publication/add_publication.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int postId = Integer.parseInt(req.getParameter("id"));
        postService.deletePost(postId);
        doGet(req, resp);
    }
}
