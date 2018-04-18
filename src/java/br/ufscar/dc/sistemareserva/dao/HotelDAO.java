/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.dao;

import br.ufscar.dc.sistemareserva.beans.Hotel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author felipequecole
 */
public class HotelDAO {
    // fazer strings sql

    private final static String CRIAR_HOTEL_SQL = "insert into hotel"
            + " (cnpj, nome, cidade, senha)"
            + " values (?,?,?,?)";

    private final static String BUSCAR_HOTEL_SQL = "select "
            + "cnpj, nome, cidade, senha "
            + "from hotel "
            + "where cnpj=?";

    private final static String LISTAR_HOTEIS_SQL = "select "
            + "cnpj, nome, cidade, senha "
            + "from hotel ";

    private final static String LISTAR_HOTEIS_CIDADE_SQL = "select "
            + "cnpj, nome, cidade, senha "
            + "from hotel "
            + "where cidade = ?";

    DataSource datasource;

    public HotelDAO(DataSource datasource) {
        this.datasource = datasource;
    }

    public Hotel gravaHotel(Hotel hotel) throws SQLException, NamingException {
        try (Connection con = datasource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_HOTEL_SQL);) {
            ps.setString(1, hotel.getCnpj());
            ps.setString(2, hotel.getNome());
            ps.setString(3, hotel.getCidade());
            ps.setString(4, hotel.getSenha());
            ps.execute();
        }
        return hotel;
    }

    public Hotel buscaHotel(String cnpj) throws SQLException {
        Hotel hotel = new Hotel();

        java.sql.Connection con = datasource.getConnection();
        PreparedStatement ps = con.prepareStatement(BUSCAR_HOTEL_SQL);
        ps.setString(1, cnpj);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            hotel.setCnpj(rs.getString("cnpj"));
            hotel.setNome(rs.getString("nome"));
            hotel.setSenha(rs.getString("senha"));
            hotel.setCidade(rs.getString("cidade"));
        } else {
            return null;
        }

        return hotel;
    }

    public List<Hotel> listaTodosHoteis() throws SQLException, NamingException {
        List<Hotel> lista = new ArrayList<>();

        Connection con = datasource.getConnection();
        PreparedStatement ps = con.prepareStatement(LISTAR_HOTEIS_SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            Hotel h = new Hotel();
            h.setCnpj(rs.getString("cnpj"));
            h.setNome(rs.getString("nome"));
            h.setCidade(rs.getString("cidade"));

            lista.add(h);
        }

        return (lista.isEmpty() ? null : lista);
    }

    public List<Hotel> listaTodosHoteisCidade(String cidade) throws SQLException, NamingException {
        List<Hotel> lista = new ArrayList<>();

        Connection con = datasource.getConnection();
        PreparedStatement ps = con.prepareStatement(LISTAR_HOTEIS_CIDADE_SQL);

        ps.setString(1, cidade);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Hotel h = new Hotel();
            h.setCnpj(rs.getString("cnpj"));
            h.setNome(rs.getString("nome"));
            h.setCidade(rs.getString("cidade"));

            lista.add(h);
        }

        return (lista.isEmpty() ? null : lista);
    }

}
