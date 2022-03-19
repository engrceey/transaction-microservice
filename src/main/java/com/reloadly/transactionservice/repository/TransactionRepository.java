package com.reloadly.transactionservice.repository;

import com.reloadly.transactionservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

//    @Query("SELECT u FROM TRANSACTIONS u WHERE u.senderAccountNumber = ?1 and u.receiverAccountNumber = ?1")
//    Collection<Transaction> findTransactionsByAccountNumber(long accountNum);
}
