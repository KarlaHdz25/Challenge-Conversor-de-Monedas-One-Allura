package conversor;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {

    private static final String API_KEY = "52c5b1d100e9740526973886";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public double obtenerTasa(String monedaBase, String monedaDestino) throws Exception {
        // Construcción de la URL usando solicitud por pares (tipo 2)
        String url = BASE_URL + API_KEY + "/pair/" + monedaBase + "/" + monedaDestino;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new Exception("No se pudo conectar con la API. Verifica tu conexión a internet.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new Exception("La solicitud fue interrumpida.");
        }

        // Verificar código HTTP
        if (response.statusCode() != 200) {
            throw new Exception("Error en la API. Código HTTP: " + response.statusCode());
        }

        // Parsear JSON con Gson
        JsonElement elemento = JsonParser.parseString(response.body());
        JsonObject objectRoot = elemento.getAsJsonObject();

        // Verificar resultado de la API
        String resultado = objectRoot.get("result").getAsString();
        if (!resultado.equals("success")) {
            String errorType = objectRoot.has("error-type")
                    ? objectRoot.get("error-type").getAsString()
                    : "desconocido";
            throw new Exception("La API reportó un error: " + errorType +
                    ". Verifica que tu API key sea válida.");
        }

        // Obtener la tasa de conversión
        double tasa = objectRoot.get("conversion_rate").getAsDouble();

        return tasa;
    }
}
