
package maps;


public abstract class Map {
    
    int[][] map;
    
    public Map() {
        this.map = new int[][] {{0,0,1,0},{0,0,1,0},{0,0,1,0}};
    }

    public int[][] getMap() {
        return map;
    }    
    
    public boolean isFree(int x, int y) {
        return false;
    }
}
