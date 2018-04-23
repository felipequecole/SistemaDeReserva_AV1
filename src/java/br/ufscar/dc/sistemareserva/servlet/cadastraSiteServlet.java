/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.servlet;

import br.ufscar.dc.sistemareserva.beans.Site;
import br.ufscar.dc.sistemareserva.dao.SiteDAO;
import br.ufscar.dc.sistemareserva.forms.CadastraSiteFormBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
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
import org.apache.commons.beanutils.BeanUtils;
import java.util.List;

/**
 *
 * @author eduardo
 */
@WebServlet(name = "cadastraSiteServlet", urlPatterns = {"/cadastraSiteServlet"})
public class cadastraSiteServlet extends HttpServlet {
    @Resource(name = "jdbc/SistemaReservaDBLocal")
    DataSource datasource;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        CadastraSiteFormBean csfb = new CadastraSiteFormBean();
        SiteDAO sdao = new SiteDAO(datasource);
        try {
            BeanUtils.populate(csfb, request.getParameterMap());
            List<String> mensagens = csfb.validar();
            if (mensagens != null){
                request.setAttribute("mensagens", mensagens);
                request.getSession().setAttribute("form",csfb);
                request.getRequestDispatcher("cadastraSite.jsp").forward(request,response);
            } else {
            Site site = new Site();
            site.setNome(csfb.getNome());
            site.setUrl(csfb.getUrl());
            site.setTelefone(csfb.getTelefone());
            site.setSenha(csfb.getSenha());
            site = sdao.gravarSite(site);
            request.getSession().setAttribute("mensagem","Site cadastrado.");
            response.sendRedirect("index.jsp");
            }
        } catch (IllegalAccessException ex) {
            Logger.getLogger(cadastraSiteServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("mensagem", "Erro ao processar requisição.");
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(cadastraSiteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
          catch (SQLException ex){
            Logger.getLogger(cadastraSiteServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("mensagem", "Erro ao acessar o banco.");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(cadastraSiteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(cadastraSiteServlet.class.getName()).log(Level.SEVERE, null, ex);
            // tratar erro
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(cadastraSiteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(cadastraSiteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
