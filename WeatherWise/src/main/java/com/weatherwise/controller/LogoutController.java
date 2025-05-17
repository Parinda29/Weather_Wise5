package com.weatherwise.controller;

import java.io.IOException;
import com.weatherwise.util.CookiesUtil;
import com.weatherwise.util.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Parinda Rai
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CookiesUtil.deleteCookie(resp, "role");
		SessionUtil.invalidateSession(req);
		resp.sendRedirect(req.getContextPath() + "/login");
	}
}