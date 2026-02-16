package com.bank;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class WithdrawServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int amount = Integer.parseInt(request.getParameter("amount"));

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement check = con.prepareStatement(
                "SELECT balance FROM users WHERE id=1"
            );
            ResultSet rs = check.executeQuery();
            rs.next();

            int balance = rs.getInt("balance");

            if (balance >= amount) {
                PreparedStatement ps = con.prepareStatement(
                    "UPDATE users SET balance = balance - ? WHERE id=1"
                );
                ps.setInt(1, amount);
                ps.executeUpdate();
                response.getWriter().println("Withdrawal Successful!");
            } else {
                response.getWriter().println("Insufficient Balance!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
