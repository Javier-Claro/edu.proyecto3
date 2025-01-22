package controladores;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Juego extends JPanel implements ActionListener, KeyListener {
    private final int ANCHO = 800;
    private final int ALTO = 600;
    private final Barra barra;
    private final Bola bola;
    private final Ladrillo[] ladrillos;
    private Timer timer = null;
    private Timer temporizador = null;
    private int tiempoRestante;

    public Juego(int velocidad, int ladrillosTotales, int tiempoLimite) {
        setPreferredSize(new Dimension(ANCHO, ALTO));
        setBackground(Color.BLACK);

        barra = new Barra(ANCHO / 2 - 50, ALTO - 30, 100);
        bola = new Bola(ANCHO / 2, ALTO / 2, velocidad);

        ladrillos = new Ladrillo[ladrillosTotales];
        int columnas = 10;
        int filas = (int) Math.ceil((double) ladrillosTotales / columnas);
        int ladrilloAncho = (ANCHO - (columnas - 1) * 5) / columnas;
        int ladrilloAlto = 20;

        int index = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (index < ladrillosTotales) {
                    int x = j * (ladrilloAncho + 5);
                    int y = i * (ladrilloAlto + 5);
                    ladrillos[index++] = new Ladrillo(x, y, ladrilloAncho, ladrilloAlto);
                }
            }
        }

        tiempoRestante = tiempoLimite * 60; // Convertir minutos a segundos
        temporizador = new Timer(1000, e -> {
            tiempoRestante--;
            if (tiempoRestante <= 0) {
                temporizador.stop();
                timer.stop();
                JOptionPane.showMessageDialog(this, "¡Tiempo agotado!", "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
            repaint();
        });
        temporizador.start();

        timer = new Timer(16, this);
        addKeyListener(this);
        setFocusable(true);
        timer.start();
    }

    public void iniciar() {
        JFrame frame = new JFrame("Breakout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        barra.dibujar(g);
        bola.dibujar(g);

        for (Ladrillo ladrillo : ladrillos) {
            if (ladrillo != null) {
                ladrillo.dibujar(g);
            }
        }

        // Dibujar el temporizador
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Tiempo restante: " + tiempoRestante / 60 + ":" + String.format("%02d", tiempoRestante % 60), 10, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bola.mover(ANCHO, ALTO);
        bola.verificarColision(barra, ladrillos);
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) barra.moverIzquierda();
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) barra.moverDerecha();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}