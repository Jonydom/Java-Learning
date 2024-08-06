package org.example.learning.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {
    private Long spuId;
    private String title;
    private Integer city;
    private BigDecimal salePrice;
    private List<AdditionalServiceItemM> serviceItemList;
    private String recommendReason;
}
