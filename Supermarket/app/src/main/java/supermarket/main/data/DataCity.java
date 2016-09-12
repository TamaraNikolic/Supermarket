package supermarket.main.data;

import java.util.ArrayList;


public class DataCity {

   public String name;
    public ArrayList<String> estates;


    @Override
    public String toString() {
        return name + "" +estates.toString();
    }
}
