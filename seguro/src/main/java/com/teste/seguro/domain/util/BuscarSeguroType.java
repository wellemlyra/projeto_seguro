package com.teste.seguro.domain.util;

import com.teste.seguro.domain.enums.SeguroType;

public class BuscarSeguroType {

    /**
     * Calcula o preço do seguro com base no tipo de seguro.
     * @param tipo Tipo de seguro
     * @return Preço do seguro
     */
    public static double calcularPreco(SeguroType tipo) {
        return switch (tipo) {
            case BRONZE -> 100.0;
            case PRATA -> 200.0;
            case OURO -> 300.0;
        };
    }
}
