/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author 182010049
 */
public class DAOFactory {
    private static ClienteDAO cDAO = new ClienteDAO();
    
    public static ClienteDAO getClienteDAO() {
        return cDAO;
    }
}
