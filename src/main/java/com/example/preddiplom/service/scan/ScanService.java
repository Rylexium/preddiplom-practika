package com.example.preddiplom.service.scan;

import com.example.preddiplom.entity.TransactionTransfer;
import com.example.preddiplom.models.statuses.StatusTransaction;
import com.example.preddiplom.repo.TransactionTransferRepository;
import com.example.preddiplom.utils.ConnectToUrl;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScanService {
    private final TransactionTransferRepository transactionTransferRepository;

    // api/finalize/bankNumber_to_bankNumber
    // api/finalize/bankNumber_to_bankCard
    // api/finalize/bankCard_to_bankNumber
    // api/finalize/bankCard_to_bankCard

    public void run() {
        List<TransactionTransfer> transactionTransfers
                = transactionTransferRepository.findAllByStatusTransaction(StatusTransaction.IN_PROGRESS.getCode());
        for(TransactionTransfer transaction : transactionTransfers)
            send(transaction);
    }

    private void send(TransactionTransfer transaction) {
        try {
            if(transaction.getSenderBankNumber() != null && transaction.getRecipientBankNumber() != null) { //bankNumber to bankNumber
                ConnectToUrl.transfer("bankNumber_to_bankNumber",
                        new JSONObject()
                                .put("uuidTransaction", transaction.getUuid())
                                .put("senderBankNumber", transaction.getSenderBankNumber())
                                .put("recipientBankNumber", transaction.getRecipientBankNumber())
                                .put("value", transaction.getValue()));
            }
            else if(transaction.getSenderBankNumber() != null && transaction.getRecipientBankCard() != null) { //bankNumber to bankCard
                ConnectToUrl.transfer("bankNumber_to_bankCard",
                        new JSONObject()
                                .put("uuidTransaction", transaction.getUuid())
                                .put("senderBankNumber", transaction.getSenderBankNumber())
                                .put("recipientBankCard", transaction.getRecipientBankCard())
                                .put("value", transaction.getValue()));
            }
            else if(transaction.getSenderBankCard() != null && transaction.getRecipientBankNumber() != null) { //bankCard to bankNumber
                ConnectToUrl.transfer("bankCard_to_bankNumber",
                        new JSONObject()
                                .put("uuidTransaction", transaction.getUuid())
                                .put("senderBankCard", transaction.getSenderBankCard())
                                .put("recipientBankNumber", transaction.getRecipientBankNumber())
                                .put("value", transaction.getValue()));
            }
            else { // bankCard to bankCard
                ConnectToUrl.transfer("bankCard_to_bankCard",
                        new JSONObject()
                                .put("uuidTransaction", transaction.getUuid())
                                .put("senderBankCard", transaction.getSenderBankCard())
                                .put("bankCard2", transaction.getRecipientBankCard())
                                .put("recipientBankCard", transaction.getValue()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
