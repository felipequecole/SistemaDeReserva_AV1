/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.forms;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author frankson
 */
public class CadastraHotelFormBean {
  String cnpj, nome, senha, cidade;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    public List<String> validar(){
        
        ArrayList<String> mensagens = new ArrayList<String>();
        
        if(nome.trim().length() == 0){
            mensagens.add("Nome não pode ser vazio!");
        }
        
        if(senha.trim().length() < 6){
            mensagens.add("Senha deve ter no minimo 6 digitos!");
        }
        
        if(cidade.trim().length() == 0){
            mensagens.add("Cidade não pode ser vazio!");
        }
        
        if(cnpj.trim().length() == 0){
            mensagens.add("CNPJ não pode ser vazio!");
        }
       
        if(cnpj.matches("[a-zA-Z]")){
            mensagens.add("CNPJ não pode conter letras!");
        }
        return (mensagens.isEmpty() ? null: mensagens);
    }
    
}
