

public class Tabuleiro {

    private String tabuleiro[][] = new String[3][3];
    
    

    public String[][] getTabuleiro() {
		return tabuleiro;
	}

	public void mostrarTabuleiro() {
		
		System.out.println("--------");
		
        for (int i = 0; i < this.tabuleiro.length; i++) {
        	System.out.print("|");
            for (int j = 0; j < this.tabuleiro.length; j++) {
                System.out.print(this.tabuleiro[i][j] + " ");
            }
            System.out.println("|");
        }
        
        System.out.println("--------");
        System.out.println("");
    }

    public void preencher() {
        for (int i = 0; i < this.tabuleiro.length; i++) {
            for (int j = 0; j < this.tabuleiro.length; j++) {
                this.tabuleiro[i][j] = "_";
            }
        }
    }

    public void jogar(int linha, int col, Jogador p) {
        boolean numVal = false;
        while (!numVal) {
            if (linha >= 0 && linha <= 2 && col >= 0 && col <= 2) {
                if (verificar(linha, col)) {
                    this.tabuleiro[linha][col] = p.getSimbolo();
                    numVal = true;
                } else {
                    System.out.println("Casa j� ocupada, tente novamente!");
                    return;
                }
            } else {
                System.out.println("N�mero inv�lido");
                return;
            }
        }
    }

    public void jogadaPC(Jogador pc) {
        boolean numVal = false;
        while (!numVal) {
            int lin = (int) (Math.random() * 3);
            int col = (int) (Math.random() * 3);
            if (verificar(lin, col)) {
                this.tabuleiro[lin][col] = pc.getSimbolo();
                numVal = true;
            }
        }
    }

    public boolean verificar(int l, int c) {
        return this.tabuleiro[l][c].equals("_");
    }

    public boolean verificaVitoria() {
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0].equals(tabuleiro[i][1]) && tabuleiro[i][1].equals(tabuleiro[i][2]) && !tabuleiro[i][0].equals("_")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[0][i].equals(tabuleiro[1][i]) && tabuleiro[1][i].equals(tabuleiro[2][i]) && !tabuleiro[0][i].equals("_")) {
                return true;
            }
        }
        if (tabuleiro[0][0].equals(tabuleiro[1][1]) && tabuleiro[1][1].equals(tabuleiro[2][2]) && !tabuleiro[0][0].equals("_")) {
            return true;
        }
        if (tabuleiro[0][2].equals(tabuleiro[1][1]) && tabuleiro[1][1].equals(tabuleiro[2][0]) && !tabuleiro[0][2].equals("_")) {
            return true;
        }
        return false;
    }

    public boolean espacoDisponivel(Tabuleiro tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro.verificar(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String[][] cloneTabuleiro() {
        String[][] clone = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                clone[i][j] = this.tabuleiro[i][j];
            }
        }
        return clone;
    }

    public void setTabuleiro(String[][] estado) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.tabuleiro[i][j] = estado[i][j];
            }
        }
    }
}

