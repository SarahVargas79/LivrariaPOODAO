/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author 182010049
 */
public class ServicosFactory {
    
    private static ClienteServicos cServicos = new ClienteServicos();
    
    public static ClienteServicos getClienteServicos() {
        return cServicos;
    }
    
    private static EditoraServicos eServicos = new EditoraServicos();
    
    public static EditoraServicos getEditoraServicos() {
        return eServicos;
    }
    
}
