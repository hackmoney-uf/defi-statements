package org.example.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.emptyList;

public class Transaction {

    public final String hash;
    public final String from;
    public final String to;
    public final String value;
    public final BigDecimal valueQuote;
    public final String feesPaid;
    public final ZonedDateTime signedAt;
    public final List<Erc20Transaction> erc20Transactions;

    private Transaction(Builder builder) {
        hash = builder.hash;
        from = builder.from;
        to = builder.to;
        value = builder.value;
        valueQuote = builder.valueQuote;
        feesPaid = builder.feesPaid;
        signedAt = builder.signedAt;
        erc20Transactions = builder.erc20Transactions;
    }

    @Override
    public String toString() {
        return "Transaction{" +
            "hash='" + hash + '\'' +
            ", from='" + from + '\'' +
            ", to='" + to + '\'' +
            ", value='" + value + '\'' +
            ", valueQuote=" + valueQuote +
            ", feesPaid='" + feesPaid + '\'' +
            ", signedAt='" + signedAt + '\'' +
            ", erc20Transactions='" + erc20Transactions + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(hash, that.hash) && Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(value, that.value) && Objects.equals(valueQuote, that.valueQuote) && Objects.equals(feesPaid, that.feesPaid) && Objects.equals(signedAt, that.signedAt) && Objects.equals(erc20Transactions, that.erc20Transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash, from, to, value, valueQuote, feesPaid, signedAt, erc20Transactions);
    }

    public record Erc20Transaction(String from, String to, String value, String symbol, String contract) {

    }

    public static final class Builder {
        private String hash;
        private String from;
        private String to;
        private String value;
        private BigDecimal valueQuote;
        private String feesPaid;
        private ZonedDateTime signedAt;
        private List<Erc20Transaction> erc20Transactions = emptyList();

        public static Builder transaction() {
            return new Builder();
        }

        private Builder() {
        }

        public Builder hash(String hash) {
            this.hash = hash;
            return this;
        }

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder to(String to) {
            this.to = to;
            return this;
        }

        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public Builder valueQuote(BigDecimal valueQuote) {
            this.valueQuote = valueQuote;
            return this;
        }

        public Builder feesPaid(String feesPaid) {
            this.feesPaid = feesPaid;
            return this;
        }

        public Builder signedAt(ZonedDateTime signedAt) {
            this.signedAt = signedAt;
            return this;
        }

        public Builder erc20Transactions(List<Erc20Transaction> erc20Transactions) {
            this.erc20Transactions = erc20Transactions;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }
}
