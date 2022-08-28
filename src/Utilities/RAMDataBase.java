package Utilities;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public abstract class RAMDataBase<DBRegType> {
    private HashMap<Integer, DBRegType> DB;
    private int nextIDToAssign;


    public RAMDataBase(HashMap<Integer, DBRegType> emptyDB) throws DEVELOPMENT_ERROR {
        if (emptyDB.size() != 0) {
            throw new DEVELOPMENT_ERROR("El \"HashMap<Integer, DBRegType> emptyDB\" recibido debe estar vacio. Para agregar valores hacerlo con el comando add o con el otro constructor \"RAMDataBase(HashMap<Integer, DBRegType> emptyDB, DBRegType newDBRecords[])\".");
        }
        this.nextIDToAssign = 0;
        this.DB = emptyDB;
    }
    public RAMDataBase(HashMap<Integer, DBRegType> emptyDB, DBRegType newDBRecords[]) {
        this(emptyDB);
        this.add(newDBRecords);
    }


    public DBRegType get(int objectID) {
        return this.DB.get(objectID);
    }
    public List<DBRegType> getRecords() {
        List<DBRegType> records = new ArrayList<DBRegType>();
        for (int DBRecordID:this.DB.keySet()) {
            DBRegType record = this.DB.get(DBRecordID);
            records.add(record);
        }
        return records;
    }

    public void update(int objectID, DBRegType newValue) {
        this.DB.replace(objectID, newValue);
    }

    public int add(DBRegType newDBRecord) {
        HashMap<Integer, DBRegType> DB = this.DB;
        DB.put(this.nextIDToAssign, newDBRecord);
        this.nextIDToAssign++;
        return this.nextIDToAssign - 1;
    }
    public void add(DBRegType newDBRecords[]) {
        for (DBRegType newDBRecord:newDBRecords) {
            this.add(newDBRecord);
        }
    }
}