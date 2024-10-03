package by.tms.instaclone66.servlet;

import by.tms.instaclone66.entity.User;
import by.tms.instaclone66.service.SubscriptionsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/subscribers")
public class SubscribersServlet extends HttpServlet {
    SubscriptionsService subscriptionsService = SubscriptionsService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User author = (User) req.getSession().getAttribute("author");
        req.setAttribute("peoples", subscriptionsService.showAllSubscribers(author.getId()));
        req.getServletContext().getRequestDispatcher("/pages/subscription/subscribers.jsp").forward(req, resp);
    }
}
