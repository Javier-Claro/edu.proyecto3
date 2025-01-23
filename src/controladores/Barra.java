package controladores;
import java.awt.*;

/**
 * Clase encargada del control de las palas del juego
 * 230135 - mjcb
 */
public class Barra {
    private int x, y, ancho;
    
    /**
     * Método que declara las medidas de las barra
     * 230135 - mjcb
     */
    public Barra(int x, int y, int ancho) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
    }
    
    /**
     * Método que le da vida a la barra
     * 230135 - mjcb
     */
    public void dibujar(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, ancho, 10);
    }
    
    /**
     * Método que controla el movimiento hacia la izquierda
     * 230135 - mjcb
     */
    public void moverIzquierda() {
        if (x > 0) x -= 20;
    }
    
    /**
     * Método que controla el movimiento hacia la derecha
     * 230135 - mjcb
     */
    public void moverDerecha() {
        if (x + ancho < 800) x += 20; // ANCHO = 800 en Juego
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }
}
