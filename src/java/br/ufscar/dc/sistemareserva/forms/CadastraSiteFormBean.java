/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.forms;

import java.util.List;
import java.lang.String;
import java.util.ArrayList;

/**
 *
 * @author eduardo
 */
public class CadastraSiteFormBean {
    private String url, nome, senha, telefone;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public List<String> validar(){
    List<String> mensagens = new ArrayList<String>();
    
    if (nome.trim().length() == 0){
        mensagens.add("Nome não pode ser vazio!");
    }
    
    if (telefone.trim().length() == 0){
        mensagens.add("Telefone não pode ser vazio!");
    }
    
    if (url.trim().length() == 0){
        mensagens.add("Endereço não pode ser vazio!");
    }
    
    if (senha.trim().length() < 6){
        mensagens.add("Senha não pode ter menos de 6 caracteres!");
    }
    /*
    if (!url.matches("(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?\"")){
    //pode dar merda
        mensagens.add("Insira um endereço valido!");
    }
    */
    
    return (mensagens.isEmpty() ? null : mensagens);
    }
    
}


