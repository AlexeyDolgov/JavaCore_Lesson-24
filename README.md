# JavaCore_Lesson-24
Java Core Practice: Cinema Project

[Проект Кінотеатр](https://github.com/AlexeyDolgov/JavaCore_Lesson-24/tree/master/JavaCore_Lesson-24/src/ua/lviv/lgs/task24).<br><br>
Даний проект має такі сутності: Days, Time, Movie, Seance, Schedule, Hall, Cinema.<br><br>
<i>enum Days</i>: містить дні тижня<br><br>
<i>Time</i>: int <i>min</i>, int <i>hour</i>; необхідно передбачити межі для цих полів (для min 0..60, для hour 0..24);<br><br>
<i>Movie</i>: String <i>title</i>, Time <i>duration</i>;<br><br>
<i>Seance</i>: Movie <i>movie</i>, Time <i>startTime</i>, Time <i>endTime</i>; в конструктор має надходити параметрами значення для
перших двох полів, третє поле повинне обчислюватись за формулою startTime + duration;<br><br>
<i>Schedule</i>: TreeSet \<Seance> (в полі пишемо Set \<Seance>, а в конструкторі вже new TreeSet \<Seance>()); містить методи:
addSeance(Seance), removeSeance (Seance);<br><br>
<i>Hall</i>: TreeMap\<Days, Schedule>, Time open, Time close; врахувати час відкриття і закриття залу при формуванні сеансів!<br><br>
<i>Cinema</i>: TreeSet\<Hall>, Time open, Time close;<br><br>
Main class: створення об'єкту Cinema; реалізовує меню, в якому виконується весь функціонал Cinema.<br><br>
Для кожного класу зробити адекватний toString, щоб все було читабельно і доступно. Супроводжуючі повідомлення і тому подібне. Там, де
потрібно, зробити compareTo(). Маєте якісь власні ідеї для розробки - будь-ласка. Це моделювання роботи кінотеатру, тому все що
наблизить програму до реальності вітається. <br><br>
