/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.servlet;

import br.ufscar.dc.sistemareserva.beans.Admin;
import br.ufscar.dc.sistemareserva.beans.Site;
import br.ufscar.dc.sistemareserva.dao.AdminDAO;
import br.ufscar.dc.sistemareserva.dao.SiteDAO;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author felipequecole
 */
@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Resource(name = "jdbc/SistemaReservaDBLocal")
    DataSource datasource;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String senha = request.getParameter("senha");
        String tipo = request.getParameter("tipo");
        if (tipo.equals("site")) {
            SiteDAO sdao = new SiteDAO(datasource);
            Site site = sdao.buscarSite(username);
//            System.out.println(site.getNome());
            if (site == null){
                request.getSession().setAttribute("login_mensagem", "Login Inválido!");
                response.sendRedirect("login.jsp");
            }
            else if (site.getSenha().equals(senha)) {
                request.getSession().setAttribute("user", site.getNome());
                request.getSession().setAttribute("role", "site");
                response.sendRedirect("index.jsp");
//                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("login_mensagem", "Login Inválido!");
                response.sendRedirect("login.jsp");
//                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else if (tipo.equals("admin")){
            AdminDAO adao = new AdminDAO(datasource);
            Admin admin = adao.buscaAdmin(username);
            if (admin == null) {
                request.getSession().setAttribute("login_mensagem", "Login Inválido!");
                response.sendRedirect("login.jsp");
            } else if (admin.getSenha().equals(senha)) {
                request.getSession().setAttribute("user", admin.getNome());
                request.getSession().setAttribute("role", "admin");
                response.sendRedirect("index.jsp");
            } else { 
                request.getSession().setAttribute("login_mensagem", "Login Inválido!");
                response.sendRedirect("login.jsp");
            }
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
