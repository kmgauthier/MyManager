package mymanager;
import java.io.*;
/**
 *
 * @author Matt
 */
public class HealthData implements Serializable {
    
    private int calsBurned, calsConsumed;//creating private integers
    private int netCals;
    
    public HealthData(int newCalsBurned, int newCalsConsumed){//creating constructor HealthData
        addCalsBurned(newCalsBurned);
        addCalsConsumed(newCalsConsumed);
    }
    
    public HealthData(){
        calsBurned = 0;
        calsConsumed = 0;
        netCals = 0;
    }
    
    public void addCalsBurned(int newCalsBurned){//addCalsBurned method
        if(newCalsBurned >= 0){
            calsBurned += newCalsBurned;
            netCals -= newCalsBurned;
        } else {
            
        }
    }
    
    public void addCalsConsumed(int newCalsConsumed){//addCalsConsumed method
        if(newCalsConsumed >= 0){
            calsConsumed += newCalsConsumed;
            calcNetCals();
        } else {
            
        }
    }
    
    public int getCalsBurned(){//getCalsBurned method
        return calsBurned;
    }
    
    public int getCalsConsumed(){//getCalsConsumed method
        return calsConsumed;
    }
    
    public void calcNetCals(){//method to calculate net calories
        
        netCals = calsConsumed - calsBurned;
        
    }
    
    public int getNetCals(){//method to get Net Calories
        return netCals;
    }
    
    public static void write(HealthData data){//method to write health data to "health_storage.txt" file
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

    public static HealthData read(){//method to read data from text file
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
