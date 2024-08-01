package org.example.kihelp.wallet.model.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record TransactionFindRequest(
        @Max(1) int page,
        @Min(0) @Max(5) int limit
) {
}
