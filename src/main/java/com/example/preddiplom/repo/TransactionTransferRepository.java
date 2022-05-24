package com.example.preddiplom.repo;

import com.example.preddiplom.entity.TransactionTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionTransferRepository extends JpaRepository<TransactionTransfer, UUID> {
    List<TransactionTransfer> findAllByStatusTransaction(int code);
}
