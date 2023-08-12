# RecyclerView on Database example
## Src
Neco  
[SQLite База Данных на Андроид](https://www.youtube.com/watch?v=udnaDIWjamg)  
[Создаем приложение "БЛОКНОТ"](https://www.youtube.com/watch?v=tQot9NMbtiw)
## Differences
- app icon
- new theme colors
- theme inheritance
- styled TextInputLayout w daynight background
- long content scroll
- custom draggable scrollbar
- styled square fab
- fragments
- jetpack navigation
- lazy init
- Note class
- show 1st line of content in RecyclerView
- sort notes
- adapter binding
## Notes
- db viewer: run app > view > tool windows > app inspection > database inspector tab
- id = is in the BaseColumns
- db must be open before insert
## Steps
### Db
- sub package 'db'
- add db/Note.kt
- add db/DbNotes.kt
- add db/DbHelper.kt
- add db/DbManager.kt
- add db to EditFragment.kt
   ```
   private val dbManager: DbManager by lazy { DbManager(requireContext()) }
   ```
   onViewCreated:
   ```
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
### RecyclerView
- add recyclerview to fragment_list.xml
   ```
   <androidx.recyclerview.widget.RecyclerView
         android:id="@+id/rv"
         tools:listitem="@layout/rv_item"
         />
- add rv_item.xml
- add NoteAdapter.kt