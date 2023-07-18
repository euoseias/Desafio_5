package quintoDesafio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransferDataAPI {
    private List<Transfer> transfers;

    public TransferDataAPI() {
        transfers = new ArrayList<>();
        // Exemplo de dados de transferência
        transfers.add(new Transfer(1, "João", "Maria", 1000.00, new Date()));
        transfers.add(new Transfer(2, "Maria", "Pedro", 500.50, new Date()));
        transfers.add(new Transfer(3, "Ana", "João", 750.25, new Date()));
        transfers.add(new Transfer(4, "Pedro", "Maria", 300.75, new Date()));
    }

    public List<Transfer> getTransfers(String accountNumber, Date startDate, Date endDate, String operatorName) {
        List<Transfer> filteredTransfers = new ArrayList<>();

        for (Transfer transfer : transfers) {
            if (accountNumber != null && !accountNumber.isEmpty() && !transfer.getAccountNumber().equals(accountNumber)) {
                continue;
            }

            if (startDate != null && transfer.getDate().before(startDate)) {
                continue;
            }

            if (endDate != null && transfer.getDate().after(endDate)) {
                continue;
            }

            if (operatorName != null && !operatorName.isEmpty() &&
                    !(transfer.getOriginOperator().equals(operatorName) || transfer.getDestinationOperator().equals(operatorName))) {
                continue;
            }

            filteredTransfers.add(transfer);
        }

        return filteredTransfers;
    }

    public static void main(String[] args) {
        TransferDataAPI transferDataAPI = new TransferDataAPI();

        // Exemplo de uso da API
        List<Transfer> allTransfers = transferDataAPI.getTransfers(null, null, null, null);
        System.out.println("Todas as transferências:");
        for (Transfer transfer : allTransfers) {
            System.out.println(transfer);
        }

        System.out.println();

        Date startDate = new Date(); // Data de início
        Date endDate = new Date(); // Data de fim
        String operatorName = "Maria"; // Nome do operador

        List<Transfer> filteredTransfers = transferDataAPI.getTransfers(null, startDate, endDate, operatorName);
        System.out.println("Transferências filtradas por período e operador:");
        for (Transfer transfer : filteredTransfers) {
            System.out.println(transfer);
        }
    }
}

class Transfer {
    private int id;
    private String originOperator;
    private String destinationOperator;
    private double amount;
    private Date date;

    public Transfer(int id, String originOperator, String destinationOperator, double amount, Date date) {
        this.id = id;
        this.originOperator = originOperator;
        this.destinationOperator = destinationOperator;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getOriginOperator() {
        return originOperator;
    }

    public String getDestinationOperator() {
        return destinationOperator;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public String getAccountNumber() {
        // Número da conta bancária pode ser derivado do operador de destino ou origem
        // Neste exemplo, usaremos o operador de destino como número da conta bancária
        return destinationOperator;
    }

    @Override
    public String toString() {
        return "Transfer [id=" + id +
                ", originOperator=" + originOperator +
                ", destinationOperator=" + destinationOperator +
                ", amount=" + String.format("%.2f", amount) +
                ", date=" + date + "]";
    }
}
