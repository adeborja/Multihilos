import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by adeborja on 27/05/19.
 */
public class Consumidor implements Runnable{

    private Random random;
    private Mostrador mos;

    public Consumidor(Mostrador m)
    {
        random = new Random();
        mos = m;
    }


    public void obtenerCaja() throws InterruptedException {
        long espera = random.nextInt(6000);
        //if (espera < 1000) espera=1000;
        //else if(espera>3000) espera=3000;

        System.out.println("El consumidor espera "+ (double)espera/1000 +" segundos para coger una caja del mostrador.");

        TimeUnit.MILLISECONDS.sleep(espera);

        //obtener caja
        mos.cogerCaja();
    }

    @Override
    public void run() {
        try {
            while (true)
            {
                obtenerCaja();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
