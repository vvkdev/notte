# RecyclerView on Database example
## Src
Neco:  
- [Исходный код](https://neco-desarrollo.es/2021/04/sqlite-на-kotlin-урок-10)  
- [Урок 17: Создание базы данных SQLite](https://www.youtube.com/watch?v=tQot9NMbtiw)  
- [Урок 1: Создаем разметку экранов](https://youtube.com/watch?v=udnaDIWjamg)  
- [Урок 2: Доделываем разметку экранов и добавляем выбор фото](https://youtube.com/watch?v=_4zx41whaXs)  
- [Урок 3: Database Inspector, сохранения и выбор фото](https://youtube.com/watch?v=BS6Z4EkWUR4)  
- [Урок 4: RecyclerView Adapter](https://youtube.com/watch?v=hcYVBg--2s8)  
- [Урок 5: Передача данных на EditActivity](https://youtube.com/watch?v=HY9xLzRCYTY)  
- [Урок 6: Удаления заметок с помощью Swipe](https://youtube.com/watch?v=o1bFS29UUT4)  
- [Урок 7: Поиск по БД, добавляем SearchView](https://youtube.com/watch?v=eEOSob7frz4)  
- [Урок 8: Редактирования элементов](https://youtube.com/watch?v=j3Wr-RTxv-I)  
- [Урок 9: Сохранение времени создания заметки](https://youtube.com/watch?v=gP2gVzL2fp4)  
- [Урок 10: Многопоточность. Считывание и записывание с второстепенного потока](https://youtube.com/watch?v=qnQO3cnCShI)
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