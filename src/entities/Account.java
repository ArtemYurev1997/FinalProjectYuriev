package entities;


import java.io.Serial;
import java.io.Serializable;

    public class Account implements Serializable {
        @Serial
        private static final long serialVersionUID = 455346754L;
        static long nextId = 1L;

        private Long id;
        private String accountNumber;
        private String currency;
        private Double value;
        private Long userId;


        public Account( String accountNumber, String currency, Double value) {
            id = Long.valueOf(0);
            this.accountNumber = accountNumber;
            this.currency = currency;
            this.value = value;
        }
        public Account() {

        }
        public Account(Long userId, String accountNumber, String currency, Double value) {
            this.userId = userId;
            this.accountNumber = accountNumber;
            this.currency = currency;
            this.value = value;
        }

        public Long getId() {
            return id;
        }

        public void setId() {
            id = nextId;
            nextId++;
        }
        public void setId(Long id) {
            this.id = id;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "Account{" +
                    "id=" + id +
                    ", accountNumber='" + accountNumber + '\'' +
                    ", currency='" + currency + '\'' +
                    ", value=" + value +
                    ", userId=" + userId +
                    '}';
        }
}
