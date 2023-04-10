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
import model.Editora;

/**
 *
 * @author 182010049
 */
public class EditoraDAO {
    
    public void cadastrarEditoraDAO(Editora eVO) {
        
        try {
            //busca conexão com o BD
            Connection con = Conexao.getConexao();//abrindo conexão
            //cria espaço de trabalho SQL, é a área no Java onde vamo executar os sripts SQL.
            String sql;
            sql = "insert into editoras values (null, ?, ?, ?, ?, ?)";//? paramêtros que está esperando para receber, null é o id.
            PreparedStatement pst = con.prepareStatement(sql);
            //Estes são os paramêtros
            pst.setString(1, eVO.getNomeEditora());
            pst.setString(2, eVO.getCnpj());
            pst.setString(3, eVO.getEndereco());
            pst.setString(4, eVO.getTelefone());
            pst.setString(5, eVO.getGerente());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("\nErro ao Cadastrar!\n" + ex.getMessage());        
        }
    }//fim cadastrar
    
    public ArrayList<Editora> getEditorasDAO() {
        ArrayList<Editora> editoras = new ArrayList<>();
        try {//Para tratar erros
            Connection con = Conexao.getConexao();
            Statement stat = con.createStatement();
            String sql = "select * from editoras";//' para executar para o banco(mysql)
            ResultSet rs = stat.executeQuery(sql);//ResultSet estrutura no java, é um meio de campo entre o banco de dados e o java(aplicação).
            while (rs.next()) {              
                Editora e = new Editora();//objeto editora
                //lado do java |x| (lado do banco)
                e.setIdEditora(rs.getByte("ideditora"));
                e.setNomeEditora(rs.getString("nomeEditora"));
                e.setCnpj(rs.getString("cnpj"));
                e.setEndereco(rs.getString("endereco"));
                e.setTelefone(rs.getString("telefone"));
                e.setGerente(rs.getString("gerente"));
                editoras.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("\nErro ao Listar!\n" + ex.getMessage());
        }
        return editoras;
    }//fim do listar
    
    public Editora getEditoraByDoc(String cnpj) {
        
        Editora e = new Editora();
        try {
            Connection con = Conexao.getConexao();//estabelecendo conexão
            String sql = "select * from editoras where cnpj = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cnpj);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {              
                //lado do java |x| (lado do banco)
                e.setIdEditora(rs.getByte("ideditora"));
                e.setNomeEditora(rs.getString("nomeEditora"));
                e.setCnpj(rs.getString("cnpj"));
                e.setEndereco(rs.getString("endereco"));
                e.setTelefone(rs.getString("telefone"));
                e.setGerente(rs.getString("gerente"));
            }
        } catch (SQLException ex) {
            System.out.println("\nErro ao consultar CNPJ!\n" + ex.getMessage());
        }
       return e;
    }
    
    public void deletarEditoraDAO(String cnpj) {
        
        try {
            Connection con = Conexao.getConexao();
            String sql = "delete from editoras where cnpj = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cnpj);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("\nErro ao deletar CNPJ!\n" + ex.getMessage());
        }
    }//fim deletarCliente
    
    public void atualizaEditoraByDoc(Editora eVO) {
        
        try {
            Connection con = Conexao.getConexao();
            String sql = "update editoras set nomeEditora = ?, endereco = ?, telefone = ?, gerente = ? where cnpj = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, eVO.getNomeEditora());
            pst.setString(2, eVO.getEndereco());
            pst.setString(3, eVO.getTelefone());
            pst.setString(4,eVO.getGerente());
            pst.setString(5, eVO.getCnpj());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("\nErro ao atualizar CNPJ!\n" + ex.getMessage());
        }
    }    
}
