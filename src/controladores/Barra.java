package controladores;
import java.awt.*;

public class Barra {
    private int x, y, ancho;

    public Barra(int x, int y, int ancho) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, ancho, 10);
    }

    public void moverIzquierda() {
        if (x > 0) x -= 15;
    }

    public void moverDerecha() {
        if (x + ancho < 800) x += 15; // ANCHO = 800 en Juego
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