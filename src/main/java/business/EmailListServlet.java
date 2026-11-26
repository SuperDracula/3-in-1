package business;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;

import model.User;
import model.Survey;

@WebServlet("/emailList")
public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String url = "/survey/survey_form.jsp";

        String action = request.getParameter("action");

        System.out.println("Action: " + action);

        log("EmailListServlet - action: " + action);

        if (action == null) {
            action = "join";
        }

        if (action.equals("join")) {
            url = "/survey/survey_form.jsp";
        } else if (action.equals("add")) {
            Date currentDate = new Date();

            GregorianCalendar calendar = new GregorianCalendar();
            int currentYear = calendar.get(Calendar.YEAR);

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String dob = request.getParameter("dob");
            String hear = request.getParameter("hear");
            String yes1 = request.getParameter("yes1");
            String yes2 = request.getParameter("yes2");
            String[] contactRequested = request.getParameterValues("contactMethod");

            String contactMethod = "";
            if (contactRequested != null && contactRequested.length > 0) {
                contactMethod = String.join(", ", contactRequested);
            } else {
                contactMethod = "None";
            }

            if (yes1 != null) {
                yes1 = "yes, I'd like that";
            }

            if (yes2 != null) {
                yes2 = "yes, please send me the email announcements";
            }

            HttpSession session = request.getSession();

            User user = new User(firstName, lastName, email, dob);
            Survey survey = new Survey(hear, yes1, yes2, contactMethod);

            ArrayList<User> users = (ArrayList<User>) session.getAttribute("users");
            if (users == null) {
                users = new ArrayList<>();
            }
            users.add(user);

            session.setAttribute("users", users);
            session.setAttribute("user", user);

            request.setAttribute("user", user);
            request.setAttribute("survey", survey);
            request.setAttribute("currentYear", currentYear);
            url = "/survey/thanks.jsp";
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}