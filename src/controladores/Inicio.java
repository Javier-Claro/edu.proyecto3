package controladores;
import javax.swing.*;

public class Inicio {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String[] opciones = {"Fácil", "Intermedio"};
            int seleccion = JOptionPane.showOptionDialog(null, "Selecciona el nivel de dificultad:", "Breakout",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            int velocidad = (seleccion == 1) ? 8 : 4; // Fácil: velocidad 4, Intermedio: velocidad 8
            int ladrillosTotales = (seleccion == 1) ? 50 : 30; // Fácil: 30 ladrillos, Intermedio: 50 ladrillos
            int tiempoLimite = (seleccion == 1) ? 30 : 10; // Fácil: 10 minutos, Intermedio: 30 minutos

            Juego juego = new Juego(velocidad, ladrillosTotales, tiempoLimite);
            juego.iniciar();
        });
    }
}
