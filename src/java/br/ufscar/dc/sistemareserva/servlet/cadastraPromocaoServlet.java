/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.servlet;

import br.ufscar.dc.sistemareserva.beans.Promocao;
import br.ufscar.dc.sistemareserva.dao.PromocaoDAO;
import br.ufscar.dc.sistemareserva.forms.CadastraPromocaoFormBean;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author felipequecole
 */
@WebServlet(name = "cadastraPromocaoServlet", urlPatterns = {"/cadastraPromocaoServlet"})
public class cadastraPromocaoServlet extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        CadastraPromocaoFormBean cpfb = new CadastraPromocaoFormBean();
        List<String> mensagens = new ArrayList<String>();
        String cnpj = (String) request.getSession().getAttribute("cnpj"); 
        try {
            BeanUtils.populate(cpfb, request.getParameterMap());
            mensagens = cpfb.validar(datasource, cnpj);
            request.getSession().setAttribute("form", cpfb);
            Promocao promocao = new Promocao();
            promocao.setCnpj(cnpj);
            System.out.println(promocao.getCnpj());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                promocao.setData_inicio(sdf.parse(cpfb.getData_inicio()));
                promocao.setData_fim(sdf.parse(cpfb.getData_fim()));
                promocao.setPreco(cpfb.getPreco());
                promocao.setUrl(cpfb.getUrl());
                if (mensagens != null) {
                    request.setAttribute("mensagem", mensagens);
                    request.getSession().setAttribute("form", cpfb);
                    request.getRequestDispatcher("cadastraPromocao.jsp").forward(request, response);
                } else {
                    PromocaoDAO pdao = new PromocaoDAO(datasource);
                    Promocao promocao_ret = null;
                    try {
                        promocao_ret = pdao.gravaPromocao(promocao);
                        if (promocao_ret != null) {
                            request.setAttribute("mensagem", "Promoção cadastrada com sucesso!");
                            response.sendRedirect("index.jsp");
                        } else {
                            request.setAttribute("mensagem", "Erro ao salvar no banco");
                            request.getRequestDispatcher("erro.jsp").forward(request, response);
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(cadastraPromocaoServlet.class.getName()).log(Level.SEVERE, null, ex);
                        request.setAttribute("mensagem", "Erro ao acessar o banco.");
                        request.getRequestDispatcher("erro.jsp").forward(request, response);
                    }

                }
            } catch (ParseException ex) {
                Logger.getLogger(cadastraPromocaoServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("mensagem", mensagens);
                request.getRequestDispatcher("cadastraPromocao.jsp").forward(request, response);
            }

        } catch (IllegalAccessException ex) {
            Logger.getLogger(cadastraPromocaoServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.getSession().setAttribute("mensagem", ex.getLocalizedMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(cadastraPromocaoServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.getSession().setAttribute("mensagem", ex.getLocalizedMessage());
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
        } catch (ParseException ex) {
            Logger.getLogger(cadastraPromocaoServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.getSession().setAttribute("mensagem", "Eduardo é um pau no cu get");
            request.getRequestDispatcher("erro.jsp").forward(request, response);
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
        } catch (ParseException ex) {
            Logger.getLogger(cadastraPromocaoServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.getSession().setAttribute("mensagem", "Eduardo é um pau no cu post");
            request.getRequestDispatcher("erro.jsp").forward(request, response);
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
