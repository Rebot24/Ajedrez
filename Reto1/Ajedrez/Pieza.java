package Clase.Reto1.Ajedrez;

public class Pieza {
    Tipo tipo;
    Color color;

    public Pieza(Tipo tipo, Color color) {
        this.tipo = tipo;
        this.color = color;
    }

    public boolean movimiento(char letrax, char letray) {
        int nuevaposx = letrax - 'a';
        int nuevaposy = letray - 'a';

        int dx = Math.abs(nuevaposx - posx);
        int dy = Math.abs(nuevaposy - posy);

        switch (tipo) {
            case PEON: if (color == Color.BLANCO) return (dy == posy - 1 && dx == 0);
                       else return (dy == posy + 1 && dx == 0);
            case TORRE: return (dx == 0 || dy == 0);
            case ALFIL: return (dx == dy);
            case CABALLO: return ((dy == 2 && dx == 1) || (dy == 1 && dx == 2));
            case REINA: return (dx == 0 || dy == 0 || dy == dx);
            case REY: return (dx <= 1 && dy <= 1);
            default: return false;
        }
    }
}
