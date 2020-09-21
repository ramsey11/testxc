package dxc;
public class Demo1
{

    public static void main(String[] args)
    {
        TicketOffice s = new TicketOffice(new Object(), 20);
        Thread t1 = new Thread(s,"窗口1");
        Thread t2 = new Thread(s,"窗口2");
        Thread t3 = new Thread(s,"窗口3");
        t1.start();
        t2.start();
        t3.start();
        /*new Thread(ticketOffice, "窗口1").start();
        new Thread(ticketOffice, "窗口2").start();
        new Thread(ticketOffice, "窗口3").start();*/
    }
}

class TicketOffice implements Runnable {
    private Object object;

    private int ticketNum;

    public TicketOffice(java.lang.Object object, int ticketNum) {
        this.object = object;
        this.ticketNum = ticketNum;
    }

    @Override
    public void run() {
        while (ticketNum > 0) {
            synchronized (object) {
                if (ticketNum <= 0) {
                    System.out.println(Thread.currentThread().getName() + "没有票了");
                } else {
                    System.out.println(Thread.currentThread().getName() + "卖出了一张票，剩余" + --ticketNum + "张票");
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}