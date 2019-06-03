import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by adeborja on 27/05/19.
 */
public class Productor implements Runnable{

    private Random random;
    private Mostrador mos;
    private MyWaitNotify senalizador;

    public Productor(Mostrador m, MyWaitNotify s)
    {
        random = new Random();
        mos = m;
        senalizador = s;
    }

    /*public void doWait()
    {
        senalizador.doWait();
    }

    public void doNotify()
    {
        senalizador.doNotify();
    }*/

    public void colocarCaja() throws InterruptedException {
        boolean haSidoColocada = false;

        long espera = random.nextInt(3000);
        //if (espera < 1000) espera=1000;
        //else if(espera>3000) espera=3000;

        System.out.println("El productor espera "+ (double)espera/1000 +" segundos para colocar una caja en el mostrador.");

        TimeUnit.MILLISECONDS.sleep(espera);

        while (!haSidoColocada)
        {
            //incluir caja
            haSidoColocada = mos.ponerCaja();

            if(!haSidoColocada)
            {
                senalizador.doWaitColocar();
            }
            else
            {
                senalizador.doNotifyColocar();
            }
        }
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
