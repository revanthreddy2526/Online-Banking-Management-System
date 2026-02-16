package com.bank;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class FundTransferServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int amount = Integer.parseInt(request.getParameter("amount"));
        int receiverId = Integer.parseInt(request.getParameter("receiverId"));

        try {
            Connection con = DBConnection.getConnection();
            con.setAutoCommit(false);

            PreparedStatement deduct = con.prepareStatement(
                "UPDATE users SET balance = balance - ? WHERE id=1"
            );
            deduct.setInt(1, amount);
            deduct.executeUpdate();

            PreparedStatement add = con.prepareStatement(
                "UPDATE users SET balance = balance + ? WHERE id=?"
            );
            add.setInt(1, amount);
            add.setInt(2, receiverId);
            add.executeUpdate();

            con.commit();
            response.getWriter().println("Fund Transfer Successful!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
