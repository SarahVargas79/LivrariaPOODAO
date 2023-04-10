/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.DAOFactory;
import dao.EditoraDAO;
import java.util.ArrayList;
import model.Editora;

/**
 *
 * @author 182010049
 */
public class EditoraServicos {
    
    public void cadEditora(Editora eVO) {
        EditoraDAO eDAO = DAOFactory.getEditoraDAO();
        eDAO.cadastrarEditoraDAO(eVO);
    }
    
    public void atualizarEditora(Editora eVO) {
        EditoraDAO eDAO = DAOFactory.getEditoraDAO();
        eDAO.atualizaEditoraByDoc(eVO);
    }
    
    public void deletarEditora(String cnpj
    ) {
        EditoraDAO eDAO = DAOFactory.getEditoraDAO();
        eDAO.deletarEditoraDAO(cnpj);
    }
    
    public Editora buscarEditorabyCNPJ(String cnpj) {
        EditoraDAO eDAO = DAOFactory.getEditoraDAO();
        return eDAO.getEditoraByDoc(cnpj);
    }
    
    public ArrayList<Editora> getEditoras() {
        EditoraDAO eDAO = DAOFactory.getEditoraDAO();
        return eDAO.getEditorasDAO();
    }
}
