/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.forms;

import br.ufscar.dc.sistemareserva.dao.PromocaoDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author felipequecole
 */
public class CadastraPromocaoFormBean {

    private String url, data_inicio, data_fim, preco;
//    private float preco;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getData_fim() {
        return data_fim;
    }

    public void setData_fim(String data_fim) {
        this.data_fim = data_fim;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public List<String> validar(DataSource datasource, String cnpj) {
        List<String> mensagens = new ArrayList<String>();
        PromocaoDAO pDao = new PromocaoDAO(datasource);

        if (url == null) {
            mensagens.add("O campo URL não pode estar vazio");
        } else {
            if (url.trim().length() == 0) {
                mensagens.add("O endereço não pode ser vazio!");
            }
        }

        if (preco == null && !preco.equals("")) {
            mensagens.add("O campo preço não pode estar vazio");
        } else {
            try {
                if (Float.parseFloat(preco.replace(",", ".")) < 0) {
                    mensagens.add("O preço não pode ser menor que zero !");
                }
            } catch (NumberFormatException e) {
                mensagens.add("O campo preço não pode estar vazio");
            }
        }
        Date inicio = new Date();
        Date fim = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            inicio = sdf.parse(data_inicio);
        } catch (ParseException ex) {
            inicio = null;
            Logger.getLogger(CadastraPromocaoFormBean.class.getName()).log(Level.SEVERE, null, ex);
            mensagens.add("Data de inicio não é valida!");
        }
        try {
            fim = sdf.parse(data_fim);
        } catch (ParseException ex) {
            Logger.getLogger(CadastraPromocaoFormBean.class.getName()).log(Level.SEVERE, null, ex);
            mensagens.add("Data de fim não é valida!");
        }

        if (inicio != null && fim != null) {

            if (fim.getTime() - inicio.getTime() <= 0) {
                mensagens.add("Intervalo de datas não é válido.");
            }

            try {
                if (!pDao.validaPromocao(cnpj, url, inicio)) {
                    mensagens.add("Já existe uma promoção nesse hotel/site com a mesma data de inicio");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CadastraPromocaoFormBean.class.getName()).log(Level.SEVERE, null, ex);
                mensagens.add("Problemas técnicos ao acessar o banco");
            }

        }
        return (mensagens.isEmpty() ? null : mensagens);
    }

}
