package org.example.kihelp.wallet.model.resp;

public record TransactionResponse(
        Long id,
        String initial,
        Double amount,
        String createdTimeStamp
) {
}
