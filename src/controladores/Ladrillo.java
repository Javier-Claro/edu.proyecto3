package controladores;
import java.awt.*;

/**
 * Clase encargada de la aparición de los ladrillos y sus medidas y espacio
 * 230135 - mjcb
 */
public class Ladrillo {
    private int x, y, ancho, alto;
    
    /**
     * Método que declara los valores del ladrillo
     * 230135 - mjcb
     */
    public Ladrillo(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    /**
     * Método que le da via a los ladrillos
     * 230135 - mjcb
     */
    public void dibujar(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, ancho, alto);
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

    public int getAlto() {
        return alto;
    }
}
