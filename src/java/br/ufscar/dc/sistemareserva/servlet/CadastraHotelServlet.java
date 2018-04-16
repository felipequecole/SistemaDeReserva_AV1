/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.servlet;

import br.ufscar.dc.sistemareserva.beans.Hotel;
import br.ufscar.dc.sistemareserva.dao.HotelDAO;
import br.ufscar.dc.sistemareserva.forms.CadastraHotelFormBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author frankson
 */
@WebServlet(name = "CadastraHotelServlet", urlPatterns = {"/CadastraHotelServlet"})
public class CadastraHotelServlet extends HttpServlet {

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
        
        CadastraHotelFormBean chfb = new CadastraHotelFormBean();
    
        HotelDAO hoteldao = new HotelDAO(datasource);
        
        try {
            
            BeanUtils.populate(chfb, request.getParameterMap());
            List<String> mensagens = chfb.validar();
            
            if(mensagens != null){
             request.getRequestDispatcher("hotelForm.jsp").forward(request, response);
            }else{
                Hotel hotel = new Hotel();

                hotel.setNome(chfb.getNome());
                hotel.setCnpj(chfb.getCnpj());
                hotel.setSenha(chfb.getSenha());
                hotel.setCidade(chfb.getCidade());

                hotel = hoteldao.gravaHotel(hotel);

                request.setAttribute("mensagem", "Hotel cadastrado com sucesso!");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getLocalizedMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
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
