package controladores;
import javax.swing.*;

public class Inicio {
	
	/**
	 * MAIN - Método de acceso a la aplicación.
	 * 230125 - mjcb
	 * @param args
	 */
     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String[] opciones = {"Fácil", "Intermedio"};
            int seleccion = JOptionPane.showOptionDialog(null, "Selecciona el nivel de dificultad:", "BreakoutJCB",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
            
            // Fácil: velocidad 3, Intermedio: velocidad 5
            int velocidad = (seleccion == 1) ? 5 : 3;
            
            // Fácil: 30 ladrillos, Intermedio: 50 ladrillos
            int ladrillosTotales = (seleccion == 1) ? 50 : 30;

            Juego juego = new Juego(velocidad, ladrillosTotales, seleccion == 1);
            juego.iniciar();
        });
    }
}