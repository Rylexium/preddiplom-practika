package com.example.preddiplom.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class TransactionTransfer {
    @Id
    private UUID uuid;
    private String senderBankNumber;
    private String recipientBankNumber;
    private Long senderBankCard;
    private Long recipientBankCard;
    private Long value;
    private Date date;
    private Integer statusTransaction;
}
