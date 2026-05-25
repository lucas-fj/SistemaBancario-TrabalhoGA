//**Classe do tipo Conta Poupanca herda atributos da classe ContaBancaria */

public class ContaPoupanca extends ContaBancaria{
    
    //atributos
    private int diaAniversario;

    //construtor
    public ContaPoupanca(Cliente cliente, double saldoInicial, int diaAniversario) {
        super(cliente, saldoInicial);
        this.diaAniversario = diaAniversario;

        
    }

    //getters e setters
    public int getDiaAniversario() {
        return diaAniversario;
    }

    public void setDiaAniversario(int diaAniversario) {
        this.diaAniversario = diaAniversario;
    }

    //métodos
    @Override
    public void movimenta(Operacao op){
         if (op.getTipo() == 'D') { //depósito 
            setSaldo(getSaldo() + op.getValor());
            getDepositos().registrarMovimentacao(op.getValor());
            System.out.printf("Depósito realizado! Novo saldo: R$%.2f\n", getSaldo());

            if (getSaldo() > getSaldoMax()) {
                setSaldoMax(getSaldo());
            }
            
        } else if (op.getTipo() == 'S') {//saque

        // verificar saldo
        if (op.getValor() > getSaldo()) {
            System.out.println("Saldo insuficiente!");
            return;
        }

        double[] notas = {100.0, 50.0, 20.0, 10.0, 5.0, 2.0};
        int[] qtdNotas = new int[notas.length];

        double restante = op.getValor();

        for (int i = 0; i < notas.length; i++) {
            qtdNotas[i] = (int) (restante / notas[i]);
            restante = restante % notas[i];
        }

        // não consegue formar valor
        if (restante != 0) {
            System.out.println("Não é possível sacar esse valor com as notas disponíveis.");
            return;
        }

        // mostrar notas
        System.out.println("\nNotas entregues:");
        for (int i = 0; i < notas.length; i++) {
            if (qtdNotas[i] > 0) {
                System.out.printf(qtdNotas[i] + " nota(s) de R$%.2f\n", notas[i]);
            }
        }

            // atualizar saldo
            setSaldo(getSaldo() - op.getValor());

            // registrar saque
            getSaques().registrarMovimentacao(op.getValor());

            System.out.printf("Saque realizado! Novo saldo: R$%.2f\n", getSaldo());

            if (getSaldo() < getSaldoMin()) {
                setSaldoMin(getSaldo());;
            }

        } else if(op.getTipo() == 'J'){ //juros
            double juros2 = getSaldo() * (op.getValor() / 100);
            op.setValor(juros2);
            setSaldo(getSaldo() + op.getValor());
            getJuros().registrarMovimentacao(op.getValor());

            if (getSaldo() > getSaldoMax()) {
                setSaldoMax(getSaldo());;
            }

            System.out.printf("Juros aplicado! Novo saldo: R$%.2f\n", getSaldo());
        }
    }
}
