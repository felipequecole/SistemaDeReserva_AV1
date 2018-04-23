/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.servlet;

import br.ufscar.dc.sistemareserva.beans.Hotel;
import br.ufscar.dc.sistemareserva.dao.HotelDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author frankson
 */
@WebServlet(name = "ListaHotelServlet", urlPatterns = {"/ListaHotelServlet"})
public class ListaHotelServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        HotelDAO hotelDAO = new HotelDAO(datasource);
        String cidade = request.getParameter("cidade");
        Logger.getLogger(ListaHotelServlet.class.getName()).log(Level.SEVERE, null, cidade);
        List<Hotel> todosOsHoteis = null;
        
        if (cidade == null || cidade.equals("")) {
            
            try {
                todosOsHoteis = hotelDAO.listaTodosHoteis();
                request.setAttribute("query", "nenhuma cidade");
            } catch (SQLException ex) {
                Logger.getLogger(ListaHotelServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("mensagem", "Erro ao acessar o banco.");
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(ListaHotelServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            request.setAttribute("query", cidade);
            try {
                todosOsHoteis = hotelDAO.listaTodosHoteisCidade(cidade.toUpperCase());
            } catch (SQLException ex) {
                Logger.getLogger(ListaHotelServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("mensagem", "Erro ao acessar o banco.");
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            } catch (NamingException ex) {
                Logger.getLogger(ListaHotelServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        request.setAttribute("listaHoteis", todosOsHoteis);
        request.getRequestDispatcher("listaHoteis.jsp").forward(request, response);

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
