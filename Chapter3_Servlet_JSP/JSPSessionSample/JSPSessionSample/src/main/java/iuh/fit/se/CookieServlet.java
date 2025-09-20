package iuh.fit.se;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "cookieServlet", value = "/cookie")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // read form data
        String favLang = req.getParameter("favoriteLanguage");
        // create the cookie
        Cookie theCookie = new Cookie("favoriteLanguage", favLang);
        // set the lifespan of the cookie (1 year)
        theCookie.setMaxAge(60 * 60 * 24 * 365);
        // send cookie to browser
        resp.addCookie(theCookie);
        // forward
        req.getRequestDispatcher("/language-response.jsp").forward(req, resp);
    }
}

