//**Classe conta bancária contém os atributos principais da conta
// e o método movimenta() para movimentações da conta*/

public  abstract class ContaBancaria {
    
    //atributos 
    private Cliente cliente;
    private double saldoInicial;
    private double saldo;
    private Movimentacao depositos;
    private Movimentacao saques;
    private Movimentacao juros;
    private double saldoMin;
    private double saldoMax;

    //getters e setters
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public double getSaldoInicial() {
        return saldoInicial;
    }
    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }
    public Movimentacao getDepositos() {
        return depositos;
    }
    public void setDepositos(Movimentacao depositos) {
        this.depositos = depositos;
    }
    public Movimentacao getSaques() {
        return saques;
    }
    public void setSaques(Movimentacao saques) {
        this.saques = saques;
    }
    public Movimentacao getJuros() {
        return juros;
    }
    public void setJuros(Movimentacao juros) {
        this.juros = juros;
    }
    public double getSaldoMin() {
        return saldoMin;
    }
    public void setSaldoMin(double saldoMin) {
        this.saldoMin = saldoMin;
    }
    public double getSaldoMax() {
        return saldoMax;
    }
    public void setSaldoMax(double saldoMax) {
        this.saldoMax = saldoMax;
    }
    
    //construtor 
    public ContaBancaria(Cliente cliente, double saldoInicial) {
        this.cliente = cliente;
        this.saldoInicial = saldoInicial;
        this.saldo = saldoInicial;
        this.saldoMin = saldoInicial;
        this.saldoMax = saldoInicial;

        this.depositos = new Movimentacao(0, 0);
        this.saques = new Movimentacao(0, 0);
        this.juros = new Movimentacao(0, 0);
    }
    //métodos
    public abstract void movimenta(Operacao op);  
   
    //método para mostrar extrato
    public void extrato(){
        //dados cliente e conta
        System.out.println("\n--- EXTRATO ---");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Nascimento: " + cliente.getDtNascimento());
        System.out.println("Saldo inicial: R$" + saldoInicial);
        System.out.println("Saldo atual: R$" + saldo);

        //movimentações
        System.out.printf("Depósitos: " + depositos.getQuantidade() + " || " + "Total: R$%.2f\n", depositos.getValorTotal());
        System.out.printf("Saques: " + saques.getQuantidade() + " || " + "Total: R$%.2f\n", saques.getValorTotal());
        System.out.printf("Juros: " + juros.getQuantidade() + " || " + "Total: R$%.2f\n", juros.getValorTotal());
        System.out.printf("Saldo minimo: R$%.2f\n", saldoMin);
        System.out.printf("Saldo máximo: R$%.2f\n", saldoMax);
    }
}