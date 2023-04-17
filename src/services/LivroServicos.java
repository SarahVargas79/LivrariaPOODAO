/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.DAOFactory;
import dao.LivroDAO;
import java.util.ArrayList;
import model.Livro;

/**
 *
 * @author 182010049
 */
public class LivroServicos {
    
    public void cadLivro(Livro liVO) {//LivroDAO - classe | liDAO - objeto
        LivroDAO liDAO = DAOFactory.getLivroDAO();
        liDAO.cadastrarLivroDAO(liVO);
    }
    
    public ArrayList<Livro> buscaLivros() {
        LivroDAO liDAO = DAOFactory.getLivroDAO();
        return liDAO.getLivrosDAO();
    }
    
    public Livro buscaLivroISBN(String isbn) {
        LivroDAO liDAO = DAOFactory.getLivroDAO();
        return liDAO.getLivroByISBN(isbn);
    }
    
    
    public void atualizarLivro(Livro liVO) {
        LivroDAO liDAO = DAOFactory.getLivroDAO();
        liDAO.atualizaLivroByDoc(liVO);
    }
    
    public void deletarLivro(String isbn)
    {
        LivroDAO liDAO = DAOFactory.getLivroDAO();
        liDAO.deletarLivroDAO(isbn);
    }
    
}
