package com.aluracursos.modelos;

public class Moneda {
    private final String codigoDeLaMoneda;
    private double monto;
    private double cambioBoliviano;
    private double cambioDolarEstadounidense;
    private double cambioPesoArgentino;
    private double cambioPesoChileno;
    private double cambioPesoColombiano;
    private double cambioRealBrasileno;
    private double cambioNuevoSolPeruano;
    private double cambioEuro;
    private double cambioYenJapones;
    private double cambioPesoMexicano;

    public Moneda(String codigoDeLaMoneda) {
        this.codigoDeLaMoneda = codigoDeLaMoneda;
    }

    public String getCodigoDeLaMoneda() {
        return codigoDeLaMoneda;
    }

    public void setMontoYModelo(double monto, ModeloDeMoneda modelo){
                this.monto = monto;
                this.cambioBoliviano = modelo.conversion_rates().get("BOB");
                this.cambioDolarEstadounidense = modelo.conversion_rates().get("USD");
                this.cambioPesoArgentino = modelo.conversion_rates().get("ARS");
                this.cambioPesoChileno = modelo.conversion_rates().get("CLP");
                this.cambioPesoColombiano = modelo.conversion_rates().get("COP");
                this.cambioRealBrasileno = modelo.conversion_rates().get("BRL");
                this.cambioNuevoSolPeruano = modelo.conversion_rates().get("PEN");
                this.cambioEuro = modelo.conversion_rates().get("EUR");
                this.cambioYenJapones = modelo.conversion_rates().get("JPY");
                this.cambioPesoMexicano = modelo.conversion_rates().get("MXN");
    }


    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getCambioBoliviano() {
        return cambioBoliviano;
    }

    public double getCambioDolarEstadounidense() {
        return cambioDolarEstadounidense;
    }

    public double getCambioPesoArgentino() {
        return cambioPesoArgentino;
    }

    public double getCambioPesoChileno() {
        return cambioPesoChileno;
    }

    public double getCambioPesoColombiano() {
        return cambioPesoColombiano;
    }

    public double getCambioRealBrasileno() {
        return cambioRealBrasileno;
    }

    public double getCambioNuevoSolPeruano() {
        return cambioNuevoSolPeruano;
    }

    public double getCambioEuro() {
        return cambioEuro;
    }

    public double getCambioYenJapones() {
        return cambioYenJapones;
    }

    public double getCambioPesoMexicano() {
        return cambioPesoMexicano;
    }

    public void cambiarMoneda(Moneda cambioDeMoneda){
        switch (cambioDeMoneda.getCodigoDeLaMoneda()){
            case "USD": cambioDeMoneda.setMonto(this.getMonto()*this.getCambioDolarEstadounidense());
                        break;
            case "BOB": cambioDeMoneda.setMonto(this.getMonto()*this.getCambioBoliviano());
                        break;
            case "ARS": cambioDeMoneda.setMonto(this.getMonto()*this.getCambioPesoArgentino());
                        break;
            case "CLP": cambioDeMoneda.setMonto(this.getMonto()*this.getCambioPesoChileno());
                        break;
            case "COP": cambioDeMoneda.setMonto(this.getMonto()*this.getCambioPesoColombiano());
                        break;
            case "BRL": cambioDeMoneda.setMonto(this.getMonto()*this.getCambioRealBrasileno());
                        break;
            case "PEN": cambioDeMoneda.setMonto(this.getMonto()*this.getCambioNuevoSolPeruano());
                        break;
            case "EUR": cambioDeMoneda.setMonto(this.getMonto()*this.getCambioEuro());
                        break;
            case "JPY": cambioDeMoneda.setMonto(this.getMonto()*this.getCambioYenJapones());
                        break;
            case "MXN": cambioDeMoneda.setMonto(this.getMonto()*this.getCambioPesoMexicano());
                        break;
        }
    }

}
