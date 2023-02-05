import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


// Konosuba Mark, $1 Sin Tax, 2 Punishment, Optional Required
class Main {

	public static void main(final String[] args) {
		final LocalDate currentDate = LocalDate.now();
      final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
      final String formattedDate = currentDate.format(formatter);

		final Scanner sc = new Scanner(System.in);
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_BLUE = "\u001B[34m";
		final String ANSI_GREEN = "\u001B[32m";
		final ArrayList<Party> array = new ArrayList<>();
		final ArrayList<Party> For = new ArrayList<>();
		final ArrayList<Party> Against = new ArrayList<>();
		System.out.print("\033[H\033[2J");
		System.out.flush();

		array.add(new Party("Religion", 46, "Blue"));
		array.add(new Party("Motivation", 35, "Blue"));
		array.add(new Party("Maintenance", 34, "Blue"));
		array.add(new Party("Mental", 28, "Red"));
		array.add(new Party("President", 21, "Green"));

		array.add(new Party("Education", 41, "Blue"));
		array.add(new Party("Sports", 30, "Blue"));
		array.add(new Party("Exercise", 25, "Blue"));
		array.add(new Party("Watch", 19, "Red"));

		array.add(new Party("Work", 38, "Green"));
		array.add(new Party("Sleep", 42, "Red"));

		array.add(new Party("Music", 26, "Red"));

		array.add(new Party("Charisma", 34, "Red"));
		array.add(new Party("Social", 9, "Red"));
		array.add(new Party("Fun", 11, "Red"));

		array.add(new Party("Programming", 37, "Blue"));
		array.add(new Party("Engineering", 14, "Blue"));
		array.add(new Party("Knowledge", 10, "Blue"));

		// array.add(new Party("Chaos", 10, "Red"));

		// sort(array);
		double sum = 0;

		System.out.println("ENTER = yes, Any = no");
		double blue = 0;
		double red = 0;

		for (int i = 0; i < array.size(); i++) {
			final int vote = array.get(i).getVotes();
			if (array.get(i).getCoalition().equals("Blue")) {
				System.out.print(ANSI_BLUE + array.get(i).getVotes() + " = " + array.get(i).getName() + ":" + ANSI_RESET);
			} else if (array.get(i).getCoalition().equals("Green")) {
				System.out.print(ANSI_GREEN + array.get(i).getVotes() + " = " + array.get(i).getName() + ":" + ANSI_RESET);
			} else {
				System.out.print(ANSI_RED + array.get(i).getVotes() + " = " + array.get(i).getName() + ":" + ANSI_RESET);
			}
			final String str = sc.nextLine();
			if (str.equals("")) {
				For.add(array.get(i));
				sum += vote;
				if (array.get(i).getCoalition().equals("Blue")) {
					blue += vote;
				} else if (array.get(i).getCoalition().equals("Red")) {
					red += vote;
				}
			} else if (isNumeric(str)) {
				array.get(i).setVotes(Integer.parseInt(str));
				For.add(array.get(i));

				sum += Integer.parseInt(str);
				if (array.get(i).getCoalition().equals("Blue"))
					blue += Integer.parseInt(str);
				else if (array.get(i).getCoalition().equals("Red"))
					red += Integer.parseInt(str);
			} else {
				Against.add(array.get(i));
			}
		}
		System.out.print("\033[H\033[2J");
		System.out.flush();

		final double percent = Math.round((sum / Party.getTotal()) * 100);
		System.out
				.println(
						ANSI_BLUE + "\nBlue Vote " + (int) Math.round((blue / Party.getBlueTotal()) * 100) + "% " + (int) blue + "/"
								+ (int) Party.getBlueTotal() + ANSI_RESET);
		System.out
				.println(ANSI_RED + "Red Vote " + (int) Math.round((red / Party.getRedTotal()) * 100) + "% " + (int) red + "/"
						+ (int) Party.getRedTotal() + ANSI_RESET);
		System.out.println("\nTotal: " + (int) percent + "% " + (int) sum + "/" + (int) Party.getTotal() + "\n");

		if (percent == 100) {
			System.out.print("Perfect Game = \"Free\"");

		} else if (percent >= 75) {
			System.out.print("Rhodium Day = \"Top 100 Anime\"");
		} else if (percent >= 67) {
			System.out.print(formattedDate + " Titanium Day = \"Abyss Pass\" " + percent);
		} else if (percent >= 60) {
			System.out.print(formattedDate + " Diamond Day = \"T Pass\" " + percent);
		} else if (percent >= 51) {
			System.out.println(formattedDate + " Golden Day = \"Rendo Pass\" " + percent);
final Die die = new Die();
			System.out.println("Random Done: " + die.roll());
		} else {
			System.out.print("Limbo\n");
		}
		sort(For);
		sort(Against);
		System.out.println("\n\nFor: " + (int) sum + " #" + For.size());
		for (final Party var : For) {
			switch (var.getCoalition()) {
				case "Blue":
					System.out.println(ANSI_BLUE + var.getName() + ": " +
							(Math.round((var.getVotes() / Party.getTotal()) * 100)) + "%" + " (" + var.getVotes() + ")" + ANSI_RESET);
					break;
				case "Green":
					System.out.println(ANSI_GREEN + var.getName() + ": " +
							(Math.round((var.getVotes() / Party.getTotal()) * 100)) + "%" + " (" + var.getVotes() + ")" + ANSI_RESET);
					break;
				case "Red":
					System.out.println(ANSI_RED + var.getName() + ": " +
							(Math.round((var.getVotes() / Party.getTotal()) * 100)) + "%" + " (" + var.getVotes() + ")" + ANSI_RESET);
					break;
				default:
					break;
			}
		}
		System.out.println("\nAgainst: " + (int) (Party.getTotal() - sum) + " #" + Against.size());
		for (final Party var : Against) {
			final String coalition = var.getCoalition();
			final String name = var.getName();
			final int votes = var.getVotes();
			final int percentage = (int) (Math.round((votes / Party.getTotal()) * 100));

			switch (coalition) {
				case "Blue":
					System.out.println(ANSI_BLUE + name + ": " + percentage + "%" + " (" + votes + ")" + ANSI_RESET);
					break;
				case "Green":
					System.out.println(ANSI_GREEN + name + ": " + percentage + "%" + " (" + votes + ")" + ANSI_RESET);
					break;
				case "Red":
					System.out.println(ANSI_RED + name + ": " + percentage + "%" + " (" + votes + ")" + ANSI_RESET);
					break;
				default:
					System.out.println(name + ": " + percentage + "%" + " (" + votes + ")");
			}
		}
		sc.close();
	}

	public static void sort(final ArrayList<Party> list) {

		list.sort((o1, o2) -> o1.compareTo(o2));
	}

	public static boolean isNumeric(final String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (final NumberFormatException e) {
			return false;
		}
	}
}