package com.reloadly.transactionservice.dto.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PaginatedResponse<T> implements Serializable {

    private List<T> content;
    private long totalElements;
}