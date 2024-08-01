package org.example.kihelp.wallet.model.req;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.example.kihelp.wallet.utils.ValidationMessages;

public record WalletRequest(
        @NotNull(message = ValidationMessages.AMOUNT_NULL)
        @DecimalMin(value = "0.01", message = ValidationMessages.AMOUNT_POSITIVE)
        Double amount
) {
}
