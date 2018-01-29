public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper sInstance;
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "PetsLog";


    // Table Names
    private static final String TABLE_PET = "pets";
    private static final String TABLE_GROWTH = "growths";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_PET_ID = "pet_id";
    private static String KEY_AMOUNT = "amount";


    // PET Table - column names
    private static final String KEY_NAME = "name";
    private static String KEY_SPECIES = "species";
    private static String KEY_SEX = "sex";
    private static String KEY_PICLOCATION = "picLocation";
    private static String KEY_PICBLOB = "picBlob";

    //GROWTH Table - column names
    private static String KEY_WEIGHT = "weight";
    private static String KEY_LENGTH = "length";


    // Table Create Statements
    // Pet table create statement
    private static final String CREATE_TABLE_PET = "CREATE TABLE "
            + TABLE_PET + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME + " TEXT, "
            + KEY_SPECIES + " TEXT, "
            + KEY_SEX + " TEXT, "
            + KEY_PICLOCATION + " TEXT, "
            + KEY_PICBLOB + " BLOB "+ ")";

    //Growth table create statement
    private static final String CREATE_TABLE_GROWTH = "CREATE TABLE "
            + TABLE_GROWTH + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_PET_ID + " INTEGER, "
            + KEY_LENGTH + " INTEGER, "
            + KEY_WEIGHT + " INTEGER, "
            + KEY_CREATED_AT + " DATETIME, "
            +" UNIQUE ("+KEY_ID+","+KEY_PET_ID+"),"
            + " FOREIGN KEY ("+KEY_PET_ID+") REFERENCES "+TABLE_PET+"("+KEY_ID+") ON DELETE CASCADE);";


    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PET);
        db.execSQL(CREATE_TABLE_GROWTH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        Log.d(TAG, "onUpgrade() from " + oldVersion + " to " + newVersion);
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROWTH);
        onCreate(db);
    }



    public long createPet(Pet pet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, pet.getName());
        values.put(KEY_SPECIES, pet.getSpecies());
        values.put(KEY_SEX, pet.getSex());
        values.put(KEY_PICLOCATION, pet.getPicLocation());
        values.put(KEY_PICBLOB, pet.getPicBlob());

        return db.insert(TABLE_PET, null, values);
    }

    public long createGrowth(Growth growth){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_PET_ID, growth.getPet_id());
        values.put(KEY_LENGTH, growth.getLength());
        values.put(KEY_WEIGHT, growth.getWeight());
        values.put(KEY_CREATED_AT, growth.getDate());

        return db.insert(TABLE_GROWTH,null,values);
    }

    public int updatePet(Pet pet) {
        SQLiteDatabase db = this.getWritableDatabase();
//        Log.d("update","update");
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, pet.getName());
        values.put(KEY_SPECIES, pet.getSpecies());
        values.put(KEY_SEX, pet.getSex());
        values.put(KEY_PICLOCATION, pet.getPicLocation());
        values.put(KEY_PICBLOB, pet.getPicBlob());
        // updating row
        return db.update(TABLE_PET, values, KEY_ID + " = ?",
                new String[] { String.valueOf(pet.getId()) });
    }


    public void deletePet(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PET, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    public int updateGrowth(Growth growth) {
        SQLiteDatabase db = this.getWritableDatabase();
//        Log.d("update","update");
        ContentValues values = new ContentValues();
        values.put(KEY_ID, growth.getId());
        values.put(KEY_PET_ID, growth.getPet_id());
        values.put(KEY_LENGTH, growth.getLength());
        values.put(KEY_WEIGHT, growth.getWeight());
        values.put(KEY_CREATED_AT, growth.getDate());
        // updating row
        return db.update(TABLE_GROWTH, values, KEY_PET_ID + " = ? and " + KEY_ID + " = ? ",
                new String[] {String.valueOf(growth.getPet_id()), String.valueOf(growth.getId())});
    }

    public void deleteGrowth(long pet_id, long id){
        SQLiteDatabase db = this.getWritableDatabase();
//        delete from GROWTH where pet_id='' and id =''
//        "SELECT  * FROM " + TABLE_GROWTH + " WHERE " + KEY_PET_ID + " = " + pet_id + " AND " + KEY_ID + " = " + id ;

        db.delete(TABLE_GROWTH, KEY_PET_ID + " = ? and " + KEY_ID + " = ? ",
                new String[] { String.valueOf(pet_id),String.valueOf(id) });
    }

    public int updateFeeding(Food feeding) {
        SQLiteDatabase db = this.getWritableDatabase();
//        Log.d("update","update");
        ContentValues values = new ContentValues();
        values.put(KEY_ID, feeding.getId());
        values.put(KEY_PET_ID, feeding.getPet_id());
        values.put(KEY_FOOD, feeding.getFood());
        values.put(KEY_FREQUENCY, feeding.getFrequency());
        values.put(KEY_SUPPLEMENT, feeding.getSupplement());
        values.put(KEY_AMOUNT, feeding.getAmount());
        values.put(KEY_FEEDING_START,feeding.getStartDate());
        // updating row
        return db.update(TABLE_FEEDING, values, KEY_PET_ID + " = ? and " + KEY_ID + " = ? ",
                new String[] {String.valueOf(feeding.getPet_id()), String.valueOf(feeding.getId())});
    }

    public void deleteFeeding(long pet_id, long id){
        SQLiteDatabase db = this.getWritableDatabase();
        //        delete from TABLE_FEEDING where pet_id='' and id =''

        db.delete(TABLE_FEEDING, KEY_PET_ID + " = ? and " + KEY_ID + " = ? ",
                new String[] { String.valueOf(pet_id),String.valueOf(id) });
    }


    /*
    * get single pet
    */
    public Pet getPet(long pet_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_PET + " WHERE "
                + KEY_ID + " = " + pet_id;

//        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Pet pet = new Pet();
        pet.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        pet.setName((c.getString(c.getColumnIndex(KEY_NAME))));
        pet.setSpecies((c.getString(c.getColumnIndex(KEY_SPECIES))));
        pet.setSex((c.getString(c.getColumnIndex(KEY_SEX))));
        pet.setPicLocation((c.getString(c.getColumnIndex(KEY_PICLOCATION))));
        pet.setPicBlob((c.getBlob(c.getColumnIndex(KEY_PICBLOB))));


        return pet;
    }




    public Growth getSingleGrowth(long pet_id, long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_GROWTH + " WHERE "
                + KEY_PET_ID + " = " + pet_id + " AND " + KEY_ID + " = " + id ;
//        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Growth growth = new Growth();

        growth.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        growth.setPet_id(c.getInt(c.getColumnIndex(KEY_PET_ID)));
        growth.setLength(c.getDouble(c.getColumnIndex(KEY_LENGTH)));
        growth.setWeight(c.getDouble(c.getColumnIndex(KEY_WEIGHT)));
        growth.setDate(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

        return growth;
    }

    public ArrayList<Growth> getAllGrowthsById(long pet_id){
        ArrayList<Growth> growths = new ArrayList<Growth>();
        String selectQuery = "SELECT  * FROM " + TABLE_GROWTH + " WHERE "
                + KEY_PET_ID + " = " + pet_id
                + " ORDER BY " +KEY_CREATED_AT + " ASC";
//        Log.e(LOG, selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if(c != null){
            if (c.moveToFirst()) {
                do {
                    Growth growth = new Growth();

                    growth.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                    growth.setPet_id(c.getInt(c.getColumnIndex(KEY_PET_ID)));
                    growth.setLength(c.getDouble(c.getColumnIndex(KEY_LENGTH)));
                    growth.setWeight(c.getDouble(c.getColumnIndex(KEY_WEIGHT)));
                    growth.setDate(c.getString(c.getColumnIndex(KEY_CREATED_AT)));


                    // adding to growth list
                    growths.add(growth);
                } while (c.moveToNext());
            }
        }


        return growths;
    }



    /**
     * getting all pets
     * */
    public ArrayList<Pet> getAllPets() {
        ArrayList<Pet> pets = new ArrayList<Pet>();
        String selectQuery = "SELECT  * FROM " + TABLE_PET;

//        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if(c != null){
            if (c.moveToFirst()) {
                do {
                    Pet pet = new Pet();
                    pet.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                    pet.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                    pet.setSpecies((c.getString(c.getColumnIndex(KEY_SPECIES))));
                    pet.setSex((c.getString(c.getColumnIndex(KEY_SEX))));
                    pet.setPicLocation((c.getString(c.getColumnIndex(KEY_PICLOCATION))));
                    pet.setPicBlob((c.getBlob(c.getColumnIndex(KEY_PICBLOB))));


                    // adding to pet list
                    pets.add(pet);
                } while (c.moveToNext());
            }
        }

        return pets;
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    @Override
    public void onConfigure(SQLiteDatabase db){
        db.setForeignKeyConstraintsEnabled(true);
    }


}
