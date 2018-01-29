public class DatabaseHelper extends SQLiteOpenHelper { 

  private static DatabaseHelper sInstance;

  private static final String DATABASE_NAME = "database_name";
  private static final String DATABASE_TABLE = "table_name";
  private static final int DATABASE_VERSION = 1;

  public static synchronized DatabaseHelper getInstance(Context context) {
     
    // Use the application context, which will ensure that you 
    // don't accidentally leak an Activity's context.
    // See this article for more information: http://bit.ly/6LRzfx
    if (sInstance == null) {
      sInstance = new DatabaseHelper(context.getApplicationContext());
    }
    return sInstance;
  }
    
  /**
   * Constructor should be private to prevent direct instantiation.
   * make call to static method "getInstance()" instead.
   */
  private DatabaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }
}
}