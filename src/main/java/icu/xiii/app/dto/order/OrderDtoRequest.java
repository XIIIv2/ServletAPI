package icu.xiii.app.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OrderDtoRequest(Long id, LocalDate date, double cost, Set<Long> products) {
}
