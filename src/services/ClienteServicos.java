/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.ClienteDAO;
import dao.DAOFactory;
import java.util.ArrayList;
import model.Cliente;

/**
 *
 * @author 182010049
 */
public class ClienteServicos {
    
    public void cadCliente(Cliente cVO) {
        ClienteDAO cDAO = DAOFactory.getClienteDAO();
        cDAO.cadastrarClienteDAO(cVO);
    }
    
    public void atualizarCliente(Cliente cVO) {
        ClienteDAO cDAO = DAOFactory.getClienteDAO();
        cDAO.atualizaClienteByDoc(cVO);
    }
    
    public void deletarCliente(String cpf) {
        ClienteDAO cDAO = DAOFactory.getClienteDAO();
        cDAO.deletarClienteDAO(cpf);
    }
    
    public Cliente buscarClientebyCPF(String cpf) {
        ClienteDAO cDAO = DAOFactory.getClienteDAO();
        return cDAO.getClienteByDoc(cpf);
    }
    
    public ArrayList<Cliente> getClientes() {
        ClienteDAO cDAO = DAOFactory.getClienteDAO();
        return cDAO.getClientesDAO();
    }
    
}
