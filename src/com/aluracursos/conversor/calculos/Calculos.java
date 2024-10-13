package com.aluracursos.conversor.calculos;

public class Calculos {
    private double valorDeCambio;
    private double tasa;

    public Calculos(double valor1, Double valor2) {
        this.valorDeCambio = valor1;
        this.tasa = valor2;
    }

    public double getValorDeCambio() {
        return valorDeCambio;
    }

    public double getTasa() {
        return tasa;
    }

    public double cambiar(){
        return valorDeCambio * tasa;
    }
}
