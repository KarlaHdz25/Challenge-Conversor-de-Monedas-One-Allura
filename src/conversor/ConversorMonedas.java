package conversor;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConversorMonedas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║     BIENVENIDO AL CONVERSOR ALURA    ║");
        System.out.println("╚══════════════════════════════════════╝");

        while (continuar) {
            mostrarMenu();

            try {
                System.out.print("\nElige una opción: ");
                int opcion = scanner.nextInt();

                if (opcion == 7) {
                    System.out.println("\n¡Hasta luego! Gracias por usar el Conversor Alura.");
                    break;
                }

                String[] pares = obtenerPar(opcion);
                if (pares == null) {
                    System.out.println("\n⚠ Opción inválida. Por favor elige entre 1 y 7.");
                    continue;
                }

                System.out.printf("\nIngresa el monto en %s que deseas convertir: ", pares[0]);
                double monto = scanner.nextDouble();

                if (monto <= 0) {
                    System.out.println("⚠ El monto debe ser mayor a cero.");
                    continue;
                }

                ConsultaMoneda consulta = new ConsultaMoneda();
                double tasa = consulta.obtenerTasa(pares[0], pares[1]);
                double resultado = monto * tasa;

                System.out.println("\n══════════════════════════════════════");
                System.out.printf("  %.2f %s = %.2f %s%n", monto, pares[0], resultado, pares[1]);
                System.out.printf("  (Tasa de cambio: 1 %s = %.6f %s)%n", pares[0], tasa, pares[1]);
                System.out.println("══════════════════════════════════════");

            } catch (InputMismatchException e) {
                System.out.println("\n⚠ Error: ingresa solo números válidos.");
                scanner.nextLine(); // limpiar buffer
            } catch (Exception e) {
                System.out.println("\n⚠ Error: " + e.getMessage());
            }

            System.out.println("\n¿Deseas realizar otra conversión? (s/n): ");
            scanner.nextLine(); // consumir salto de línea
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if (!respuesta.equals("s")) {
                continuar = false;
                System.out.println("\n¡Hasta luego! Gracias por usar el Conversor Alura.");
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n┌──────────────────────────────────────┐");
        System.out.println("│          OPCIONES DE CONVERSIÓN       │");
        System.out.println("├──────────────────────────────────────┤");
        System.out.println("│  1) Dólar (USD)    ➜  Peso Arg (ARS) │");
        System.out.println("│  2) Peso Arg (ARS) ➜  Dólar (USD)    │");
        System.out.println("│  3) Dólar (USD)    ➜  Real Bra (BRL) │");
        System.out.println("│  4) Real Bra (BRL) ➜  Dólar (USD)    │");
        System.out.println("│  5) Dólar (USD)    ➜  Peso Col (COP) │");
        System.out.println("│  6) Peso Col (COP) ➜  Dólar (USD)    │");
        System.out.println("│  7) Salir                             │");
        System.out.println("└──────────────────────────────────────┘");
    }

    private static String[] obtenerPar(int opcion) {
        return switch (opcion) {
            case 1 -> new String[]{"USD", "ARS"};
            case 2 -> new String[]{"ARS", "USD"};
            case 3 -> new String[]{"USD", "BRL"};
            case 4 -> new String[]{"BRL", "USD"};
            case 5 -> new String[]{"USD", "COP"};
            case 6 -> new String[]{"COP", "USD"};
            default -> null;
        };
    }
}
