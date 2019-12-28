package com.company.socialmedia.model;



import java.time.LocalDate;
import java.util.Objects;

public class Account {

    private int accountId;

    private String accountName;
    private LocalDate createdDate;


    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId == account.accountId &&
                Objects.equals(accountName, account.accountName) &&
                Objects.equals(createdDate, account.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, accountName, createdDate);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountName='" + accountName + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
