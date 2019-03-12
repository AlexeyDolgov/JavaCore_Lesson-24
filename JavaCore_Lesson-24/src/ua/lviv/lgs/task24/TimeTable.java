package ua.lviv.lgs.task24;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import static java.util.stream.Collectors.toList;

@SuppressWarnings("serial")
public class TimeTable implements Serializable {
	
	private Map<Days, ArrayList<Time>> timeTable;

	public TimeTable() {
		this.timeTable = new TreeMap<Days, ArrayList<Time>>();
	}

	public Optional<Entry<Days, ArrayList<Time>>> findDayInTimeTable(Days day) {
		Optional<Entry<Days, ArrayList<Time>>> timeTableEntryFound = timeTable.entrySet().stream()
				.filter(entry -> entry.getKey().equals(day)).findFirst();
		return timeTableEntryFound;
	}

	public Time getOpeningTime(Days day) {
		return timeTable.get(day).get(0);
	}

	public Time getClosingTime(Days day) {
		return timeTable.get(day).get(1);
	}

	public void addTimeTableEntry() throws IllegalTimeFormatException {
		Days day = Days.inputDay();
		if (day == null)
			return;

		System.out.print("Время открытия - ");
		Time openingTime = Time.inputTime();

		System.out.print("Время закрытия - ");
		Time closingTime = Time.inputTime();

		if (Time.timeToMinutes(closingTime) <= Time.timeToMinutes(openingTime)) {
			System.err.println("Введённое время закрытия меньше или равно времени открытия!\n");
			return;
		}

		timeTable.put(day, new ArrayList<Time>(Arrays.asList(openingTime, closingTime)));
		System.out.println("Время работы в " + day.toLiteral(true) + " успешно добавлено в график работы!\n");
	}

	public void removeTimeTableEntry() {
		Days day = Days.inputDay();
		if (day == null)
			return;

		Optional<Entry<Days, ArrayList<Time>>> timeTableEntryFound = findDayInTimeTable(day);

		if (timeTableEntryFound.isPresent()) {
			timeTable.remove(timeTableEntryFound.get().getKey());
			System.out.println("Время работы в " + day.toLiteral(true) + " успешно удалено из графика работы!\n");
		} else {
			System.err.println(day.toLiteral(true) + " отсутствует в графике работы!\n");
		}
	}
	
	public Function<Entry<Days, ArrayList<Time>>, String> timeTableToString () {
		return entry -> entry.getKey().toLiteral(false) + " " + entry.getValue().get(0).toString()
				+ " - " + entry.getValue().get(1).toString();
	}
		
	public void viewTimeTable() {
		System.out.println("График работы:");
		timeTable.entrySet().stream().map(timeTableToString()).collect(toList()).forEach(System.out::println);
		System.out.println();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((timeTable == null) ? 0 : timeTable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeTable other = (TimeTable) obj;
		if (timeTable == null) {
			if (other.timeTable != null)
				return false;
		} else if (!timeTable.equals(other.timeTable))
			return false;
		return true;
	}
}
