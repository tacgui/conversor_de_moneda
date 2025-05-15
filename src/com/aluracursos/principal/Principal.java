package com.aluracursos.principal;

import com.aluracursos.modelos.*;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Scanner;


public class Principal {
    Gson gson = FormatoParaGson.getGson();

    public String sacarTipoDeCambio(String codigoDeLaMoneda){
        HttpClient client= HttpClient.newHttpClient();
        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/d845ee6d61065ee240fb97ec/latest/"+codigoDeLaMoneda))
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request,HttpResponse.BodyHandlers.ofString());
                    return response.body();
        } catch (IOException | InterruptedException e ) {
                    return "Problema en la solicitud web";
        }
    }

    public void realizarCambio(Moneda monedaOrigen, double montoDinero, Moneda monedaCambiada){
        String json = sacarTipoDeCambio(monedaOrigen.getCodigoDeLaMoneda());
        ModeloDeMoneda miModelo = gson.fromJson(json,ModeloDeMoneda.class);
        monedaOrigen.setMontoYModelo(montoDinero, miModelo);
        monedaOrigen.cambiarMoneda(monedaCambiada);
    }

    public String mensajeDeCambioDeMoneda(Moneda monedaOrigen, Moneda monedaCambiada){
        return "El valor es:"+monedaOrigen.getMonto() + " ["+
        monedaOrigen.getCodigoDeLaMoneda()+"] corresponde al valor final de =>>>>>> "+
        monedaCambiada.getMonto()+" ["+ monedaCambiada.getCodigoDeLaMoneda()+"]";
    }

    public void guardarRegistro(String mensaje){
        LocalDateTime fechaHora = LocalDateTime.now();
        try {
        FileWriter escritura= new FileWriter("MonedasIntercambiadas.txt",true);
        String resultado = mensaje + "["+fechaHora +"]\n";
        escritura.write(resultado);
        escritura.close();
        } catch (IOException e) {
            System.out.println("Problemas con crear el archivo");
        }
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Principal principal= new Principal();
        double montoAConvertir=-1;
        String mensaje;
        Moneda monedaDolarEstadounidense = new Moneda("USD");
        Moneda monedaBolivianoBoliviano = new Moneda("BOB");
        Moneda monedaPesoArgentino = new Moneda("ARS");
        Moneda monedaPesoChileno = new Moneda("CLP");
        Moneda monedaPesoColombiano = new Moneda("COP");
        Moneda monedaRealBrasileno = new Moneda("BRL");
        Moneda monedaNuevoSolPeruano = new Moneda("PEN");
        Moneda monedaEuro = new Moneda("EUR");
        Moneda monedaYenJapones = new Moneda("JPY");
        Moneda monedaPesoMexicano = new Moneda("MXN");

        int opcion;
        while (true) {
            System.out.println("""
                    **************************************************
                    Sea bienvenido/a al Conversor de Moneda =]
                    
                    1)Dólar =>> Peso Argentino
                    2)Peso Argentino =>> Dólar
                    3)Dólar =>> Real Brasileño
                    4)Real brasileño =>> Dólar
                    5)Dólar =>> Peso colombiano
                    6)Peso colombiano =>> Dólar
                    7)Boliviano boliviano =>> Peso Chileno
                    8)Nuevo Sol Peruano =>> Euro
                    9)Yen Japones =>> Peso Mexicano
                    10)Peso Chileno =>> Nuevo Sol Peruano
                    11)Salir
                    Elija una opción válida
                    
                    **************************************************""");
            System.out.print("Opcion:");
            String entrada=teclado.nextLine();
            try {
                opcion = Integer.parseInt(entrada);

                if (opcion >= 1 && opcion <= 11) {
                    System.out.println("Ingrese el valor que deseas converter:");
                    String montoOriginal = teclado.nextLine();
                    montoAConvertir = Double.parseDouble(montoOriginal);
                }

                switch (opcion) {
                    case 1:
                        principal.realizarCambio(monedaDolarEstadounidense, montoAConvertir, monedaPesoArgentino);
                        mensaje = principal.mensajeDeCambioDeMoneda(monedaDolarEstadounidense, monedaPesoArgentino);
                        break;
                    case 2:
                        principal.realizarCambio(monedaPesoArgentino, montoAConvertir, monedaDolarEstadounidense);
                        mensaje = principal.mensajeDeCambioDeMoneda(monedaPesoArgentino, monedaDolarEstadounidense);
                        break;
                    case 3:
                        principal.realizarCambio(monedaDolarEstadounidense, montoAConvertir, monedaRealBrasileno);
                        mensaje = principal.mensajeDeCambioDeMoneda(monedaDolarEstadounidense, monedaRealBrasileno);
                        break;
                    case 4:
                        principal.realizarCambio(monedaRealBrasileno, montoAConvertir, monedaDolarEstadounidense);
                        mensaje = principal.mensajeDeCambioDeMoneda(monedaRealBrasileno, monedaDolarEstadounidense);
                        break;
                    case 5:
                        principal.realizarCambio(monedaDolarEstadounidense, montoAConvertir, monedaPesoColombiano);
                        mensaje = principal.mensajeDeCambioDeMoneda(monedaDolarEstadounidense, monedaPesoColombiano);
                        break;
                    case 6:
                        principal.realizarCambio(monedaPesoColombiano, montoAConvertir, monedaDolarEstadounidense);
                        mensaje = principal.mensajeDeCambioDeMoneda(monedaPesoColombiano, monedaDolarEstadounidense);
                        break;
                    case 7:
                        principal.realizarCambio(monedaBolivianoBoliviano, montoAConvertir, monedaPesoChileno);
                        mensaje = principal.mensajeDeCambioDeMoneda(monedaBolivianoBoliviano, monedaPesoChileno);
                        break;
                    case 8:
                        principal.realizarCambio(monedaNuevoSolPeruano, montoAConvertir, monedaEuro);
                        mensaje = principal.mensajeDeCambioDeMoneda(monedaNuevoSolPeruano, monedaEuro);
                        break;
                    case 9:
                        principal.realizarCambio(monedaYenJapones, montoAConvertir, monedaPesoMexicano);
                        mensaje = principal.mensajeDeCambioDeMoneda(monedaYenJapones, monedaPesoMexicano);
                        break;
                    case 10:
                        principal.realizarCambio(monedaPesoChileno, montoAConvertir, monedaNuevoSolPeruano);
                        mensaje = principal.mensajeDeCambioDeMoneda(monedaPesoChileno, monedaNuevoSolPeruano);
                        break;
                    case 11: return;
                    default:
                        System.out.println("Ingrese una opción válida.");
                        continue;
                }
                System.out.println(mensaje);
                principal.guardarRegistro(mensaje);
            }catch (NumberFormatException e){
                System.out.println("Digite correctamente. Regresando al menu principal.");
            }
        }
        }
    }

