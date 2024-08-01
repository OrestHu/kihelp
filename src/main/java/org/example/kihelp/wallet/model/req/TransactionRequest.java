package org.example.kihelp.wallet.model.req;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.example.kihelp.wallet.utils.ValidationMessages;

public record TransactionRequest(
        @NotBlank(message = ValidationMessages.TELEGRAM_ID_BLANK)
        @Pattern(regexp = "^[0-9]+$", message = ValidationMessages.TELEGRAM_NOT_VALID_DATA)
        String telegramId,

        @NotBlank(message = ValidationMessages.PAYMENT_ID_BLANK)
        String paymentId,

        @NotBlank(message = ValidationMessages.INITIAL_BLANK)
        String initial,

        @NotNull(message = ValidationMessages.AMOUNT_NULL)
        @DecimalMin(value = "0.01", message = ValidationMessages.AMOUNT_POSITIVE)
        Double amount
) {
}
