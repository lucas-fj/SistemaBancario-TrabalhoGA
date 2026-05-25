//**Essa classe serve para contar a quantidade e o valor total de todas 
// movimentações feitas na conta */

public class Movimentacao {
    
    //atributos
    private int quantidade;
    private double valorTotal;

    //getters e setters
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    //construtor
    public Movimentacao(int quantidade, double valorTotal) {
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

     //método para registrar quantidade de depósitos
     public void registrarMovimentacao(double valor){
        quantidade++;
        valorTotal += valor;
     }
}