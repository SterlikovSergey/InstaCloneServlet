package by.tms.instaclone66.servlet;

import by.tms.instaclone66.entity.User;
import by.tms.instaclone66.service.CommentService;
import by.tms.instaclone66.service.PublicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(value = "/add-comment")
public class CommentServlet extends HttpServlet {
    private final CommentService commentService = CommentService.getInstance();
    private final PublicationService publicationService = PublicationService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User authorDto = (User) req.getSession().getAttribute("author");
        req.setAttribute("posts", publicationService.getPostsByAuthorId(authorDto));

        req.getServletContext().getRequestDispatcher("/pages/publication/add_publication.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User authorDto = (User) req.getSession().getAttribute("author");
        int postId = Integer.parseInt(req.getParameter("postId"));
        String textComment = req.getParameter("comment");
        LocalDate commentData = LocalDate.now();
        commentService.create(authorDto.getId(), postId, textComment, commentData);
        req.setAttribute("comments", commentService.getAllCommentsByPostId(postId));
    }
}
