package com.example.productservice.dto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor


public class APIResponse
{
    int statusCode;
    Object data;
    String StatusMessage;
    Boolean success;
}
