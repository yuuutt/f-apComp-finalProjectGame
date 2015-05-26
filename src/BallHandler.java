
public interface BallHandler {

    public void hitLeftWall(Ball B);
    public void hitRightWall(Ball ball);
    public void collided(Ball b1, Ball b2);
    public void handleBall(Ball B);
    public void handleTick();

}
