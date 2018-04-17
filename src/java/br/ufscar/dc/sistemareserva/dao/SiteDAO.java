/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.dao;

import br.ufscar.dc.sistemareserva.beans.Site;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author felipequecole
 */
public class SiteDAO {

    // fazer strings sql
    
    private final static String CRIAR_SITE_SQL = "insert into Site" 
            + "(url,nome,telefone,senha)"
            + "values (?,?,?,?)";
    
    private final static String BUSCAR_SITE_SQL = "select "
            + "senha, telefone, nome, url "
            + "from site "
            + "where url=?";

    DataSource datasource;

    public SiteDAO(DataSource datasource) {
        this.datasource = datasource;
    }
    
    public Site gravarSite (Site s) throws SQLException, NamingException {
        try (Connection con = datasource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_SITE_SQL);){
        ps.setString(1, s.getUrl());
        ps.setString(2, s.getNome());
        ps.setString(3, s.getTelefone());
        ps.setString(4, s.getSenha());
        ps.execute();
        }
     return s;
     }
    
    
    

    public Site buscarSite(String url) {
        Site site = new Site();
        try {
            Connection con = datasource.getConnection();
            PreparedStatement ps = con.prepareStatement(BUSCAR_SITE_SQL);
            ps.setString(1, url);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                site.setNome(rs.getString("nome"));
                site.setSenha(rs.getString("senha"));
                site.setTelefone(rs.getString("telefone"));
                site.setUrl(rs.getString("url"));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SiteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null; 
        }
        return site;
    }

}
