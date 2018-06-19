public class Dijkstra {

    Field course;
    Vector3 start;
    Vector3 target;
    double[][] heights;
    ArrayList miniTargets = new ArrayList();

    public Dijktra (Field c, Ball ball, Hole hole){
        course = c;
        start = ball.getPosition;
        target = hole.getPosition;
        heights = translateToHeights(c.getMatrix());
    }

    public double[][] translateToHeights(Properties[][] matrix) {

        double[][] heights = new double[Gdx.graphics.getHeight()][Gdx.graphics.getWidth()];

        for(int i; i<Gdx.graphics.getHeight() ; i++){
            for(int m ; m<Gdx.graphics.getWidth() ; m++){
                heights[i][m] = matrix[i][m].getHeight();   }    }
        return heights;
    }



}