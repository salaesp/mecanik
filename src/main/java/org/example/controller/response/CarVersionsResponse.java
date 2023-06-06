package org.example.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarVersionsResponse {
    private String brand;
    private String model;
    private Map<Long, String> versions;
}
