package Clase.Reto1.Ajedrez;

public class Pieza {
    private Tipo tipo;
    private Color color;
    private int posx;
    private int posy;

    public Pieza(char letra, char letrax, char letray, String color) {
        switch (letra) {
            case 'P': tipo = Tipo.PEON; break;
            case 'A': tipo = Tipo.ALFIL; break;
            case 'R': tipo = Tipo.REY; break;
            case 'C': tipo = Tipo.CABALLO; break;
            case 'T': tipo = Tipo.TORRE; break;
            case 'Q': tipo = Tipo.REINA; break;
        }

        posx = letrax - 'a';
        posy = letray - 'a';

        if (color.equals("BLANCO")) this.color = Color.BLANCO;
        else this.color = Color.NEGRO;
    }

    public Pieza(char letrax, char letray, String color) {
        tipo = Tipo.PEON;

        posx = letrax - 'a';
        posy = letray - 'a';

        if (color.equals("BLANCO")) this.color = Color.BLANCO;
        else this.color = Color.NEGRO;
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
