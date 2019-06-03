/**
 * Created by adeborja on 3/06/19.
 */
public class MyWaitNotify {

    Object objetoMonitorizado;
    boolean sePuedeCoger;
    boolean sePuedeColocar;

    public MyWaitNotify()
    {
        objetoMonitorizado = new Object();
        sePuedeCoger = false;
        sePuedeColocar = false;
    }

    public void doWaitCoger()
    {
        synchronized (objetoMonitorizado)
        {
            sePuedeCoger = false;

            while (!sePuedeCoger)
            {
                try
                {
                    objetoMonitorizado.wait();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            sePuedeCoger = false;
        }
    }

    public void doWaitColocar()
    {
        synchronized (objetoMonitorizado)
        {
            sePuedeColocar = false;

            while (!sePuedeColocar)
            {
                try
                {
                    objetoMonitorizado.wait();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            sePuedeColocar = false;
        }
    }




    public void doNotifyCoger()
    {
        synchronized (objetoMonitorizado)
        {
            sePuedeColocar=true;
            objetoMonitorizado.notify();
        }
    }

    public void doNotifyColocar()
    {
        synchronized (objetoMonitorizado)
        {
            sePuedeCoger=true;
            objetoMonitorizado.notify();
        }
    }

}
