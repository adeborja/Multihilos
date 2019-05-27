import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by adeborja on 27/05/19.
 */
public class Productor implements Runnable{

    private Random random;
    private Mostrador mos;

    public Productor(Mostrador m)
    {
        random = new Random();
        mos = m;
    }

    public void colocarCaja() throws InterruptedException {
        long espera = random.nextInt(3000);
        if (espera < 1000) espera=1000;
        //else if(espera>3000) espera=3000;

        System.out.println("El productor espera "+ (double)espera/1000 +" segundos para colocar una caja en el mostrador.");

        TimeUnit.MILLISECONDS.sleep(espera);

        //incluir caja
        mos.ponerCaja();
    }

    @Override
    public void run() {
        try {
            while (true)
            {
                colocarCaja();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
