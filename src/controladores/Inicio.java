package controladores;
import javax.swing.*;

public class Inicio {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String[] opciones = {"Fácil", "Intermedio"};
            int seleccion = JOptionPane.showOptionDialog(null, "Selecciona el nivel de dificultad:", "Breakout",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            int velocidad = (seleccion == 1) ? 6 : 3; // Fácil: velocidad 3, Intermedio: velocidad 6
            int ladrillosTotales = (seleccion == 1) ? 50 : 30; // Fácil: 30 ladrillos, Intermedio: 50 ladrillos

            Juego juego = new Juego(velocidad, ladrillosTotales, seleccion == 1);
            juego.iniciar();
        });
    }
}