package Utility;

public interface ISprites<T,D>  {


    void update(float deltaTime);

    public void dispose();

    public T getImg();
    public D getPosition();





}
