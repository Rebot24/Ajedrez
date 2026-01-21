public class jaque {

    public boolean hayJaque(Pieza[][] tablero, boolean reyBlanco) {
        int reyFila = -1;
        int reyCol = -1;

        // Buscar la posición del rey
        for (int f = 0; f < 8; f++) {
            for (int c = 0; c < 8; c++) {
                if (tablero[f][c] instanceof Rey &&
                        tablero[f][c].esBlanca() == reyBlanco) {

                    reyFila = f;
                    reyCol = c;
                    break;
                }
            }
            if (reyFila != -1) break;
        }

        // Si no se encuentra el rey
        if (reyFila == -1) {
            return false;
        }

        // Comprobar si alguna pieza enemiga puede atacar al rey
        for (int f = 0; f < 8; f++) {
            for (int c = 0; c < 8; c++) {
                if (tablero[f][c] != null &&
                        tablero[f][c].esBlanca() != reyBlanco) {

                    if (tablero[f][c].puedeAtacar(
                            f, c, reyFila, reyCol, tablero)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
