package com.ilerna;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Código inicial para la práctica de refactorización.
 * Criterios: Nombres genéricos, números mágicos y métodos con demasiadas
 * responsabilidades.
 */
public class ProcesadorPedidos {

    private static final double TIPO_IVA = 0.21;
    private static final double DESCUENTO = 0.10;
    private static final double GASTOS_ENVIO = 15.95;
    private static final double UMBRAL_DESCUENTO = 100;
    private static final double UMBRAL_GASTOS_ENVIO = 500;

    public static void main(String[] args) {
        ProcesadorPedidos procesador = new ProcesadorPedidos();
        List<String> productos = List.of("Producto 1", "Producto 2", "Producto 3");
        List<Double> precios = List.of(10.0, 20.0, 30.0);
        double total = procesador.procesar(productos, precios);
        Logger logger = LoggerFactory.getLogger(ProcesadorPedidos.class);
        logger.info("Total: {}", total);
    }

    public double procesar(List<String> a, List<Double> b) {
        double t = 0;
        Logger logger = LoggerFactory.getLogger(ProcesadorPedidos.class);
        // Sumar precios de la lista
        for (int i = 0; i < b.size(); i++) {
            if (logger.isInfoEnabled()) {
                logger.info("Añadiendo producto: {}", a.get(i));
            }
            t = t + b.get(i);
        }

        // Lógica de descuento (Magic Number 100 y 0.10)
        if (t > UMBRAL_DESCUENTO) {
            logger.info("Descuento aplicado.");
            t = t - (t * DESCUENTO);
        }

        // Cálculo de impuestos (Magic Number 0.21)
        double res = t + (t * TIPO_IVA);

        // Gastos de envío (Magic Number 500 y 15.95)
        if (res < UMBRAL_GASTOS_ENVIO) {
            res = res + GASTOS_ENVIO;
        }

        return res;
    }
}