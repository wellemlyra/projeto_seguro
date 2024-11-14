package com.teste.seguro.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CadastroClient {

    private final String apiUrl;
    private final RestTemplate restTemplate;

    public CadastroClient(@Value("${cadastro.api.url}") String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
    }

    public boolean verificarCliente(String cpf) {
        String url = String.format("%s/%s", apiUrl, cpf);
        try {
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}