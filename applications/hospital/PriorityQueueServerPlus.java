package applications.hospital;


/**
 * Write a description of class PriorityQueueServerPlus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PriorityQueueServerPlus extends PriorityQueueServer implements SurgeryServerPlus{
    
    private int size = 0; 
    
    public int numPatients(){
        return size; 
    }
    
    public Patient transferPatient(){
        Patient p = super.pQ.removeMin();
        this.size--; 
        return p; 
    }
    
    public void distributePatients(SurgeryServerPlus s){
        Patient[] arr = new Patient[numPatients()];
        for(int i = 0; i < arr.length; i++){
            arr[i] = transferPatient(); 
        }
        for(int i = 0; i < arr.length; i++){
            if(i % 2 == 0){
                this.addWaiting(arr[i]);
            } else {
                s.addWaiting(arr[i]);
            }
        }
    }
    
    @Override
    public void addWaiting(Patient p){
        this.size++; 
        super.addWaiting(p); 
    }
    
    @Override
    public Patient operatePatient(int h){
        this.size--; 
        return super.operatePatient(h); 
    }
}
