# Conversor de Monedas - Challenge Alura

## Estructura del Proyecto

```
ConversorMonedas/
├── src/
│   └── conversor/
│       ├── ConversorMonedas.java   ← Clase principal (menú y flujo)
│       └── ConsultaMoneda.java     ← Lógica de API y JSON
├── lib/
│   └── gson-2.10.1.jar             ← Descarga manual
└── README.md
```

---

## Conversiones disponibles

| Opción | De  | A   |
| ------ | --- | --- |
| 1      | USD | ARS |
| 2      | ARS | USD |
| 3      | USD | BRL |
| 4      | BRL | USD |
| 5      | USD | COP |
| 6      | COP | USD |

---

## Ejemplo de salida

```
╔══════════════════════════════════════╗
║     BIENVENIDO AL CONVERSOR ALURA    ║
╚══════════════════════════════════════╝

┌──────────────────────────────────────┐
│          OPCIONES DE CONVERSIÓN       │
├──────────────────────────────────────┤
│  1) Dólar (USD)    ➜  Peso Arg (ARS) │
│  ...                                  │
└──────────────────────────────────────┘

Elige una opción: 1
Ingresa el monto en USD que deseas convertir: 100

══════════════════════════════════════
  100.00 USD = 98540.00 ARS
  (Tasa de cambio: 1 USD = 985.400000 ARS)
══════════════════════════════════════
```

---

## Tecnologías utilizadas

- **Java 11+** — HttpClient nativo (java.net.http)
- **Gson 2.10.1** — Parseo de JSON
- **ExchangeRate-API** — Tasas de cambio en tiempo real (plan gratuito)
