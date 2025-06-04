package icu.xiii.app.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductDtoRequest(Long id, String name, double cost) {
}
