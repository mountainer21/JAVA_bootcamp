package ex05;

import java.util.UUID;

public class Transaction {
    enum TransferCategory {
        debit, credit
    }

    private UUID identifier;
    private final User recipient;
    private final User sender;
    private final TransferCategory transferCategory;
    private final double transferAmount;

    public Transaction(User recipient, User sender, TransferCategory transferCategory, double transferAmount) {
        if (transferCategory == TransferCategory.debit && transferAmount < 0) {
            System.err.println("IllegalArgument transfer_amount");
            System.exit(-1);
        }
        if (transferCategory == TransferCategory.credit && transferAmount >= 0) {
            System.err.println("IllegalArgument transfer_amount");
            System.exit(-1);
        }
        this.identifier = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.transferCategory = transferCategory;
        this.transferAmount = transferAmount;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public TransferCategory getTransferCategory() {
        return transferCategory;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    @Override
    public String toString() {
        return "\n----------------------------"
                + "\nUUID = " + identifier
                + "\n\nTransfer Category = " + transferCategory
                + "\nTransfer Amount = " + transferAmount
                + "\n\nRecipient\n"
                + recipient
                + "\n\nSender\n"
                + sender
                + "\n----------------------------\n";
    }
}
