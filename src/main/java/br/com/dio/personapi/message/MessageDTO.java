package br.com.dio.personapi.message;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDTO {
    private String message;
}
