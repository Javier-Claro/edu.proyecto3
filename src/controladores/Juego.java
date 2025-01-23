package controladores;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase encargada de la funcionalidad del juego
 * 230135 - mjcb
 */
public class Juego extends JPanel implements ActionListener, KeyListener {
    private static final long serialVersionUID = 1L;
	private final int ANCHO = 800;
    private final int ALTO = 600;
    private final Barra barra;
    private final Barra barraSuperior;
    private final Bola bola;
    private final Ladrillo[] ladrillos;
    private final Timer timer;
    private int tiempoRestante;
    private int vidas;
    private int puntos;
    
    /**
     * Método principal que controla la funcionalidad del juego
     * 230135 - mjcb
     */
    public Juego(int velocidad, int ladrillosTotales, boolean nivelIntermedio) {
        setPreferredSize(new Dimension(ANCHO, ALTO));
        setBackground(Color.BLACK);

        barra = new Barra(ANCHO / 2 - 50, ALTO - 30, 100);
        
        // Solo para nivel intermedio
        barraSuperior = nivelIntermedio ? new Barra(ANCHO / 2 - 50, 50, 100) : null;
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
                    
                    // Ajuste para espacio adicional
                    int y = (nivelIntermedio ? 120 : 70) + i * (ladrilloAlto + 5);
                    ladrillos[index++] = new Ladrillo(x, y, ladrilloAncho, ladrilloAlto);
                }
            }
        }
        
        // Fácil: 600 segundos, Intermedio: 1800 segundos
        tiempoRestante = (velocidad == 3) ? 600 : 1800;
        
        // Inicialización de las vidas
        vidas = 3;
        
        // Inicialización de los puntos
        puntos = 0;
        
        // Timer para actualizar el juego a 60 FPS
        timer = new Timer(1000 / 60, this);
        addKeyListener(this);
        setFocusable(true);
        timer.start();

        new Timer(1000, e -> {
            if (tiempoRestante > 0) {
                tiempoRestante--;
            } else {
                ((Timer) e.getSource()).stop();
                mostrarPantallaFinal("Tiempo agotado. ¡Fin del juego!\nPuntos totales: " + puntos);
            }
        }).start();
    }
    
    /**
     * Método que inicia la partida
     * 230135 - mjcb
     */
    public void iniciar() {
        JFrame frame = new JFrame("Breakout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    /**
     * Método que muestra la pantalla final
     * 230135 - mjcb
     */
    private void mostrarPantallaFinal(String mensaje) {
        timer.stop();
        int opcion = JOptionPane.showConfirmDialog(this, mensaje 
        + "\n¿Quieres jugar de nuevo?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            SwingUtilities.invokeLater(() -> {
            	
            	// Reiniciar el juego
                Inicio.main(null);
            });
        } else {
            System.exit(0);
        }
    }
    
    /**
     * Método que le da vida a los objetos de la pantalla de juego
     * 230135 - mjcb
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        barra.dibujar(g);
        
        // Dibujar barra superior si existe
        if (barraSuperior != null) barraSuperior.dibujar(g);
        bola.dibujar(g);

        for (Ladrillo ladrillo : ladrillos) {
            if (ladrillo != null) {
                ladrillo.dibujar(g);
            }
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Tiempo restante: " + tiempoRestante / 60 + ":" + String.format("%02d", tiempoRestante % 60), 10, 20);
        g.drawString("Vidas: " + vidas, ANCHO - 100, 20);
        g.drawString("Puntos: " + puntos, ANCHO / 2 - 50, 20);
    }
    
    /**
     * Método que controla los puntos ganados y la pérdida de las vidas
     * 230135 - mjcb
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        bola.mover(ANCHO, ALTO);

        if (bola.getY() >= ALTO) {
            vidas--;
            
         // Restar 5 puntos al perder una vida
            puntos = Math.max(0, puntos - 5);
            if (vidas == 0) {
                mostrarPantallaFinal("Has Perdido. ¡Fin del juego!\nPuntos totales: " + puntos);
            } else {
                bola.resetPosition(ANCHO / 2, ALTO / 2);
            }
        }
        
        // Sumar 10 puntos por ladrillo destruido
        puntos += bola.verificarColision(barra, ladrillos) * 10;
        if (barraSuperior != null) {
        	
        	// Verificar colisión con la barra superior
            bola.verificarColision(barraSuperior, ladrillos);
        }
        repaint();
    }
    
    /**
     * Método que controla el movimiento de la barra 
     * superior en la dificultad intermedia
     * 230135 - mjcb
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            barra.moverIzquierda();
            if (barraSuperior != null) barraSuperior.moverIzquierda();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            barra.moverDerecha();
            if (barraSuperior != null) barraSuperior.moverDerecha();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}