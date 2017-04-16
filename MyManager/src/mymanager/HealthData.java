package mymanager;
import java.io.*;
/**
 *
 * @author Matt
 */
public class HealthData implements Serializable {
    
    private static final long serialVersionUID = -7346543021312279802L;

    
    private int calsBurned, calsConsumed;//creates integer for calories burned and calories consumed
    private int netCals;//creates integer for net calories
    
    public HealthData(int newCalsBurned, int newCalsConsumed){
        addCalsBurned(newCalsBurned);
        addCalsConsumed(newCalsConsumed);
    }
    
    public HealthData(){
        calsBurned = 0;
        calsConsumed = 0;
        netCals = 0;
    }
    
    public HealthData reset(){
        HealthData hd = new HealthData();
        //calsBurned = 0;
        //calsConsumed = 0;
        //netCals = 0;
        return hd;
    }
    
    public void addCalsBurned(int newCalsBurned){//creates addCalsBurned method
        if(newCalsBurned >= 0){
            calsBurned += newCalsBurned;
            netCals -= newCalsBurned;
        } else {
            
        }
    }
    
    public void addCalsConsumed(int newCalsConsumed){
        if(newCalsConsumed >= 0){
            calsConsumed += newCalsConsumed;
            calcNetCals();
        } else {
            
        }
    }
    
    public int getCalsBurned(){//get method for calories burned
        return calsBurned;
    }
    
    public int getCalsConsumed(){//get method for calories consumed
        return calsConsumed;
    }
    
    public void calcNetCals(){//method to calculate net calories
        
        netCals = calsConsumed - calsBurned;
        
    }
    
    public int getNetCals(){//method to get net calories
        return netCals;
    }
    
    public static void write(HealthData data){//method to write health data to text file
        OutputStream ops = null;
        ObjectOutputStream objOps = null;
        try{
            ops = new FileOutputStream("health_storage.txt");
            objOps = new ObjectOutputStream(ops);
            objOps.writeObject(data);
            objOps.flush();
            
            
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }        
        
    }

    public static HealthData read(){//method to read health data
        InputStream fileIs = null;
        ObjectInputStream objIs = null;
        HealthData data = null;
        try {
            fileIs = new FileInputStream("health_storage.txt");
            objIs = new ObjectInputStream(fileIs);
            HealthData nData = (HealthData) objIs.readObject();
            data = nData;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(objIs != null) objIs.close();
            } catch (Exception ex){
                 
            }
        }
        
        return data;
    }
    
}
