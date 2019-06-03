/**
 * Created by adeborja on 27/05/19.
 */
public class Ejecutar {

    public static void main(String[] args)
    {
        Mostrador mostrador = new Mostrador();
        MyWaitNotify mwn = new MyWaitNotify();
        Productor productor = new Productor(mostrador, mwn);
        Consumidor consumidor = new Consumidor(mostrador, mwn);

        Thread t1 = new Thread(productor);
        Thread t2 = new Thread(consumidor);

        t1.start();
        t2.start();
    }

}
