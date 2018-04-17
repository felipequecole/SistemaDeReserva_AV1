/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.dao;

import br.ufscar.dc.sistemareserva.beans.Promocao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author felipequecole
 */
public class PromocaoDAO {
    private final static String CRIAR_PROMOCAO_SQL = "insert into promocao " +
            "(url, cnpj, data_inicio, data_fim, preco) values " +
            "(?,?,?,?,?)";
    
    DataSource datasource;
    
    public PromocaoDAO(DataSource datasource){
        this.datasource = datasource;
    }
    
    public Promocao gravaPromocao(Promocao promocao){
         try (Connection con = datasource.getConnection();
            PreparedStatement ps = con.prepareStatement(CRIAR_PROMOCAO_SQL, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, promocao.getUrl());
            ps.setString(2, promocao.getCnpj());
            ps.setDate(3,  new java.sql.Date(promocao.getData_inicio().getTime()));
            ps.setDate(4,  new java.sql.Date(promocao.getData_fim().getTime()));
            ps.setFloat(5, promocao.getPreco());
            ps.execute();
            try (ResultSet rs = ps.getGeneratedKeys()){
                rs.next();
                promocao.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromocaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null; 
        }
        return promocao;
    }
}
