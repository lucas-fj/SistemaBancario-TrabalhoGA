//**Classe principal para rodar o projeto*/

public class Main {
    public static void main(String[] args) {
        ContaBancaria conta = null; //objeto conta bancária criado como null
        int opcao;

        do{
            System.out.print("\033\143");//comando para limpar a tela sempre que realizar uma das opções
            opcao = Teclado.leInt("\n1- Abrir conta.\n2- Realizar depósito.\n3- Realizar saque.\n4- Aplicar juros.\n5- Extrato.\n6- Integrantes.\n7-Sair.");
            
            switch (opcao) {
                //criando a conta
                case 1:
                    //if para verificar se a conta ja foi criada (caso ja tenha sido opção 1 é bloqueada)
                    if (conta != null) {
                        break;
                    }

                    //recebendo dados do cliente
                    String nome = Teclado.leString("Nome completo: "); 
                    String cpf = Teclado.leString("CPF: ");
                    System.out.println("Digite sua data de nascimento.");
                    int dia = Teclado.leInt("Dia: ");
                    dia = Data.conferirDia(dia);
                    
                    int mes = Teclado.leInt("Mês: ");
                    mes = Data.conferirMes(mes);
                    
                    int ano = Teclado.leInt("Ano: ");
                    ano = Data.conferirAno(ano);

                    //criando o objeto cliente e objeto data
                    Cliente cliente = new Cliente(nome, cpf, new Data(dia, mes, ano));

                    //recebendo saldo inicial e tipo de conta
                    char tipo = Teclado.leChar("Escolha o tipo de conta, Corrente(C), Poupança(P) ou Investimento(I): ");
                    double saldo = Teclado.leDouble("Informe o saldo inicial: ");

                    if (tipo == 'C' || tipo == 'c') {
                        double limite = Teclado.leDouble("Informe o limite:");
                        conta = new ContaCorrente(cliente, saldo, limite);
                    } else if (tipo == 'P' || tipo == 'p') {
                        int diaAniversario = Teclado.leInt("Informe o dia aniversário: ");
                        diaAniversario = Data.conferirDia(diaAniversario);
                        conta = new ContaPoupanca(cliente, saldo, diaAniversario);
                    } else if (tipo == 'I' || tipo == 'i') {
                        System.out.println("Data de vencimento: ");
                        int diaVencimento = Teclado.leInt("Dia: ");
                        diaVencimento = Data.conferirDia(diaVencimento);

                        int mesVencimento = Teclado.leInt("Mês: ");
                        mesVencimento = Data.conferirMes(mesVencimento);

                        int anoVencimento = Teclado.leInt("Ano: ");
                        while (true) {
                            if (anoVencimento >= 2026) {
                                break;
                            } else {
                                System.out.println("Ano de vencimento inválido! Digite novamente:");
                                anoVencimento = Teclado.leInt();
                            }
                        }
                        Data dtVencimento = new Data(diaVencimento, mesVencimento, anoVencimento);
                        conta = new ContaInvestimento(cliente, saldo, dtVencimento);
                    } else {
                        System.out.println("Tipo de conta inválido!");
                        System.out.println("Pressione ENTER para voltar ao menu principal.");
                        Teclado.leString();
                        break;
                    }

                    System.out.println("Conta criada com sucesso! Pressione ENTER para voltar ao menu principal.");
                    Teclado.leString(); //Recebe o clique no enter e retorna ao menu
                    break;
                
                //realizando deposito
                case 2:
                    //if para verificar se a conta ja foi criada
                    if (conta == null) {
                        System.out.println("Crie uma conta primeiro.");
                        System.out.println("Pressione ENTER para voltar ao menu principal.");
                        Teclado.leString(); //Recebe o clique no enter e retorna ao menu
                        break;
                    }

                    double valorDep = Teclado.leDouble("Informe o valor do depósito: ");
                    while (valorDep <= 0 ) {
                        valorDep = Teclado.leDouble("Valor inválido. Digite o valor novamente: ");
                    }

                    Operacao opDep = new Operacao('D', valorDep);//criando o objeto da operação depósito
                    conta.movimenta(opDep);
                    System.out.println("Pressione ENTER para voltar ao menu principal.");
                    Teclado.leString(); //Recebe o clique no enter e retorna ao menu
                    break;

                //realizando saque
                case 3:
                    //if para verificar se a conta ja foi criada
                    if (conta == null) {
                        System.out.println("Crie uma conta primeiro.");
                        System.out.println("Pressione ENTER para voltar ao menu principal.");
                        Teclado.leString(); //Recebe o clique no enter e retorna ao menu
                        break;
                    }

                    if (conta instanceof ContaInvestimento) {
                        System.out.println("Operação indisponível para a conta Investimento !");
                        System.out.println("Pressione ENTER para voltar ao menu principal.");
                        Teclado.leString(); //Recebe o clique no enter e retorna ao menu
                        break;
                    }

                    double valorSaq = Teclado.leDouble("Informe o valor do saque: ");
                    while (valorSaq <= 0) {
                        valorSaq = Teclado.leDouble("Valor inválido. Digite novamente: ");
                    }

                    Operacao opSaq = new Operacao('S', valorSaq);
                    conta.movimenta(opSaq);

                    System.out.println("Pressione ENTER para voltar ao menu principal.");
                    Teclado.leString(); //Recebe o clique no enter e retorna ao menu
                    break;
                
                //aplicando juros
                case 4: 
                // Verifica se a conta já foi criada.
                 if (conta == null) {
                        System.out.println("A conta não foi criada!!");
                        System.out.println("Pressione ENTER para voltar ao menu principal.");
                        Teclado.leString();
                        break;
                    }
                    // Verifica se o Objeto conta é uma instância da classe conta Corrente e retorna um valor booleano, seja ele true ou false.
                    else if(conta instanceof ContaCorrente){
                        System.out.println("Não é possível aplicar juros em uma conta corrente ");
                        System.out.println("Pressione ENTER para voltar ao menu principal.");
                        Teclado.leString(); //Recebe o clique no enter e retorna ao menu
                        break;
                    } else { 
                        System.out.println("Informe o percentual de juros à ser aplicado na conta: ");
                        //Recebe o valor do juros.
                        double jurosI = Teclado.leDouble();
                        //Verifica se o valor é positivo e maior que zero;
                    if (jurosI <= 0) {
                            System.out.println("Não é possível aplicar este valor em percentual!!!");
                            System.out.println("Pressione ENTER para voltar ao menu principal.");
                            Teclado.leString(); //Recebe o clique no enter e retorna ao menu
                            break;
                    } // Se a variável juros for afirmativa, instância um objeto da classe operação com atibutos 'Tipo' e 'Valor;'
                        else {
                        Operacao j = new Operacao('J', jurosI);
                        //Chama o método movimenta da conta Invetimento;
                        conta.movimenta(j);
                        System.out.println("Pressione ENTER para voltar ao menu principal.");
                        Teclado.leString(); //Recebe o clique no enter e retorna ao menu
                        }
                    }
                    break;
                    
                //mostrando o extrato da conta
                case 5:
                    //if para verificar se a conta ja foi criada
                    if (conta == null) {
                        System.out.println("Crie uma conta primeiro.");
                        System.out.println("Pressione ENTER para voltar ao menu principal.");
                        Teclado.leString(); //Recebe o clique no enter e retorna ao menu
                        break;
                    } 

                    conta.extrato();
                    System.out.println();
                    System.out.println("Pressione ENTER para voltar ao menu principal.");
                    Teclado.leString(); //Recebe o clique no enter e retorna ao menu
                    break;
                
                //nomes dos integrantes
                case 6:
                    mostrarNomes();
                    System.out.println("Pressione ENTER para voltar ao menu principal.");
                    Teclado.leString(); //Recebe o clique no enter e retorna ao menu
                    break;
            }
        } while (opcao != 7);
    }

    public static void mostrarNomes(){
        System.out.println("Lucas Furquim Jardim");
        System.out.println("João Pedro Ourique Severo");
        System.out.println("Paula Hoffmann");
        System.out.println("Yasmim Macari Kuhn");
    }
}