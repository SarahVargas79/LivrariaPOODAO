/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;

/**
 *
 * @author 182010049
 */
public class ClienteDAO {

    public void cadastrarClienteDAO(Cliente cVO) {
        //busca conexão com o BD
        Connection con = Conexao.getConexao();
        try {
            //cria espaço de trabalho SQL, é a área no Java onde vamo executar os sripts SQL.
            Statement stat = con.createStatement();
            String sql;
            sql = "insert into clientes values" 
                                                        + "(null, " 
                                                        + "'" + cVO.getNomeCliente() + "'," 
                                                        + "'" + cVO.getCpf() + "');";
            stat.execute(sql);
        } catch (SQLException ex) {
            System.out.println("\nErro ao Cadastrar!\n" + ex.getMessage());        
        }
    }//fim cadastrar
    
    public ArrayList<Cliente> getClienteBD() {
        Connection con = Conexao.getConexao();
        try {//Para tratr erros
            Statement stat = con.createStatement();
            String sql = "select * from clientes";//' para executar para o banco(mysql)
            ResultSet rs = stat.executeQuery(sql);//ResultSet estrutura no java, é um meio de campo entre o banco de dados e o java(aplicação).
            ArrayList<Cliente> clientes = new ArrayList<>();
            while (rs.next()) {              
                Cliente c = new Cliente();//objeto cliente
                //lado do java |x| (lado do banco)
                c.setIdCliente(rs.getByte("idcliente"));
                c.setNomeCliente(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                clientes.add(c);
            }
            return clientes;
        } catch (SQLException ex) {
            System.out.println("\nErro ao Listar!\n" + ex.getMessage());
        }
        return null;
    }//fim do listar
    
    public Cliente getClienteByDoc(String cpf) {
        Connection con = Conexao.getConexao();//estabelecendo conexão
        Cliente c = null;
        try {
            Statement stat = con.createStatement();
            String sql = "select * from clientes where cpf = '" + cpf + "'";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {              
                //lado do java |x| (lado do banco)
                c.setIdCliente(rs.getByte("idcliente"));
                c.setNomeCliente(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
            }
        } catch (SQLException ex) {
            System.out.println("\nErro ao consultar CPF!\n" + ex.getMessage());
        }
       return c;
    }
    
}
