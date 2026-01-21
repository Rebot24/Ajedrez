public class Emojis {

    public static class Pieza {
        boolean blanca;

        public Pieza(boolean blanca) {
            this.blanca = blanca;
        }

        public String simbolo() {
            return " ";
        }

        public boolean puedeMover(int fo, int co, int fd, int cd) {
            return false;
        }
    }

    public static class Peon extends Pieza {

        public Peon(boolean blanca) {
            super(blanca); //para saber si es blanca o negra
        }

        public boolean puedeMover(int fo, int co, int fd, int cd) {
            int direccion = blanca ? -1 : 1; // si es blanca -1 y sino 1
            return co == cd && fd - fo == direccion; //no puede mover de columna pero si de fila
        }

        public String simbolo() {
            return blanca ? "♙" : "♟";
        }
    }

    public static class Rey extends Pieza {

        public Rey(boolean blanca) {
            super(blanca);
        }

        public boolean puedeMover(int fo, int co, int fd, int cd){

        }

        public String simbolo(){
            return blanca ? "♔" : "♚";
        }
    }

    public static class Dama extends Pieza {

        public Dama(boolean blanca){
            super(blanca);
        }

        public boolean puedeMover(int fo, int co, int fd, int cd){

        }

        public String simbolo(){
            return blanca ? "♕" : "♛";
        }
    }

    public static class Torre extends Pieza {

        public Torre(boolean blanca) {
            super(blanca);
        }

        public boolean puedeMover(int fo, int co, int fd, int cd){

        }

        public String simbolo(){
            return blanca ? "♖" : "♜";
        }
    }

    public static class Alfil extends Pieza {

        public Alfil(boolean blanca) {
            super(blanca);
        }
        public boolean puedeMover(int fo, int co, int fd, int cd){

        }

        public String simbolo(){
            return blanca ? "♗" : "♝";
        }
    }

    public static class Caballo extends Pieza {

        public Caballo(boolean blanca) {
            super(blanca);
        }

        public boolean puedeMover(int fo, int co, int fd, int cd){

        }

        public String simbolo(){
            return blanca ? "♘" : "♞";
        }
    }









   
   
    
    
    
    
    
    
    
    




























}

