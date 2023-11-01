package com.banco.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.domain.transaction.transactions;

public interface TransactionRepository extends JpaRepository<transactions, UUID> {

}
