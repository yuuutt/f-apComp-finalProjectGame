import java.awt.Component;
import java.awt.Rectangle;
import java.util.ArrayList;


public class BallPhysics extends Thread {

    private ArrayList<Ball> balls;
    private Component parent;
    private Component boundary;
    private long lastTime;
    private long sleepTime;
    private boolean done;
    private boolean sideWalls = false;
    private BallHandler ballHandler = null;
    private long tickerMilliseconds = 1000;
    private long lastCreationTime = 0;
    private int minXVelocity = 10;

    public BallPhysics(Component parent, Component boundary, int framesPerSecond) {
    	
        this.parent = parent;
        this.boundary = boundary;
        sleepTime = 1000 / framesPerSecond;
        lastTime = System.currentTimeMillis();
        balls = new ArrayList<Ball>();
        done = false;
        start();

    }

    public synchronized int getNumberBalls() {
    	
        return balls.size();

    }

    public synchronized void setMinXVelocity(int vx) {

        minXVelocity = vx;

    }

    public synchronized void setSideWalls(boolean f) {

        sideWalls = f;

    }

    public synchronized void setBallHandler(BallHandler handler) {

        ballHandler = handler;

    }

    public synchronized void setTickerMilliseconds(long millis) {

        tickerMilliseconds = millis;

    }

    public synchronized void stopIt() {

        done = true;

    }

    private synchronized boolean getDone() {

        return done;

    }

    public synchronized void addBall(Ball ball) {

        balls.add(ball);

    }

    public synchronized void removeBall(Ball ball) {

        balls.remove(ball);

    }

 

    private synchronized void handleBalls(long currentTime) {

        double vx;
        for (int i = 0; i < balls.size(); i++)

            setBallPosition(currentTime - lastTime, balls.get(i));

        for (int i = 0; i < balls.size(); i++) {

            for (int j = i + 1; j < balls.size(); j++) {

                if (balls.get(i).intersectsBall(balls.get(j))) {

                    handleCollision(balls.get(i), balls.get(j),

                            (currentTime - lastTime) / 1000.0);

                    if (ballHandler != null)

                        ballHandler.collided(balls.get(i), balls.get(j));

                }
            }
        }

        if (minXVelocity > 0)

            for (int i = 0; i < balls.size(); i++) {

                vx = balls.get(i).getVelocityX();

                if (Math.abs(vx) < 1)

                    balls.get(i).setVelocityX(1);

                else if (Math.abs(vx) < minXVelocity)

                    balls.get(i).setVelocityX(vx * 1.2);

            }

        lastTime = currentTime;

    }

    private synchronized void removeBalls() {

        Ball ball;
        double x;
        double radius;
        Rectangle bounds = boundary.getBounds();
        if ((bounds.width == 0) || (bounds.height == 0))
            return;
        for (int i = 0; i < balls.size(); i++) {
            ball = balls.get(i);
            x = ball.getX();
            radius = ball.getRadius();
            if (x - radius > bounds.width) {
                if (ballHandler != null)
                    ballHandler.hitRightWall(ball);
                balls.remove(i);
                i--;
            } else if (x < -radius) {
                if (ballHandler != null)
                    ballHandler.hitLeftWall(ball);
                balls.remove(i);
                i--;
            }
        }
    }

    private void setBallPosition(long timeDiff, Ball ball) {

        double deltaTime = timeDiff / 1000.0;
        double oldX = ball.getX();
        double oldY = ball.getY();
        double radius = ball.getRadius();
        double velocityX = ball.getVelocityX();
        double velocityY = ball.getVelocityY();
        double newX = oldX + velocityX * deltaTime;
        double newY = oldY + velocityY * deltaTime;
        Rectangle bounds = boundary.getBounds();
        if ((bounds.width == 0) || (bounds.height == 0))
            return;
        ball.setX(newX);
        ball.setY(newY);
        if (sideWalls && (newX - radius <= 0)) {
            newX = 2 * radius - newX;
            ball.setVelocityX(Math.abs(velocityX));
            if (ballHandler != null)
                ballHandler.hitLeftWall(ball);
        }
        if (sideWalls && (newX + radius >= bounds.width)) {
            newX = 2 * (bounds.width - radius) - newX;
            ball.setVelocityX(-Math.abs(velocityX));
            if (ballHandler != null)
                ballHandler.hitRightWall(ball);
        }
        if (newY - radius <= 0) {
            newY = 2 * radius - newY;
            ball.setVelocityY(Math.abs(velocityY));
        }
        if (newY + radius >= bounds.height) {
            newY = 2 * (bounds.height - radius) - newY;
            ball.setVelocityY(-Math.abs(velocityY));
        }

        if (ballHandler != null)
            ballHandler.handleBall(ball);
    }

    private void handleCollision(Ball b1, Ball b2, double deltaT) {
    	
        double v1x = b1.getVelocityX();
        double v1y = b1.getVelocityY();
        double v2x = b2.getVelocityX();
        double v2y = b2.getVelocityY();
        double r1 = b1.getRadius();
        double r2 = b2.getRadius();
        double m1 = r1 * r1 * r1;
        double m2 = r2 * r2 * r2;
        double x1 = b1.getX();
        double y1 = b1.getY();
        double x2 = b2.getX();
        double y2 = b2.getY();
        double dist = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        if (dist == 0)
            return;
        double w1x = (x2 - x1) / dist;
        double w1y = (y2 - y1) / dist;
        double w2x = w1y;
        double w2y = -w1x;
        double a = v1x * w1x + v1y * w1y;
        double b = v1x * w2x + v1y * w2y;
        double c = v2x * w1x + v2y * w1y;
        double d = v2x * w2x + v2y * w2y;
        double e = (m1 * a + m2 * c) / (m1 + m2);
        double ap = -a + 2 * e;
        double cp = -c + 2 * e;
        v1x = ap * w1x + b * w2x;
        v1y = ap * w1y + b * w2y;
        v2x = cp * w1x + d * w2x;
        v2y = cp * w1y + d * w2y;
        b1.setVelocityX(v1x);
        b1.setVelocityY(v1y);
        b2.setVelocityX(v2x);
        b2.setVelocityY(v2y);
        x1 = x1 + 2 * deltaT * v1x;
        y1 = y1 + 2 * deltaT * v1y;
        x2 = x2 + 2 * deltaT * v2x;
        y2 = y2 + 2 * deltaT * v2y;
        b1.setX(x1);
        b1.setY(y1);
        b2.setX(x2);
        b2.setY(y2);
        double delta = r1 + r2 - dist;
        if (delta < 0)
            return;
    }

    public void run() {
    	
        long currentTime;
        while (!getDone()) {
            currentTime = System.currentTimeMillis();
            if (tickerMilliseconds != 0) {
                if (currentTime > lastCreationTime + tickerMilliseconds) {
                    lastCreationTime = currentTime;
                    if (ballHandler != null)
                        ballHandler.handleTick();
                }
            }
            handleBalls(currentTime);
            removeBalls();
            parent.repaint();
            try {
                sleep(sleepTime);
            } catch (InterruptedException e) {
            }
        }

    }

 

}
