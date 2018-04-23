/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.dao;

import br.ufscar.dc.sistemareserva.beans.Admin;
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
public class AdminDAO {

    private final static String BUSCAR_ADMIN_SQL = "select "
            + "nome, email, senha "
            + "from admin "
            + "where nome=?";

    DataSource datasource;

    public AdminDAO(DataSource datasource) {
        this.datasource = datasource;
    }

    public Admin buscaAdmin(String username) throws SQLException {
        Admin admin = new Admin();
        try (Connection con = datasource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_ADMIN_SQL);) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    admin.setEmail(rs.getString("email"));
                    admin.setSenha(rs.getString("senha"));
                    admin.setNome(rs.getString("nome"));
                } else {
                    return null;
                }
            }
        }
        return admin;
    }
}
