/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.forms;

import br.ufscar.dc.sistemareserva.dao.PromocaoDAO;
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
    private String url, data_inicio, data_fim;
    private float preco;

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

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    
    public List<String> validar(DataSource datasource, String cnpj){
    List<String> mensagens = new ArrayList<String>();
    PromocaoDAO pDao = new PromocaoDAO(datasource);
    
    if(url.trim().length() == 0){
        mensagens.add("O endereço não pode ser vazio!");
    }
    
    if (data_inicio.trim().length() == 0){
        mensagens.add("A data de inicio não pode ser vazia!");    
    }
    
    if (data_fim.trim().length() == 0){
        mensagens.add("A data de fim não pode ser vazia!");    
    }
    
    if (preco < 0){
        mensagens.add("O preço não pode ser menor que zero !");       
    }
    if(pDao.validaPromocao(url, cnpj ,data_inicio)){
        mensagens.add("Já existe uma promoção nesse hotel/site com a mesma data de inicio");
    }
    
    Date inicio = new Date();
    Date fim =  new Date();
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
        try {
            inicio = sdf.parse(data_inicio);
        } catch (ParseException ex) {
            Logger.getLogger(CadastraPromocaoFormBean.class.getName()).log(Level.SEVERE, null, ex);
            mensagens.add("Data de inicio não é valida!");      
        }
        try {
            fim = sdf.parse(data_fim);
        } catch (ParseException ex) {
            Logger.getLogger(CadastraPromocaoFormBean.class.getName()).log(Level.SEVERE, null, ex);
            mensagens.add("Data de fim não é valida!");     
        }
    
        if (fim.getTime() - inicio.getTime() <= 0){
            mensagens.add("Intervalo de datas não é válido.");
        }
    return (mensagens.isEmpty() ? null : mensagens);
    }
    
    
}
