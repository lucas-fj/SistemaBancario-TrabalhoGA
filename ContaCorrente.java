//**Classe do tipo Conta Corrente herda atributos da classe ContaBancaria */

public class ContaCorrente extends ContaBancaria {

    // atributos
    private double limiteCredito;

    //construtor
    public ContaCorrente(Cliente cliente, double saldoInicial, double limiteCredito) {
        super(cliente, saldoInicial);
        this.limiteCredito = limiteCredito;
    }

    //getters e setters
    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    //método movimenta (faz apenas as operações de depósito e saque verificando se o saldo está disponível e o limite ok)
    @Override
    public void movimenta(Operacao op){
        if (op.getTipo() == 'D') { //depósito  
        setSaldo(getSaldo() + op.getValor());

        // registra depósito
        getDepositos().registrarMovimentacao(op.getValor());

        System.out.printf("Depósito realizado! Novo saldo: R$%.2f\n", getSaldo());

        if (getSaldo() > getSaldoMax()) {
            setSaldoMax(getSaldo());
        }
        
        } else if (op.getTipo() == 'S') {//saque

        // verificar saldo
        if (op.getValor() >  (getSaldo() + limiteCredito)) {
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
        
        if (getSaldo() < getSaldoMin()) {
            setSaldoMin(getSaldo());
        }

        System.out.printf("Saque realizado! Novo saldo: R$%.2f\n", getSaldo());
        }

    }
}