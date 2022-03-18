package com.reloadly.transactionservice.entity;

import com.reloadly.transactionservice.constants.TransactionStatus;
import com.reloadly.transactionservice.constants.TransactionType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "transactions")
public class Transaction extends BaseEntity {

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "transaction_id", nullable = false )
    private String transactionId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "sender")
    private String sender;

    @Column(name = "sender_account_number")
    private long senderAccountNumber;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "receiver_account_number")
    private long receiverAccountNumber;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "transaction_status")
    @Builder.Default
    private TransactionStatus transactionStatus = TransactionStatus.PENDING;


}
