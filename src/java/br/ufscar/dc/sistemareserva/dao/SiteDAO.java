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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author felipequecole
 */
public class SiteDAO {

    // fazer strings sql
    private final static String BUSCAR_SITE_SQL = "select "
            + "email, senha, telefone, nome, url "
            + "from site "
            + "where email=?";

    DataSource datasource;

    public SiteDAO(DataSource datasource) {
        this.datasource = datasource;
    }

    public Site buscarSite(String email) {
        Site site = new Site(); 
        try {
            Connection con = datasource.getConnection();
            PreparedStatement ps = con.prepareStatement(BUSCAR_SITE_SQL);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            rs.next();
            site.setEmail(rs.getString("email"));
            site.setNome(rs.getString("nome"));
            site.setSenha(rs.getString("senha"));
            site.setTelefone(rs.getString("telefone"));
            site.setUrl(rs.getString("url"));
        } catch (SQLException ex) {
            Logger.getLogger(SiteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return site; 
    }

}
