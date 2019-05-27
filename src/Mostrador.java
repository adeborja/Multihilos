import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by adeborja on 27/05/19.
 */
public class Mostrador {

    private List<Caja> mostrador;
    //private boolean mostradorOcupado;
    Semaphore semaphore;

    public Mostrador()
    {
        mostrador = new ArrayList<Caja>();
        //mostradorOcupado = false;
        semaphore = new Semaphore(1, true);
    }

    public int cajasDisponibles()
    {
        return mostrador.size();
    }

    public void ponerCaja() throws InterruptedException {
        boolean haSidoAnadida = false;

        while (!haSidoAnadida)
        {
            //Si hay sitio en el mostrador para colocar una caja
            if(cajasDisponibles() < 5)
            {
                semaphore.acquire();
                //mostradorOcupado = true;
                mostrador.add(new Caja());
                haSidoAnadida = true;

                System.out.println("Se ha colocado una caja. Ahora hay "+cajasDisponibles());

                synchronized (mostrador)
                {
                    mostrador.notify();
                }

                //mostradorOcupado = false;
                semaphore.release();
            }
            //Si el mostrador esta lleno
            else
            {
                System.out.println("El mostrador esta lleno. Se esperará para colocar una caja");
                synchronized (mostrador)
                {
                    mostrador.wait();
                }
            }
        }
    }

    public void cogerCaja() throws InterruptedException {
        boolean haSidoCogida= false;

        while (!haSidoCogida)
        {
            //Si hay alguna caja mostrador
            if(cajasDisponibles() > 0)
            {
                semaphore.acquire();
                //mostradorOcupado = true;
                mostrador.remove(0);
                haSidoCogida = true;

                System.out.println("Se ha cogido una caja. Ahora hay "+cajasDisponibles());

                synchronized (mostrador)
                {
                    mostrador.notify();
                }

                //mostradorOcupado = false;
                semaphore.release();
            }
            //Si el mostrador esta vacio
            else
            {
                System.out.println("El mostrador esta vacío. Se esperará para coger una caja");
                synchronized (mostrador)
                {
                    mostrador.wait();
                }
            }
        }
    }
}
