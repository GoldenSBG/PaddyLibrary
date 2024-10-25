package PaddyLibrary.physics;

public class Gravity {
    private float gravity;
    private float maxFallSpeed;
    private float velocityY;

    public Gravity(float gravity, float maxFallSpeed) {
        this.gravity = gravity;
        this.maxFallSpeed = maxFallSpeed;
        this.velocityY = 0;
    }

    public void applyGravity() {
        velocityY += gravity;

        if (velocityY > maxFallSpeed) {
            velocityY = maxFallSpeed;
        }
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }
}
