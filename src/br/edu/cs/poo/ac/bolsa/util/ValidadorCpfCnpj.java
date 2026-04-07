package br.edu.cs.poo.ac.bolsa.util;

public class ValidadorCpfCnpj {

    public static ResultadoValidacao validarCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return ResultadoValidacao.NAO_INFORMADO;
        }

        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            return ResultadoValidacao.FORMATO_INVALIDO;
        }

        if (cpf.chars().distinct().count() == 1) {
            return ResultadoValidacao.FORMATO_INVALIDO;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int resto = soma % 11;
        int dv1 = (resto < 2) ? 0 : 11 - resto;

        if (dv1 != (cpf.charAt(9) - '0')) {
            return ResultadoValidacao.DV_INVALIDO;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        resto = soma % 11;
        int dv2 = (resto < 2) ? 0 : 11 - resto;

        if (dv2 != (cpf.charAt(10) - '0')) {
            return ResultadoValidacao.DV_INVALIDO;
        }

        return null;
    }

    public static ResultadoValidacao validarCnpj(String cnpj) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            return ResultadoValidacao.NAO_INFORMADO;
        }

        cnpj = cnpj.replaceAll("[^0-9]", "");

        if (cnpj.length() != 14) {
            return ResultadoValidacao.FORMATO_INVALIDO;
        }

        if (cnpj.chars().distinct().count() == 1) {
            return ResultadoValidacao.FORMATO_INVALIDO;
        }

        int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma = 0;
        for (int i = 0; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * pesos1[i];
        }
        int resto = soma % 11;
        int dv1 = (resto < 2) ? 0 : 11 - resto;

        if (dv1 != (cnpj.charAt(12) - '0')) {
            return ResultadoValidacao.DV_INVALIDO;
        }

        int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += (cnpj.charAt(i) - '0') * pesos2[i];
        }
        resto = soma % 11;
        int dv2 = (resto < 2) ? 0 : 11 - resto;

        if (dv2 != (cnpj.charAt(13) - '0')) {
            return ResultadoValidacao.DV_INVALIDO;
        }

        return null;
    }
}