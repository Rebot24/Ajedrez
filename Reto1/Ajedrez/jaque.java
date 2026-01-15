public class jaque {

    public boolean HayJaqueB(boolean reyBlanco) {
        int reyfila = -1;
        int reycol = -1;

        // esto es basicamente para encontrar donde esta el rey
        for (int f = 0; f < 8; f++) {
            for (int c = 0; c < 8; c++) {
                if (tablero[f][c] instanceof Rey && tablero[f][c].esBlanca() == reyBlanco) {
                    reyfila = f;
                    reycol = c;
                    break;
                }
            }
        }

        // y esto para el jaque, que basicamente se busca que pieza del tablero esta en posicion de atacar al rey
        for (int f = 0; f < 8; f++) {
            for (int c = 0; c < 8; c++) {
                if (tablero[f][c] != null &&
                        tablero[f][c].esBlanca() != reyBlanco) {

                    if (tablero[f][c].puedeAtacar(
                            f, c, reyfila, reycol, tablero)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean HayjaqueN(boolean reyNegro){
        int reyfila = -1;
        int reycol = -1;

        //lo mismo pero en el rey negro
        for (int f = 0; f < 8; f++) {
            for (int c = 0; c < 8; c++) {
                if(tablero[f][c] instanceof Rey && tablero[f][c].esBlanca() == false){
                    reyfila = 1;
                    reycol = 1;
                    break;
                }

            }
        }

        //y otra vez lo mismo
        for (int f = 0; f < 8; f++) {
            for (int c = 0; c < 8; c++) {
                if (tablero[f][c] != null &&
                        tablero[f][c].esBlanca() == true) {

                    if (tablero[f][c].puedeAtacar(
                            f, c, reyfila, reycol, tablero)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
