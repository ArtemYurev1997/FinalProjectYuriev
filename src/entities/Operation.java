package entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class Operation implements Serializable {
    @Serial
    private static final long serialVersionUID = 8465375599L;

    private Long id;
    private Long clientIdToAccount;
    private String clientToName;
    private String clientToSurname;
    private String clientToAccount;
    private String clientFromName;
    private String clientFromSurname;
    private String clientFromAccount;
    private Long clientIdFromAccount;
    private double sum;
    private String currency;
    private Date localDate;
    private double commission;
    private boolean isCommissionPass;

    public Operation(Long id, Long clientIdToAccount, String clientToName, String clientToSurname, String clientToAccount, String clientFromName, String clientFromSurname, String clientFromAccount, Long clientIdFromAccount, double sum, String currency, Date localDate, double commission, boolean isCommissionPass) {
        this.id = id;
        this.clientIdToAccount = clientIdToAccount;
        this.clientToName = clientToName;
        this.clientToSurname = clientToSurname;
        this.clientToAccount = clientToAccount;
        this.clientFromName = clientFromName;
        this.clientFromSurname = clientFromSurname;
        this.clientFromAccount = clientFromAccount;
        this.clientIdFromAccount = clientIdFromAccount;
        this.sum = sum;
        this.currency = currency;
        this.localDate = localDate;
        this.commission = commission;
        this.isCommissionPass = isCommissionPass;
    }
    public Operation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientIdToAccount() {
        return clientIdToAccount;
    }

    public void setClientIdToAccount(Long clientIdToAccount) {
        this.clientIdToAccount = clientIdToAccount;
    }

    public String getClientToName() {
        return clientToName;
    }

    public void setClientToName(String clientToName) {
        this.clientToName = clientToName;
    }

    public String getClientToSurname() {
        return clientToSurname;
    }

    public void setClientToSurname(String clientToSurname) {
        this.clientToSurname = clientToSurname;
    }

    public String getClientToAccount() {
        return clientToAccount;
    }

    public void setClientToAccount(String clientToAccount) {
        this.clientToAccount = clientToAccount;
    }


    public String getClientFromName() {
        return clientFromName;
    }

    public void setClientFromName(String clientFromName) {
        this.clientFromName = clientFromName;
    }

    public String getClientFromSurname() {
        return clientFromSurname;
    }

    public void setClientFromSurname(String clientFromSurname) {
        this.clientFromSurname = clientFromSurname;
    }

    public String getClientFromAccount() {
        return clientFromAccount;
    }

    public void setClientFromAccount(String clientFromAccount) {
        this.clientFromAccount = clientFromAccount;
    }

    public Long getClientIdFromAccount() {
        return clientIdFromAccount;
    }

    public void setClientIdFromAccount(Long clientIdFromAccount) {
        this.clientIdFromAccount = clientIdFromAccount;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getLocalDate() {
        return localDate;
    }

    public void setLocalDate(Date localDate) {
        this.localDate = localDate;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public boolean isCommissionPass() {
        return isCommissionPass;
    }

    public void setCommissionPass(boolean commissionPass) {
        isCommissionPass = commissionPass;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", clientIdToAccount=" + clientIdToAccount +
                ", clientToName='" + clientToName + '\'' +
                ", clientToSurname='" + clientToSurname + '\'' +
                ", clientToAccount='" + clientToAccount + '\'' +
                ", clientFromName='" + clientFromName + '\'' +
                ", clientFromSurname='" + clientFromSurname + '\'' +
                ", clientFromAccount='" + clientFromAccount + '\'' +
                ", clientIdFromAccount=" + clientIdFromAccount +
                ", sum=" + sum +
                ", currency='" + currency + '\'' +
                ", localDate=" + localDate +
                ", commission=" + commission +
                ", isCommissionPass=" + isCommissionPass +
                '}';
    }
}
