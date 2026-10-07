package com.springai.springai_proj_1.dto;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class EmailRequestDTO {
    private String customerName;
    private String customerMsg;

}
