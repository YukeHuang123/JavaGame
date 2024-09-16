package au.edu.anu.comp6120.thu16_a3_d;

/**
 * location of each element
 */
public class Location {
    int row;
    int col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object obj) {
        if(((Location)obj).getRow() == this.row && ((Location)obj).getCol() == this.getCol()){
            return true;
        }
        return false;
    }
}
