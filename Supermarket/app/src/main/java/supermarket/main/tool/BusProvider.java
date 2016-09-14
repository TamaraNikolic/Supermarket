package supermarket.main.tool;

import com.squareup.otto.Bus;

/**
 * Created by cubesschool3 on 9/14/16.
 */
public class BusProvider  {

    private static Bus bus;

    private BusProvider(){

    }

    public static Bus getInstance(){
        if(bus==null){
            bus=new Bus();
        }
        return bus;
    }

}
