package com.travelsphere.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.travelsphere.model.Cart;
@WebServlet("/add-to-cart")

public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			ArrayList<Cart> cartList = new ArrayList<Cart>();
			int id = Integer.parseInt(request.getParameter("id"));
			Cart cm = new Cart();
			cm.setId(id);
			cm.setQuantity(1);
			HttpSession session = request.getSession();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

			if (cart_list == null) {
				cartList.add(cm);
				session.setAttribute("cart-list", cartList);
				
				out.println("session created and added the list");
				response.sendRedirect("cart.jsp");

			} else {
				cartList = cart_list;
				boolean exist = false;
				for (Cart c : cart_list) {
					if (c.getId() == id) {
						exist = true;
						out.println("product exist");
						response.sendRedirect("cart.jsp");

					}

				}
				if (!exist) {
					cartList.add(cm);
					out.println("product added");
					response.sendRedirect("cart.jsp");

				}
			}

		}
	}

}
