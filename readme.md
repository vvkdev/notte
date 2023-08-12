# RecyclerView on Database example
## Src
Neco  
[SQLite База Данных на Андроид](https://www.youtube.com/watch?v=udnaDIWjamg)  
[Создаем приложение "БЛОКНОТ"](https://www.youtube.com/watch?v=tQot9NMbtiw)
## Differences
- new theme colors
- theme inheritance
- styled TextInputLayout w daynight background
- long content scroll
- custom scrollbar
- styled fab
- fragments
- jetpack navigation
- lazy init
- Note class
- show 1st line of content in RecyclerView
- sort notes
## Notes
- db viewer: run app > view > tool windows > app inspection > database inspector tab
- id = is in the BaseColumns
- db must be open before insert
## Steps
- sub package 'db'
- db/Note.kt
   ```
   data class Note (
      val title: String,
      val content: String
   )
- db/DbNotes.kt
   ```
   object DbNotes {
      const val DB_VERSION = 1
      const val DB_NOTES = "notes.db"
      const val TABLE_NOTES = "notes"
      const val COLUMN_TITLE = "title"
      const val COLUMN_CONTENT = "content"

      const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NOTES (" +
               "${BaseColumns._ID} INTEGER PRIMARY KEY," +
               "$COLUMN_TITLE TEXT," +
               "$COLUMN_CONTENT TEXT)"

      const val DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NOTES"
   }
- db/DbHelper.kt
   ```
   class DbHelper(context: Context) :
      SQLiteOpenHelper(context, DbNotes.DB_NOTES, null, DbNotes.DB_VERSION) {
      override fun onCreate(db: SQLiteDatabase?) {
         db?.execSQL(DbNotes.CREATE_TABLE)
      }

      override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
         db?.execSQL(DbNotes.DELETE_TABLE)
         onCreate(db)
      }
   }
- db/DbManager.kt
   ```
   class DbManager(context: Context) {
      private val dbHelper = DbHelper(context)
      private val db: SQLiteDatabase by lazy<SQLiteDatabase> { dbHelper.writableDatabase }

      fun insertToDb(title: String, content: String) {
         val values = ContentValues().apply {
               put(DbNotes.COLUMN_TITLE, title)
               put(DbNotes.COLUMN_CONTENT, content)
         }
         db.insert(DbNotes.TABLE_NOTES, null, values)
      }

      fun readDb(): MutableList<Note> {
         val dataList = mutableListOf<Note>()
         val cursor = db.query(
               DbNotes.TABLE_NOTES,
               null, null, null, null, null,
               DbNotes.COLUMN_TITLE
         )
         with(cursor) {
               while (moveToNext()) {
                  val title = cursor.getString(cursor.getColumnIndexOrThrow(DbNotes.COLUMN_TITLE))
                  val content = cursor.getString(cursor.getColumnIndexOrThrow(DbNotes.COLUMN_CONTENT))
                  dataList.add(Note(title, content))
               }
               cursor.close()
               return dataList
         }
      }

      fun closeDb() = dbHelper.close()
   }
- fragment.kt  
   onViewCreated:
   ```
   private val dbManager: DbManager by lazy<DbManager> { DbManager(requireActivity()) }
   // insert data
   .setOnClickListener { with(binding) {
      dbManager.insertToDb(edTitle.text.toString(), edContent.text.toString()) } }
   // read data
   .setOnClickListener {    
   tvDb.text = ""
   val dataList = dbManager.readDb()
   for (item in dataList) {
      tvTest.append(item.title + " " + item.content)
      tvTest.append("\n") } }
   ```
   onDestroy:
   ```
   dbManager.closeDb()