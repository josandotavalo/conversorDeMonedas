package com.aluracursos.conversor.principal;

import com.aluracursos.conversor.calculos.Calculos;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);
        int opcion = 0;
        String codigoDeMoneda = "USD";
        String direccion = "https://v6.exchangerate-api.com/v6/f20043f97686693fad850ee5/latest/"+codigoDeMoneda;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String resultJson = response.body();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(resultJson, JsonObject.class);
        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
        Map<String, Double> rates = gson.fromJson(conversionRates, Map.class);
        Double arsRate = rates.get("ARS");
        Double brlRate = rates.get("BRL");
        Double copRate = rates.get("COP");

        while(opcion != 7) {
            System.out.println("**************************************************");
            System.out.println("Sea bienvenido/a al Conversor de Monedas\n");

            System.out.println("1) Dólar =>> Peso argentino\n"+
                    "2) Peso argentino =>> Dólar\n"+
                    "3) Dólar =>> Real brasileño\n"+
                    "4) Real brasileño =>> Dólar\n"+
                    "5) Dólar =>> Peso colombiano\n"+
                    "6) Peso colombiano =>> Dólar\n"+
                    "7) Salir");

            System.out.println("Elija una opción válida: ");
            System.out.println("**************************************************");

            opcion = lectura.nextInt();

            switch (opcion){
                case 1:
                    System.out.println("Ingrese el valor de [USD] a cambiar: ");
                    double valor = Double.valueOf(lectura.next());
                    Calculos conversion = new Calculos(valor, arsRate);
                    System.out.println("El valor de: "+valor+" [USD] cooresponde a: "+conversion.cambiar()+" [ARS]");
                    break;
                case 2:
                    System.out.println("Ingrese el valor de [ARS] a cambiar: ");
                    valor = Double.valueOf(lectura.next());
                    conversion = new Calculos(valor, 1/arsRate);
                    System.out.println("El valor de: "+valor+" [USD] cooresponde a: "+conversion.cambiar()+" [ARS]");
                    break;
                case 3:
                    System.out.println("Ingrese el valor [USD] a cambiar: ");
                    valor = Double.valueOf(lectura.next());
                    conversion = new Calculos(valor, brlRate);
                    System.out.println("El valor de: "+valor+" [USD] cooresponde a: "+conversion.cambiar()+" [BRL]");
                    break;
                case 4:
                    System.out.println("Ingrese el valor de [BRL] a cambiar: ");
                    valor = Double.valueOf(lectura.next());
                    conversion = new Calculos(valor, 1/brlRate);
                    System.out.println("El valor de: "+valor+" [BRL] cooresponde a: "+conversion.cambiar()+" [USD]");
                    break;
                case 5:
                    System.out.println("Ingrese el valor de [USD] a cambiar: ");
                    valor = Double.valueOf(lectura.next());
                    conversion = new Calculos(valor, copRate);
                    System.out.println("El valor de: "+valor+" [USD] cooresponde a: "+conversion.cambiar()+" [COP]");
                    break;
                case 6:
                    System.out.println("Ingrese el valor de [COP] a cambiar: ");
                    valor = Double.valueOf(lectura.next());
                    conversion = new Calculos(valor, 1/copRate);
                    System.out.println("El valor de: "+valor+" [COP] cooresponde a: "+conversion.cambiar()+" [USD]");
                    break;
                case 7:
                    System.out.println("Saliendo del programa");
                    break;
            }


        }

    }
}
