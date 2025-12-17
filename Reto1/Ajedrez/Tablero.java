package Clase.Reto1.Ajedrez;

public class Tablero {
    String [][] tablero;
    Estado estado;

    Tablero() {
        tablero = new String[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tablero[i][j] = "";
                estado = Estado.LIBRE;
            }
        }
    }


    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void mostrarTablero() {
        String blanco = "\u001B[47m";
        String negro = "\u001B[40m";
        String reset = "\u001B[0m";

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    tablero[i][j] = blanco + "   " + reset;
                } else {
                    tablero[i][j] = negro + "   " + reset;
                }
                System.out.print(tablero[i][j]);
            }
            System.out.println();
        }
    }
}
