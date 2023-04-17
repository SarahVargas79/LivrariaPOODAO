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
import model.Livro;
import services.EditoraServicos;
import services.ServicosFactory;

/**
 *
 * @author 182010049
 */
public class LivroDAO {

    public void cadastrarLivroDAO(Livro liVO) {

        try {
            //busca conexão com o BD
            Connection con = Conexao.getConexao();//abrindo conexão
            //cria espaço de trabalho SQL, é a área no Java onde vamo executar os sripts SQL.
            String sql;
            sql = "insert into livros values (null, ?, ?, ?, ?, ?, ?, ?)";//? paramêtros que está esperando para receber, null é o id.
            PreparedStatement pst = con.prepareStatement(sql);
            //Estes são os paramêtros
            pst.setString(1, liVO.getTitulo());
            pst.setString(2, liVO.getAutor());
            pst.setString(3, liVO.getAssunto());
            pst.setString(4, liVO.getIsbn());
            pst.setInt(5, liVO.getEstoque());
            pst.setFloat(6, liVO.getPreco());
            pst.setInt(7, liVO.getIdEditora().getIdEditora());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("\nErro ao Cadastrar!\n" + ex.getMessage());
        }
    }//fim cadastrar

    public ArrayList<Livro> getLivrosDAO() {
        ArrayList<Livro> livros = new ArrayList<>();
        try {//Para tratar erros
            Connection con = Conexao.getConexao();
            String sql = "select livros.*, e.cnpj from livros join editoras e using(ideditora)";//para executar para o banco(mysql)
            Statement stm = con.createStatement();
            EditoraServicos editoraS = ServicosFactory.getEditoraServicos();
            ResultSet rs = stm.executeQuery(sql);//ResultSet estrutura no java, é um meio de campo entre o banco de dados e o java(aplicação).
            while (rs.next()) {
                Livro li = new Livro();//objeto editora
                //lado do java |x| (lado do banco)
                li.setIdLivro(rs.getByte("idlivro"));
                li.setTitulo(rs.getString("titulo"));
                li.setAutor(rs.getString("autor"));
                li.setAssunto(rs.getString("assunto"));
                li.setIsbn(rs.getString("isbn"));
                li.setEstoque(rs.getInt("estoque"));
                li.setPreco(rs.getFloat("preco"));
                li.setIdEditora(editoraS.buscarEditorabyCNPJ(rs.getString("cnpj")));
                livros.add(li);
            }
        } catch (SQLException ex) {
            System.out.println("\nErro ao listar livros!\n" + ex.getMessage());
        }
        return livros;

    }//fim do listar

    public Livro getLivroByISBN(String isbn) {
        EditoraServicos editoraS = ServicosFactory.getEditoraServicos();
        Livro li = new Livro();
        try {
            Connection con = Conexao.getConexao();//estabelecendo conexão
            String sql = "select livros.*, e.cnpj from livros join editoras e using(ideditora) where isbn = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, isbn);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                //lado do java |x| (lado do banco)
                li.setIdLivro(rs.getByte("idlivro"));
                li.setTitulo(rs.getString("titulo"));
                li.setAutor(rs.getString("autor"));
                li.setAssunto(rs.getString("assunto"));
                li.setIsbn(rs.getString("isbn"));
                li.setEstoque(rs.getInt("estoque"));
                li.setPreco(rs.getFloat("preco"));
                li.setIdEditora(editoraS.buscarEditorabyCNPJ(rs.getString("cnpj")));
            }
        } catch (SQLException ex) {
            System.out.println("\nErro ao buscar livro!\n" + ex.getMessage());
        }
        return li;
    }//fim buscar livro

    public void atualizaLivroByDoc(Livro liVO) {

        try {
            Connection con = Conexao.getConexao();
            String sql = "update livros set estoque = ?, preco = ? where isbn = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, liVO.getEstoque());
            pst.setFloat(2, liVO.getPreco());
            pst.setString(3, liVO.getIsbn());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("\nErro ao atualizar livro!\n" + ex.getMessage());
        }
    }

    public void deletarLivroDAO(String isbn) {

        try {
            Connection con = Conexao.getConexao();
            String sql = "delete from livros where isbn = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, isbn);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("\nErro ao deletar livro!\n" + ex.getMessage());
        }
    }//fim deletar

}//fim da classe
