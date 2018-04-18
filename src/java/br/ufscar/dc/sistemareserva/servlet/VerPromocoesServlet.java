/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.servlet;

import br.ufscar.dc.sistemareserva.beans.Promocao;
import br.ufscar.dc.sistemareserva.dao.PromocaoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "VerPromocoesServlet", urlPatterns = {"/VerPromocoesServlet"})
public class VerPromocoesServlet extends HttpServlet {

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
        String role = (String) request.getSession().getAttribute("role");
        if (role == null) {
            response.sendRedirect("login.jsp");
        } else {
            PromocaoDAO pdao = new PromocaoDAO(datasource);
            List<Promocao> promocoes = null;
            if (role.equals("hotel")) {
                String cnpj = (String) request.getSession().getAttribute("cnpj");
                try {
                    promocoes = pdao.listaPromocaoHotel(cnpj);
                    request.setAttribute("promocoes", promocoes);
                    request.getRequestDispatcher("verPromocoes.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(VerPromocoesServlet.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("mensagem", "Erro ao acessar o banco");
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            } else if (role.equals("site")) {
                String url = (String) request.getSession().getAttribute("url");
                request.setAttribute("promocoes", promocoes);
                try {
                    promocoes = pdao.listaPromocaoSite(url);
                    
                    request.setAttribute("promocoes", promocoes);
                    request.getRequestDispatcher("verPromocoes.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(VerPromocoesServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } else {
                request.setAttribute("mensagem", "Acesso não permitido");
                request.getRequestDispatcher("erro.jsp").forward(request, response);
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
