import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

        while (true) {
            System.out.println("================================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("================================");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            if (opcao == 0) {
                break;
            }

            switch (opcao) {
                case 1:
                    System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                    char tipo = scanner.nextLine().charAt(0);
                    if (tipo == 'F') {
                        System.out.println("Digite o id da pessoa:");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consumir a nova linha
                        System.out.println("Insira os dados...");
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Idade: ");
                        int idade = scanner.nextInt();
                        scanner.nextLine(); // Consumir a nova linha

                        PessoaFisica pf = new PessoaFisica(id, nome, cpf, idade);
                        repoFisica.inserir(pf);
                    } else if (tipo == 'J') {
                        System.out.println("Digite o id da pessoa:");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consumir a nova linha
                        System.out.println("Insira os dados...");
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("CNPJ: ");
                        String cnpj = scanner.nextLine();

                        PessoaJuridica pj = new PessoaJuridica(id, nome, cnpj);
                        repoJuridica.inserir(pj);
                    }
                    break;

                case 2:
                    System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                    tipo = scanner.nextLine().charAt(0);
                    if (tipo == 'F') {
                        System.out.print("Digite o id da pessoa fisica: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consumir a nova linha
                        PessoaFisica pf = repoFisica.obter(id);
                        if (pf != null) {
                            System.out.println("Dados atuais:");
                            pf.exibir();
                            System.out.println("Insira os novos dados...");
                            System.out.print("Nome: ");
                            pf.setNome(scanner.nextLine());
                            System.out.print("CPF: ");
                            pf.setCpf(scanner.nextLine());
                            System.out.print("Idade: ");
                            pf.setIdade(scanner.nextInt());
                            scanner.nextLine(); // Consumir a nova linha
                            repoFisica.alterar(pf);
                        } else {
                            System.out.println("Pessoa Fisica não encontrada.");
                        }
                    } else if (tipo == 'J') {
                        System.out.print("Digite o id da pessoa juridica: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consumir a nova linha
                        PessoaJuridica pj = repoJuridica.obter(id);
                        if (pj != null) {
                            System.out.println("Dados atuais:");
                            pj.exibir();
                            System.out.println("Insira os novos dados...");
                            System.out.print("Nome: ");
                            pj.setNome(scanner.nextLine());
                            System.out.print("CNPJ: ");
                            pj.setCnpj(scanner.nextLine());
                            repoJuridica.alterar(pj);
                        } else {
                            System.out.println("Pessoa Juridica não encontrada.");
                        }
                    }
                    break;

                case 3:
                    System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                    tipo = scanner.nextLine().charAt(0);
                    if (tipo == 'F') {
                        System.out.print("Digite o id da pessoa fisica: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consumir a nova linha
                        repoFisica.excluir(id);
                    } else if (tipo == 'J') {
                        System.out.print("Digite o id da pessoa juridica: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consumir a nova linha
                        repoJuridica.excluir(id);
                    }
                    break;

                case 4:
                    System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                    tipo = scanner.nextLine().charAt(0);
                    if (tipo == 'F') {
                        System.out.print("Digite o id da pessoa fisica: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consumir a nova linha
                        PessoaFisica pf = repoFisica.obter(id);
                        if (pf != null) {
                            pf.exibir();
                        } else {
                            System.out.println("Pessoa Fisica não encontrada.");
                        }
                    } else if (tipo == 'J') {
                        System.out.print("Digite o id da pessoa juridica: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consumir a nova linha
                        PessoaJuridica pj = repoJuridica.obter(id);
                        if (pj != null) {
                            pj.exibir();
                        } else {
                            System.out.println("Pessoa Juridica não encontrada.");
                        }
                    }
                    break;

                case 5:
                    System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                    tipo = scanner.nextLine().charAt(0);
                    if (tipo == 'F') {
                        for (PessoaFisica pf : repoFisica.obterTodos()) {
                            pf.exibir();
                        }
                    } else if (tipo == 'J') {
                        for (PessoaJuridica pj : repoJuridica.obterTodos()) {
                            pj.exibir();
                        }
                    }
                    break;

                case 6:
                    System.out.print("Digite o prefixo do arquivo: ");
                    String prefixo = scanner.nextLine();
                    repoFisica.persistir(prefixo + ".fisica.bin");
                    repoJuridica.persistir(prefixo + ".juridica.bin");
                    break;

                case 7:
                    System.out.print("Digite o prefixo do arquivo: ");
                    prefixo = scanner.nextLine();
                    try {
                        repoFisica.recuperar(prefixo + ".fisica.bin");
                        repoJuridica.recuperar(prefixo + ".juridica.bin");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Erro ao recuperar dados: " + e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        scanner.close();
    }
}

