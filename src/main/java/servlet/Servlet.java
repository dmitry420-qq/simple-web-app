package servlet;

import com.google.gson.Gson;
import data.Item;
import data.ItemsDAO;
import data.storage.DAOFactory;
import data.storage.SqlPerRequestDAOFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;

public class Servlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        try {
            DAOFactory daoFactory = SqlPerRequestDAOFactory.getDAOFactory();
            ItemsDAO itemsDAO = daoFactory.getItemsDAO();
            Stream<Item> items = itemsDAO.getAll().stream();
            if (req.getParameter("id") != null)
                items = items.filter(item -> item.getId() == Integer.parseInt(req.getParameter("id")));
            if (req.getParameter("name") != null)
                items = items.filter(item -> item.getName().equals(req.getParameter("name")));
            if (req.getParameter("description") != null)
                items = items.filter(item -> item.getDescription().equals(req.getParameter("description")));
            if (req.getParameter("cost") != null)
                items = items.filter(item -> item.getCost() == Double.parseDouble(req.getParameter("cost")));

            out.print(gson.toJson(items.toArray()));
        } catch (Exception e) {
            out.print("err");
            System.err.println("err");
        }
        out.flush();
    }
}