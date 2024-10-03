package by.tms.instaclone66.servlet;

import by.tms.instaclone66.entity.User;
import by.tms.instaclone66.service.SubscriptionsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/unsubscribe")
public class UnsubscribeServlet extends HttpServlet {
    SubscriptionsService subscriptionsService = SubscriptionsService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User authorDto = (User) req.getSession().getAttribute("author");
        req.setAttribute("peoples", subscriptionsService.showAllSubscriptions(authorDto.getId()));
        req.getServletContext().getRequestDispatcher("/pages/subscription/subscribe.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User author = (User) req.getSession().getAttribute("author");
        int idUnfollowing = Integer.parseInt(req.getParameter("id"));
        subscriptionsService.unsubscribe(idUnfollowing, author.getId());
        req.setAttribute("peoples", subscriptionsService.showAllSubscriptions(author.getId()));
        req.getServletContext().getRequestDispatcher("/pages/subscription/unsubscribe.jsp").forward(req, resp);
    }
}
