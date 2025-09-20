package iuh.fit.se.controllers;

import iuh.fit.se.daos.UserDAO;
import iuh.fit.se.daos.impl.UserDAOImpl;
import iuh.fit.se.entities.User;
import iuh.fit.se.utils.EntityManagerFactoryUtil;
import jakarta.persistence.EntityManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet(name = "userController", urlPatterns = {"/users", "/users*"})
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "new":
                handleShowRegisterForm(req, resp);
                break;
            case "delete":
                handleDeleteUser(req, resp);
                break;
            case "edit":
                handleShowEditForm(req, resp);
                break;
            default:
                handleShowUser(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "insert":
                handleAddUser(req, resp);
                break;
            case "update":
                handleEditUser(req, resp);
                break;
            default:
                handleShowUser(req, resp);
                break;
        }
    }

    // Get method
    private void handleShowUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager()) {
            UserDAO userDao = new UserDAOImpl(entityManager);
            List<User> listUser = userDao.findAll();

            req.setAttribute("listUser", listUser);
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/user/list.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get method
    private void handleShowRegisterForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/user/add.jsp");
        dispatcher.forward(req, resp);
    }

    // Post method
    private void handleAddUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager()) {
            UserDAO userDao = new UserDAOImpl(entityManager);
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String country = req.getParameter("country");
            User newUser = new User(name, email, country);

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<User>> violations = validator.validate(newUser);

            if (violations.isEmpty()) {
                userDao.save(newUser);
                resp.sendRedirect("users");
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("views/user/add.jsp");

                StringBuilder stringBuilder = new StringBuilder();
                violations.forEach(violation -> {
                    stringBuilder.append(violation.getPropertyPath() + ": " + violation.getMessage());
                    stringBuilder.append("<br />");
                });

                req.setAttribute("user", newUser);
                req.setAttribute("errors", stringBuilder);
                dispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get method
    private void handleShowEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager()) {
            UserDAO userDao = new UserDAOImpl(entityManager);
            int id = Integer.parseInt(req.getParameter("id"));
            User existingUser = userDao.findById(id);
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/user/edit.jsp");
            req.setAttribute("user", existingUser);
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Post method
    private void handleEditUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager()) {
            UserDAO userDao = new UserDAOImpl(entityManager);
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String country = req.getParameter("country");

            User user = new User(id, name, email, country);
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<User>> violations = validator.validate(user);

            if (violations.isEmpty()) {
                userDao.update(user);
                resp.sendRedirect("users");
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("views/user/edit.jsp");

                StringBuilder stringBuilder = new StringBuilder();
                violations.forEach(violation -> {
                    stringBuilder.append(violation.getPropertyPath() + ": " + violation.getMessage());
                    stringBuilder.append("<br />");
                });

                req.setAttribute("user", user);
                req.setAttribute("errors", stringBuilder);
                dispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleDeleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager()) {
            UserDAO userDao = new UserDAOImpl(entityManager);
            int id = Integer.parseInt(req.getParameter("id"));
            userDao.delete(id);
            resp.sendRedirect("users");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        EntityManagerFactoryUtil.close();
        super.destroy();
    }
}
