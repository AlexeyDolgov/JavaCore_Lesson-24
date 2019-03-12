package ua.lviv.lgs.task24;

import java.io.Serializable;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Map.Entry;

@SuppressWarnings("serial")
public class Cinema implements Serializable {

	private String name;
	private TimeTable cinemaTimeTable;
	private TreeSet<Hall> cinemaHalls;

	public Cinema(String name) {
		this.name = name;
		this.cinemaTimeTable = new TimeTable();
		this.cinemaHalls = new TreeSet<Hall>();
	}

	public String getName() {
		return name;
	}

	public static Cinema inputCinema() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.print("Введите название кинотеатра: ");
		String name = scanner.nextLine();
		if (name.equals("")) {
			System.err.println("Название кинотеатра введено некорректно!");
			name = "-= Какой-то безымянный кинотеатр =-";
		}
		System.out.println(name.toString() + " успешно создан!\n");
		return new Cinema(name);
	}

	public void addHallToCinema() {
		Hall cinemaHall = Hall.inputHall();
		cinemaHalls.add(cinemaHall);
		System.out.println("Кинозал \"" + cinemaHall.getName() + "\" успешно добавлен в кинотеатр \"" + name + "\"!\n");
	}

	public Optional<Hall> getHallFromSet(Hall cinemaHall) {
		Optional<Hall> hallFound = cinemaHalls.stream().filter(entry -> entry.getName().equals(cinemaHall.getName()))
				.findFirst();

		return hallFound;
	}

	public void removeHallFromCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			cinemaHalls.remove(hallFound.get());
			System.out.println(
					"Кинозал \"" + hallFound.get().getName() + "\" успешно удалён из кинотеатра \"" + name + "\"!\n");
		} else {
			System.err.println("Кинозал \"" + cinemaHall.getName() + "\" отсутствует кинотеатре \"" + name + "\"!\n");
		}
	}

	public void addTimeTableToCinema() throws IllegalTimeFormatException {
		cinemaTimeTable.addTimeTableEntry();
		//System.out.println("График работы кинотеатра \"" + name + "\" успешно добавлен!\n");
	}

	public void removeTimeTableFromCinema() {
		cinemaTimeTable.removeTimeTableEntry();
		System.out.println("График работы кинотеатра \"" + name + "\" успешно удалён!\n");
	}

	public void addTimeTableToHallInCinema() throws IllegalTimeFormatException {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			hallFound.get().addTimeTableToHall();
			System.out.println("График работы для \"" + hallFound.get() + "\" успешно добавлен в кинотеатр \""
					+ name + "\"!\n");
		} else {
			System.err.println("Кинозал \"" + cinemaHall.getName() + "\" отсутствует кинотеатре \"" + name + "\"!\n");
		}
	}

	public void removeTimeTableFromHallInCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			hallFound.get().removeTimeTableFromHall();
			System.out.println("График работы для \"" + hallFound.get() + "\" успешно удалён из кинотеатра \""
					+ name + "\"!\n");
		} else {
			System.err.println("Кинозал \"" + cinemaHall.getName() + "\" отсутствует кинотеатре \"" + name + "\"!\n");
		}
	}

	public void addScheduleToHallInCinema() throws IllegalTimeFormatException {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			hallFound.get().addScheduleToHall();
			System.out.println("Расписание сеансов для \"" + hallFound.get() + "\" успешно добавлен в кинотеатр \""
					+ name + "\"!\n");
		} else {
			System.err.println("Кинозал \"" + cinemaHall.getName() + "\" отсутствует кинотеатре \"" + name + "\"!\n");
		}
	}

	public void removeScheduleFromHallInCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			hallFound.get().removeScheduleFromHall();
			System.out.println("Расписание сеансов для \"" + hallFound.get() + "\" успешно удалён из кинотеатра \""
					+ name + "\"!\n");
		} else {
			System.err.println("Кинозал \"" + cinemaHall.getName() + "\" отсутствует кинотеатре \"" + name + "\"!\n");
		}
	}

	public void addSeanceToScheduleInHallInCinema() throws IllegalTimeFormatException {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			Days day = Days.inputDay();
			if (day == null)
				return;

			Optional<Entry<Days, Schedule>> hallScheduleEntryFound = hallFound.get().getHallSchedule().entrySet()
					.stream().filter(entry -> entry.getKey().equals(day)).findFirst();

			if (hallScheduleEntryFound.isPresent()) {
				hallScheduleEntryFound.get().getValue().addSeance();
			} else
				System.err.println(day.toLiteral(true) + " отсутствует в расписании сеансов для \""
						+ hallFound.get() + "\" кинотеатра \"" + name + "\"!\n");
		} else {
			System.err.println("Кинозал \"" + cinemaHall.getName() + "\" отсутствует кинотеатре \"" + name + "\"!\n");
		}
	}

	public void removeSeanceFromScheduleInHallInCinema() throws IllegalTimeFormatException {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			Days day = Days.inputDay();
			if (day == null)
				return;

			Optional<Entry<Days, Schedule>> hallScheduleEntryFound = hallFound.get().getHallSchedule().entrySet()
					.stream().filter(entry -> entry.getKey().equals(day)).findFirst();

			if (hallScheduleEntryFound.isPresent()) {
				hallScheduleEntryFound.get().getValue().removeSeance();
			} else
				System.err.println(day.toLiteral(true) + " отсутствует в расписании сеансов для \""
						+ hallFound.get() + "\" кинотеатра \"" + name + "\"!\n");
		} else {
			System.err.println("Кинозал \"" + cinemaHall.getName() + "\" отсутствует кинотеатре \"" + name + "\"!\n");
		}
	}

	public void viewCinemaTimeTable() {
		System.out.print("Кинотеатр \"" + name + "\"\n");
		cinemaTimeTable.viewTimeTable();
		System.out.println();
	}

	public void viewCinemaHalls() {
		System.out.println("Перечень кинозалов кинотеатра \"" + name + "\":");
		cinemaHalls.forEach(System.out::println);
		System.out.println();
	}

	public void viewHallTimeTableInCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			hallFound.get().viewHallTimeTable();
		} else {
			System.err.println("Кинозал \"" + cinemaHall.getName() + "\" отсутствует кинотеатре \"" + name + "\"!\n");
		}
	}

	public void viewHallScheduleInCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			hallFound.get().viewHallSchedule();
		} else {
			System.err.println("Кинозал \"" + cinemaHall.getName() + "\" отсутствует кинотеатре \"" + name + "\"!\n");
		}
	}

	@Override
	public String toString() {
		if (name == "-= Какой-то безымянный кинотеатр =-") {
			return (String) name;
		} else
			return "Кинотеатр \"" + name + "\"";
	}
}
