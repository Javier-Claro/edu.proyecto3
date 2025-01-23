package controladores;
import java.awt.*;

public class Bola {
    private int x, y, velocidadX, velocidadY;

    public Bola(int x, int y, int velocidad) {
        this.x = x;
        this.y = y;
        this.velocidadX = velocidad;
        this.velocidadY = -velocidad;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, 10, 10);
    }

    public void mover(int ancho, int alto) {
        x += velocidadX;
        y += velocidadY;

        if (x <= 0 || x >= ancho - 10) {
            velocidadX = -velocidadX;
        }
        if (y <= 0) {
            velocidadY = -velocidadY;
        }
    }

    public int verificarColision(Barra barra, Ladrillo[] ladrillos) {
        int puntosGanados = 0;
        if (y + 10 >= barra.getY() && x + 10 >= barra.getX() && x <= barra.getX() + barra.getAncho()) {
            velocidadY = -velocidadY;
        }

        for (int i = 0; i < ladrillos.length; i++) {
            Ladrillo ladrillo = ladrillos[i];
            if (ladrillo != null && x + 10 >= ladrillo.getX() && x <= ladrillo.getX() + ladrillo.getAncho()
                    && y + 10 >= ladrillo.getY() && y <= ladrillo.getY() + ladrillo.getAlto()) {
                ladrillos[i] = null;
                velocidadY = -velocidadY;
                puntosGanados++;
            }
        }
        return puntosGanados;
    }

    public int getY() {
        return y;
    }

    public void resetPosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.velocidadX = Math.abs(velocidadX);
        this.velocidadY = -Math.abs(velocidadY);
    }
}
