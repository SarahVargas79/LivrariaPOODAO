/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livrariapoo;

import controller.CCliente;
import controller.CEditora;
import controller.CLivro;
import controller.CVendaLivro;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import static livrariapoo.LivrariaPOO.leiaNumInt;
import model.Cliente;
import model.Editora;
import model.Livro;
import model.VendaLivro;
import services.ClienteServicos;
import services.EditoraServicos;
import services.LivroServicos;
import services.ServicosFactory;
import services.VendaLivroServicos;

import util.Validadores;

/**
 *
 * @author 182010049
 */
public class LivrariaPOO {

    public static CCliente cadCliente = new CCliente();
    public static CEditora cadEditora = new CEditora();
    public static CLivro cadLivro = new CLivro();
    public static CVendaLivro cadVendaLivro = new CVendaLivro();
    public static Scanner leia = new Scanner(System.in);

    public static int leiaNumInt() {//Dois scanner para não crachar a aplicação.
        Scanner leiaNum = new Scanner(System.in);
        int num = 99; //valor inválido
        try {//try como um "TENTAR EXECUTAR UM TRECHO DE CÓDIGO", se não executar esse trecho vai se para o cath onde as chamadas exceções (erros) são tratadas, se não ele é ignorado.
            return leiaNum.nextInt();
        } catch (Exception e) {//O bloco try diz "que tal código pode gerar exceção(erro)" e o bloco catch faz o "tratamento  para essa exceção(erro)".
            System.out.println("Tente novamente!");
            leiaNumInt();
        }
        return num;
    }

    public static float leiaNumFloat() {//Dois scanner para não crachar a aplicação.
        Scanner leiaNum = new Scanner(System.in);
        float num = 99; //valor inválido
        try {//try como um "TENTAR EXECUTAR UM TRECHO DE CÓDIGO", se não executar esse trecho vai se para o cath onde as chamadas exceções (erros) são tratadas, se não ele é ignorado.
            num = leiaNum.nextFloat();
        } catch (Exception e) {//O bloco try diz "que tal código pode gerar exceção(erro)" e o bloco catch faz o "tratamento  para essa exceção(erro)".
            System.out.println("Tente novamente!");
            leiaNumFloat();
        }
        return num;
    }

    public static void menuP() {
        System.out.println("\n.:Livraria:.");
        System.out.println("1 - Ger.Clientes");
        System.out.println("2 - Ger.Editoras");
        System.out.println("3 - Ger.Livros");
        System.out.println("4 - Venda Livro");
        System.out.println("0 - sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void subMenu(int op) {
        String tpCad = null;
        switch (op) {
            case 1:
                tpCad = "Cliente";
                break;
            case 2:
                tpCad = "Editora";
                break;
            case 3:
                tpCad = "Livro";
                break;
        }
        System.out.println("\nGer." + tpCad + ":.");
        System.out.println("1 - Cadastrar" + tpCad);
        System.out.println("2 - Editar" + tpCad);
        System.out.println("3 - Listar" + tpCad + "s");
        System.out.println("4 - Deletar" + tpCad);
        System.out.println("0 - Voltar");
        System.out.print("Escolha uma opção: ");
    }

    public static void cadastrarCliente() {
        int idCliente;
        String nomeCliente;
        String cpf;
        String cnpj = null;
        String endereco;
        String telefone;
        ClienteServicos clienteS = ServicosFactory.getClienteServicos();

        System.out.println("-- Cadastro de Cliente --");
        System.out.print("\nDigite o CPF do cliente: ");
        boolean cpfIs;
        int opCPF;
        do {
            cpf = leia.nextLine();
            cpfIs = Validadores.isCPF(cpf);
            if (!cpfIs) {
                System.out.println("\nCPF inválido, \nDeseja tentar novamente? 1 - Sim | 2 - Não");
                opCPF = leiaNumInt();
                if (opCPF == 1) {
                    System.out.print("\nDigite o CPF do cliente: ");
                } else if (opCPF == 2) {
                    System.out.println("\nCadastro cancelado pelo usuário!");
                    break;
                }

            } else if (clienteS.buscarClientebyCPF(cpf).getCpf() != null) {
                System.out.println("\nCliente já cadastrado!");
            } else {
                System.out.print("\nInforme o nome do cliente: ");
                nomeCliente = leia.nextLine();
                nomeCliente = nomeCliente.toUpperCase();
                System.out.print("\nInforme o endereço do cliente: ");
                endereco = leia.nextLine();
                endereco = endereco.toUpperCase();
                System.out.print("\nInforme o telefone do cliente: ");
                telefone = leia.nextLine();
                idCliente = cadCliente.geraID();
                Cliente cli = new Cliente(idCliente, nomeCliente, cpf, cnpj, endereco, telefone);
                clienteS.cadCliente(cli);
                cadCliente.addCliente(cli);
                System.out.println("\nCliente cadastrado com sucesso!");
            }
        } while (!cpfIs);

    }//fim cadastrar cliente

    private static void editarCliente() {
        System.out.println("-- Editar Cliente --");
        System.out.print("\nDigite o CPF do cliente: ");
        String cpf = leia.nextLine();
        if (Validadores.isCPF(cpf)) {
            Cliente cli = cadCliente.getClienteCPF(cpf);
            if (cli != null) {
                System.out.println("1 - Nome:\t" + cli.getNomeCliente());
                System.out.println("2 - End.:\t" + cli.getEndereco());
                System.out.println("3 - Fone:\t" + cli.getTelefone());
                System.out.println("4 - Todos os campos acima?");
                System.out.print("\nQual campo deseja alterar? \nDigite aqui sua opção: ");
                int opEditar = leiaNumInt();

                if (opEditar == 1 || opEditar == 4) {// "||" pipe significa "ou"
                    System.out.print("\nInforme o nome do cliente: ");
                    cli.setNomeCliente(leia.nextLine());
                }
                if (opEditar == 2 || opEditar == 4) {
                    System.out.print("\nInforme o endereço do cliente: ");
                    cli.setEndereco(leia.nextLine());
                }
                if (opEditar == 3 || opEditar == 4) {
                    System.out.print("\nInforme o telefone do cliente: ");
                    cli.setTelefone(leia.nextLine());
                }
                if (opEditar < 1 || opEditar > 4) {
                    System.out.println("\nOpção inválida");
                }
                ClienteServicos clienteS = ServicosFactory.getClienteServicos();
                clienteS.atualizarCliente(cli);
                /*
                switch (opEditar) {
                    case 1:
                        System.out.print("\nInforme o nome: ");
                        cli.setNomeCliente(leia.nextLine());
                        break;
                    case 2:
                        System.out.print("\nInforme o endereço: ");
                        cli.setEndereco(leia.nextLine());
                        break;
                    case 3:
                        System.out.print("\nInforme o telefone: ");
                        cli.setTelefone(leia.nextLine());
                        break;
                    case 4:
                        System.out.print("\nInforme todos os campos abaixo: ");
                        System.out.print("\nInforme o nome: ");
                        cli.setNomeCliente(leia.nextLine());
                        System.out.print("\nInforme o endereço: ");
                        cli.setEndereco(leia.nextLine());
                        System.out.print("\nInforme o telefone: ");
                        cli.setTelefone(leia.nextLine());
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
                 */
                System.out.println("\nCliente:\n" + cli.toString());
            } else {
                System.out.println("\nCliente não cadastrado!");
            }
        } else {
            System.out.println("\nCPF inválido!");
        }
    }//fim editar cliente

    public static void imprimirListaClientes() {
        System.out.println("-- Lista de Clientes --");
        ClienteServicos clienteS = ServicosFactory.getClienteServicos();
        for (Cliente cli : clienteS.getClientes()) {
            System.out.println("\n---");
            System.out.println("Nome:\t" + cli.getNomeCliente());//\t faz tabulação
            System.out.println("CPF:\t" + cli.getCpf());
            System.out.println("End.:\t" + cli.getEndereco());
            System.out.println("Fone:\t" + cli.getTelefone());
        }
    }//fim imprimir lista de clientes

    public static void deletarCliente() {
        System.out.println("-- Deletar Cliente --");
        System.out.print("\nDigite o CPF do cliente: ");
        String cpf = leia.next();
        ClienteServicos clienteS = ServicosFactory.getClienteServicos();
        if (Validadores.isCPF(cpf)) {
            Cliente cli = clienteS.buscarClientebyCPF(cpf);
            if (cli.getCpf() != null) {
                //cadCliente.removeCliente(cli);

                System.out.println("\nConfirmar deletar cliente? 1 - Sim | 2 - Não");
                int opCliente = leiaNumInt();
                if (opCliente == 1) {
                    clienteS.deletarCliente(cpf);
                    System.out.println("\nCliente deletado com sucesso!");
                } else if (opCliente == 2) {
                    System.out.println("\nUsuário cancelou remoção de cliente!");
                }
            } else {
                System.out.println("\nCliente não consta na base de dados!");
            }
        } else {
            System.out.println("\nCPF inválido!");
        }
    }//fim deletar cliente

    public static void cadastrarEditora() {
        int idEditora;
        String nomeEditora;
        String cnpj;
        String endereco;
        String telefone;
        String gerente;
        EditoraServicos editoraS = ServicosFactory.getEditoraServicos();

        System.out.println("-- Registrar Editora --");
        System.out.print("\nDigite o CNPJ da editora: ");
        boolean cnpjIs;
        int opCNPJ;
        do {
            cnpj = leia.nextLine();
            cnpjIs = Validadores.isCNPJ(cnpj);
            if (!cnpjIs) {
                System.out.println("\nCNPJ inválido, \nDeseja tentar novamente? 1 - Sim | 2 - Não");
                opCNPJ = leiaNumInt();
                if (opCNPJ == 1) {
                    System.out.print("\nDigite o CNPJ da editora: ");
                } else if (opCNPJ == 2) {
                    System.out.println("\nRegistro cancelado pelo usuário!");
                    break;
                }
            } else if (editoraS.buscarEditorabyCNPJ(cnpj).getCnpj() != null) {
                System.out.println("\nEditora já registrada!");
            } else {
                System.out.print("\nInforme o nome da editora: ");
                nomeEditora = leia.nextLine();
                nomeEditora = nomeEditora.toUpperCase();
                System.out.print("\nInforme o endereço da editora: ");
                endereco = leia.nextLine();
                endereco = endereco.toUpperCase();
                System.out.print("\nInforme o telefone da editora: ");
                telefone = leia.nextLine();
                System.out.print("\nInforme o nome do gerente da editora: ");
                gerente = leia.nextLine();
                idEditora = cadEditora.geraID();
                Editora ed = new Editora(idEditora, nomeEditora, cnpj, endereco, telefone, gerente);
                editoraS.cadEditora(ed);
                cadEditora.addEditora(ed);
                System.out.println("\nEditora registrada com sucesso!");
            }
        } while (!cnpjIs);

    }//fim cadastro editora

    private static void editarEditora() {
        System.out.println("-- Edição de Editora --");
        System.out.print("\nDigite o CNPJ da editora: ");
        String cnpj = leia.nextLine();
        if (Validadores.isCNPJ(cnpj)) {
            Editora ed = cadEditora.getEditoraCNPJ(cnpj);
            if (ed != null) {
                System.out.println("\n");
                System.out.println("1 - Nome:    \t" + ed.getNomeEditora());
                System.out.println("2 - Endereço:\t" + ed.getEndereco());
                System.out.println("3 - Telefone:\t" + ed.getTelefone());
                System.out.println("4 - Gerente: \t" + ed.getGerente());
                System.out.println("5 - Todos os campos acima? ");
                System.out.print("\nQual campo deseja alterar? \nDigite aqui sua opção:");
                int opEditar = leiaNumInt();
                if (opEditar == 1 || opEditar == 5) {
                    System.out.print("\nInforme o nome da editora: ");
                    ed.setNomeEditora(leia.nextLine());
                }
                if (opEditar == 2 || opEditar == 5) {
                    System.out.print("\nInforme o endereço da editora: ");
                    ed.setEndereco(leia.nextLine());
                }
                if (opEditar == 3 || opEditar == 5) {
                    System.out.print("\nInforme o telefone da editora: ");
                    ed.setTelefone(leia.nextLine());
                }
                if (opEditar == 4 || opEditar == 5) {
                    System.out.print("\nInforme o nome do gerente da editora: ");
                    ed.setGerente(leia.nextLine());
                }
                if (opEditar < 1 || opEditar > 5) {
                    System.out.print("\nOpção inválida\n");
                }
                EditoraServicos editoraS = ServicosFactory.getEditoraServicos();
                editoraS.atualizarEditora(ed);

                System.out.println("\nEditora:\n" + ed.toString());
            } else {
                System.out.println("\nEditora não registrada!");
            }
        } else {
            System.out.println("\nCNPJ inválido");
        }
    }//fim editar editora

    private static void imprimirListaEditoras() {
        System.out.println("-- Lista de Editoras --");
        EditoraServicos editoraS = ServicosFactory.getEditoraServicos();
        for (Editora ed : editoraS.getEditoras()) {
            System.out.println("\n---");
            System.out.println("Nome:    \t" + ed.getNomeEditora());
            System.out.println("CNPJ:    \t" + ed.getCnpj());
            System.out.println("Endereço:\t" + ed.getEndereco());
            System.out.println("Telefone:\t" + ed.getTelefone());
            System.out.println("Gerente: \t" + ed.getGerente());
        }
    }//fim imprimir lista de editoras

    private static void deletarEditora() {
        System.out.println("-- Deletar Editora --");
        System.out.print("\nDigite o CNPJ da editora: ");
        String cnpj = leia.next();
        EditoraServicos editoraS = ServicosFactory.getEditoraServicos();
        if (Validadores.isCNPJ(cnpj)) {
            Editora ed = editoraS.buscarEditorabyCNPJ(cnpj);
            if (ed.getCnpj() != null) {
                //cadEditora.removeEditoras(ed);

                System.out.println("\nConfirmar deletar editora? 1 - Sim | 2 - Não");
                int opEdit = leiaNumInt();
                if (opEdit == 1) {
                    editoraS.deletarEditora(cnpj);
                    System.out.println("\nEditora deletada com sucesso!");
                } else if (opEdit == 2) {
                    System.out.println("\nUsuário cancelou remoção de editora!");
                }
            } else {
                System.out.println("\nEditora não consta na base de dados!");
            }
        } else {
            System.out.println("\nCNPJ inválido");
        }
    }//fim deletar editora

    private static void cadastrarLivro() {
        int idLivro;
        String titulo;
        String autor;
        String assunto;
        String isbn;
        int estoque;
        float preco;

        LivroServicos livroS = ServicosFactory.getLivroServicos();
        EditoraServicos editoraS = ServicosFactory.getEditoraServicos();
        System.out.println("-- Cadastro de Livro --");
        System.out.print("\nDigite o ISBN do livro: ");
        isbn = leia.nextLine();
        if (livroS.buscaLivroISBN(isbn).getIsbn() != null) {
            System.out.println("\nLivro já cadastrado!");
        } else {
            idLivro = cadLivro.geraID();//Não precisa alterar para o serviço do livro, pois o id será gerado pelo banco.
            System.out.print("\nInforme o titulo do livro: ");
            titulo = leia.nextLine();
            System.out.print("\nInforme o autor do livro: ");
            autor = leia.nextLine();
            System.out.print("\nInforme o assunto do livro: ");
            assunto = leia.nextLine();
            System.out.print("\nInforme o estoque do livro: ");
            estoque = leiaNumInt();
            System.out.print("\nInforme o preço do livro: ");
            preco = leiaNumFloat();
            boolean isCNPJ = false;
            Editora idEditora = null;
            do {
                System.out.print("\nInforme o CNPJ da editora: ");
                String cnpj = leia.nextLine();
                isCNPJ = Validadores.isCNPJ(cnpj);
                if (isCNPJ) {
                    idEditora = editoraS.buscarEditorabyCNPJ(cnpj);
                    if (idEditora.getCnpj() == null) {
                        System.out.println("\nEditora não cadastrada");
                        isCNPJ = false;
                    } else {
                        System.out.println("\nEditora: " + idEditora.getNomeEditora());
                    }
                } else {
                    System.out.println("\nCNPJ inválido!");
                }
            } while (!isCNPJ);
            Livro li = new Livro(idLivro, titulo, autor, assunto, isbn, estoque, preco, idEditora);
            livroS.cadLivro(li);
            cadLivro.addLivro(li);
            System.out.println("\nLivro cadastrado com sucesso!");
        }
    }//fim cadastrar Livro

    private static void editarLivro() {
        System.out.println("-- Editar Livro --");
        System.out.print("\nDigite o ISBN do livro: ");
        String isbn = leia.nextLine();
        LivroServicos livroS = ServicosFactory.getLivroServicos();
        Livro li = livroS.buscaLivroISBN(isbn);
        if (li.getIsbn() != null) {
            System.out.println("Livro selecionado: " + li.getTitulo());
            System.out.println("O que deseja alterar: ");
            System.out.println("1 - Estoque: \t" + li.getEstoque());
            System.out.println("2 - Preço:     \t" + li.getPreco());
            System.out.println("3 - Todos os campos acima? ");
            System.out.println("0 - Cancelar");
            System.out.print("\nDigite aqui sua opção: ");
            int opEditar = leiaNumInt();
            if (opEditar == 1 || opEditar == 3) {
                System.out.println("\nEstoque atual:\t" + li.getEstoque());
                System.out.print("Informe novo estoque: ");
                li.setEstoque(leiaNumInt());
            }
            if (opEditar == 2 || opEditar == 3) {
                System.out.println("\nPreço atual:\t" + li.getPreco());
                System.out.print("Informe novo preço: ");
                li.setPreco(leiaNumFloat());
            }
            if (opEditar == 0) {
                System.out.print("\nOperação cancelada pelo usuário\n");
                return;
            }
            livroS.atualizarLivro(li);
            System.out.println("\nLivro editado:\n" + li.toString());
        } else {
            System.out.println("\nISBN Inválido!");
        }
    }//fim editar livro

    private static void imprimirListaLivros() {
        System.out.println("-- Lista de Livros --");
        LivroServicos livroS = ServicosFactory.getLivroServicos();
        if (!livroS.buscaLivros().isEmpty()) {
            for (Livro li : livroS.buscaLivros()) {
                /*System.out.println("---\nTitulo:\t\t" + li.getTitulo());
            System.out.println("Autor:         \t" + li.getAutor());
            System.out.println("Asssunto:   \t" + li.getAssunto());
            System.out.println("Isbn:          \t" + li.getIsbn());
            System.out.println("Estoque:    \t" + li.getEstoque());
            System.out.println("Preço:        \t" + li.getPreco());
            System.out.println("Editora:     \t" + li.getIdEditora().getNomeEditora());*/
                System.out.println(li.toString());
            }
        } else {
            System.out.println("Não tem livros cadastrados!");
        }
    }//fim imprimir lista de livros

    private static void deletarLivro() {
        System.out.println("-- Deletar Livro --");
        System.out.print("\nDigite o ISBN do livro: ");
        String isbn = leia.nextLine();
        LivroServicos livroS = ServicosFactory.getLivroServicos();
        Livro li = livroS.buscaLivroISBN(isbn);
        if (li.getIsbn() != null) {
            //cadLivro.removeLivros(li);

            System.out.println("\nConfirmar deletar livro? 1 - Sim | 2 - Não");
            int opDelet = leiaNumInt();
            if (opDelet == 1) {
                livroS.deletarLivro(isbn);
                System.out.println("\nLivro: " + li.getTitulo() + " deletado!");
            } else if (opDelet == 2) {
                System.out.println("Usuário cancelou remoção de livro!");
            }
        } else {
            System.out.println("\nISBN não encontrado!");
        }
    }//fim deletar livro

    public static void vendaLivro() {
        int idVendaLivro;
        Cliente idCliente = null;
        ArrayList<Livro> livros = new ArrayList<>();
        float subTotal = 0;
        LocalDate dataVenda = LocalDate.now();
        VendaLivroServicos vlS = ServicosFactory.getVendaLivros();
        ClienteServicos clienteS = ServicosFactory.getClienteServicos();
        LivroServicos livroS = ServicosFactory.getLivroServicos();
        

        do {//Seleciona cliente   
            System.out.print("\nDigite o CPF do cliente: ");
            String CPF = leia.nextLine();
            if (Validadores.isCPF(CPF)) {
                //idCliente = cadCliente.getClienteCPF(CPF);
                idCliente = clienteS.buscarClientebyCPF(CPF);
                if (idCliente.getCpf() == null) {
                    System.out.println("\nCliente não cadastrado, tente novamente!");
                }
            } else {
                System.out.println("\nCPF inválido, tente novamente!");
            }
        } while (idCliente.getCpf() == null);

        boolean venda = true;
        do {
            Livro li = null;
            String isbn;
            do {
                System.out.print("\nDigite o ISBN: ");
                isbn = leia.nextLine();
                //li = cadLivro.getLivroISBN(isbn);
                li = livroS.buscaLivroISBN(isbn);
                if (li.getIsbn() == null) {
                    System.out.println("\nLivro não encontrado, tente novamente!");
                }
            } while (li.getIsbn() == null);
            if (li.getEstoque() > 0) {
                livros.add(li);
                //cadLivro.atualizaEstoqueLivro(li.getIsbn());
                int estoque = li.getEstoque() -1;
                li.setEstoque(estoque);
                livroS.atualizarLivro(li);
                subTotal += li.getPreco();
            } else {
                System.out.println("\n" + li.getTitulo() + " não tem mais estoque.");
            }
            System.out.println("\nDeseja comprar mais livros nesta venda? \n1 - sim | 2 -Não \nDigite sua opção: ");
            if (leiaNumInt() == 2) {
                venda = false;
            }
        } while (venda);
        idVendaLivro = cadVendaLivro.geraID();
        VendaLivro vl = new VendaLivro(idVendaLivro, idCliente, livros, subTotal, dataVenda);
        //cadVendaLivro.addVendaLivro(vl);
        vlS.vendaLivros(vl);
        System.out.println("\n-- Venda --\n" + vl.toString());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {//A main dispara a chamada.
        // TODO code application logic here

        //CCliente ccli = new CCliente();
        //Chamando mock. 
        cadCliente.mockClientes();
        cadEditora.mockEditoras();
        cadLivro.mockLivros();
        cadVendaLivro.mockVendaLivros();

        int opM;

        do {
            menuP();
            opM = leiaNumInt();
            switch (opM) {
                case 1:
                case 2:
                case 3:

                    int opSM;

                    do {
                        subMenu(opM);
                        opSM = leiaNumInt();
                        switch (opSM) {
                            case 1:
                                System.out.println("\n-- Cadastrar --\n");
                                //usar opM para definir qual cadastro
                                if (opM == 1) {
                                    cadastrarCliente();
                                } else if (opM == 2) {
                                    cadastrarEditora();
                                } else if (opM == 3) {
                                    cadastrarLivro();
                                }
                                break;
                            case 2:
                                System.out.println("\n-- Editar --\n");
                                if (opM == 1) {
                                    editarCliente();
                                } else if (opM == 2) {
                                    editarEditora();
                                } else if (opM == 3) {
                                    editarLivro();
                                }
                                break;
                            case 3:
                                System.out.println("\n-- Listar --\n");
                                if (opM == 1) {
                                    imprimirListaClientes();
                                } else if (opM == 2) {
                                    imprimirListaEditoras();
                                } else if (opM == 3) {
                                    imprimirListaLivros();
                                }
                                break;
                            case 4:
                                System.out.println("\n-- Deletar --\n");
                                if (opM == 1) {
                                    deletarCliente();
                                } else if (opM == 2) {
                                    deletarEditora();
                                } else if (opM == 3) {
                                    deletarLivro();
                                }
                                break;
                            case 0:
                                System.out.println("\n-- Menu Principal --");
                                break;
                            default:
                                System.out.println("Opção inválida, tente novamente!");
                                break;
                        }

                    } while (opSM != 0);//subMenu
                    break;
                case 4:
                    System.out.println("\n-- Venda Livro --");
                    vendaLivro();
                    break;
                case 0:
                    System.out.println("\nAplicação encerrada pelo usuário!!");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente!");
                    break;
            }
        } while (opM != 0);//fim sistema
    }
}
