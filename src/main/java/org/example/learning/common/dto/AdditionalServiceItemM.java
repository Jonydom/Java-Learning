package org.example.learning.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdditionalServiceItemM {

    private String serviceName;

    private List<String> serviceItem;
}
