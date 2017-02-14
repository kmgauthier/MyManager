package mymanager;
import java.io.*;
/**
 *
 * @author Matt
 */
public class HealthData implements Serializable {
    
    private int calsBurned, calsConsumed;
    private int netCals;
    
    public HealthData(int newCalsBurned, int newCalsConsumed){
        setCalsBurned(newCalsBurned);
        addCalsConsumed(newCalsConsumed);
    }
    
    public HealthData(){
        calsBurned = 0;
        calsConsumed = 0;
        netCals = 0;
    }
    
    public void setCalsBurned(int newCalsBurned){
        if(newCalsBurned >= 0){
            calsBurned = newCalsBurned;
            calcNetCals();
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
    
    public int getCalsBurned(){
        return calsBurned;
    }
    
    public int getCalsConsumed(){
        return calsConsumed;
    }
    
    public void calcNetCals(){
        
        netCals = calsConsumed - calsBurned;
        
    }
    
    public int getNetCals(){
        return netCals;
    }
    
    public static void write(HealthData data){
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

    public static HealthData read(){
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
