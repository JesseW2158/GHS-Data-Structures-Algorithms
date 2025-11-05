import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Menu implements Iterable {
	Entree[] menu;
	int size;
	public static final double MAXLOAD = .75;
	public static final Cuisine GARBAGE = new Cuisine(new HashSet<>(Set.of("GARBAGE")));

	public Menu() {
		menu = new Entree[10];
		size = 0;
	}

	public Cuisine add(Entree ent) {
		int index = ent.key.hashCode() % menu.length;

		do {
			if (menu[index] == null || menu[index].value.equals(GARBAGE)) {
				if (menu[index] == null)
					size++;
				menu[index] = ent;
				if (1.0 * size / menu.length >= MAXLOAD)
					rehash();
				return null;
			}
			if (menu[index].key.equals(ent.key)) {
				Cuisine temp = menu[index].value;
				menu[index].value = ent.value;
				return temp;
			}
			index = (index + 1) % menu.length;
		} while (true);

	}

	public Cuisine eat(Foontry foo) {
		if (foo == null)
			return null;
		int index = foo.hashCode() % menu.length;

		while (menu[index] != null) {
			if (menu[index].key.equals(foo)) {
				if (menu[index].value.equals(GARBAGE))
					return null;
				else {
					Cuisine eaten = menu[index].value;
					menu[index].value = GARBAGE;
					return eaten;
				}

			}
			index = (index + 1) % menu.length;
		}

		return null;

	}

	public Cuisine get(Foontry foo) {
		if (foo == null)
			return null;
		int index = foo.hashCode() % menu.length;
		while (menu[index] != null) {
			if (menu[index].key.equals(foo))
				return menu[index].value.equals(GARBAGE) ? null : menu[index].value;
			index = (index + 1) % menu.length;
		}
		return null;
	}

	public void rehash() {
		// makes a bigger menu; AND GET RID OF THE TOMBSTONES
		Entree[] newEntrees = new Entree[menu.length * 2];
		Menu newMenu = new Menu();
		newMenu.menu = newEntrees;
		for (Entree ent : menu) {
			if (ent == null || ent.value.equals(GARBAGE))
				continue;
			newMenu.add(ent);
		}
		this.size = newMenu.size;
		this.menu = newEntrees;
		System.out.println("hashy hash" + this.size);

	}
	// GET KEEP LOOKING UNTIL NULL

	// REMOVE WILL PUT A TOMBSTONE

	public String toString() {
		String output = "";
		int i = 0;
		for (Entree ent : menu) {
			if (ent != null) {
				output += i++ + "" + ent + "\n";
			}
		}

		return output;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return new foodIt();
	}

	private class foodIt implements Iterator {
		int index;

		public foodIt() {
			index = 0;
			moveToNext();

		}

		public void moveToNext() {
			while (index < menu.length && (menu[index] == null || menu[index].value.equals(GARBAGE))) {
				index++;
			}
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return index < menu.length;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			if (hasNext()) {
				Entree temp = menu[index++];
				moveToNext();
				return temp;
			}
			return null;
		}

	}
}
