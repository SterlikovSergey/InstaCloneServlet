package by.tms.instaclone66.servlet;

import by.tms.instaclone66.service.AccountService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/settings")
public class AccountServlet extends HttpServlet {
    private final AccountService accountService = AccountService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/pages/settings/settings.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deleteParam = req.getParameter("delete");
        accountService.deleteAccount(Integer.parseInt(deleteParam));
        req.getServletContext().getRequestDispatcher("pages/settings/settings.jsp").forward(req, resp);
    }
}
