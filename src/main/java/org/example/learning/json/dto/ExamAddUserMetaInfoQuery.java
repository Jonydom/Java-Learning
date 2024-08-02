package org.example.learning.json.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamAddUserMetaInfoQuery implements Serializable {
    private String sexAndMarry;
    private String age;
    private List<String> routine;
    private List<String> highOccurDisease;
    private String instPrefer;
}
