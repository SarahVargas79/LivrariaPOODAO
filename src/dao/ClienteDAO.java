/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Cliente;

/**
 *
 * @author 182010049
 */
public class ClienteDAO {

    public void cadastrarClienteDAO(Cliente cVO) {
        //busca conexão com o BD
        Connection con = Conexao.getConexao();//abrindo conexão
        try {
            //cria espaço de trabalho SQL, é a área no Java onde vamo executar os sripts SQL.
            String sql;
            sql = "insert into clientes values (null, ?, ?, null, ?, ?)";//? paramêtros que está esperando para receber, null é o id.
            PreparedStatement pst = con.prepareStatement(sql);
            //Estes são os paramêtros
            pst.setString(1, cVO.getNomeCliente());
            pst.setString(2, cVO.getCpf());
            pst.setString(3, cVO.getEndereco());
            pst.setString(4, cVO.getTelefone());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("\nErro ao Cadastrar!\n" + ex.getMessage());        
        }
    }//fim cadastrar
    
    public ArrayList<Cliente> getClientesDAO() {
        Connection con = Conexao.getConexao();
        try {//Para tratar erros
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
                c.setEndereco(rs.getString("endereco"));
                c.setTelefone(rs.getString("telefone"));
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
            String sql = "select * from clientes where cpf = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cpf);
            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {              
                //lado do java |x| (lado do banco)
                c.setIdCliente(rs.getByte("idcliente"));
                c.setNomeCliente(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setEndereco(rs.getString("endereco"));
                c.setTelefone(rs.getString("telefone"));
            }
        } catch (SQLException ex) {
            System.out.println("\nErro ao consultar CPF!\n" + ex.getMessage());
        }
       return c;
    }
    
    public void deletarClienteDAO(String cpf) {
        Connection con = Conexao.getConexao();
        try {
            String sql = "delete from clientes where cpf = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cpf);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("\nErro ao deletar CPF!\n" + ex.getMessage());
        }
    }//fim deletarCliente
    
    public void atualizaClienteByDoc(Cliente cVO) {
        Connection con = Conexao.getConexao();
        try {
            String sql = "update clientes set nome = ?, endereco = ?, telefone = ? where cpf = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cVO.getNomeCliente());
            pst.setString(2, cVO.getEndereco());
            pst.setString(3, cVO.getTelefone());
            pst.setString(4, cVO.getCpf());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("\nErro ao atualizar CPF!\n" + ex.getMessage());
        }
    }
}
