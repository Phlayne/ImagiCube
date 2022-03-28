package fr.phlayne.imagicube.item;

public enum MineralProperties implements MineralProperty {

	WOOD("wood"), STONE("stone"), GOLD("gold"), IRON("iron"), DIAMOND("diamond"), NETHERITE("netherite"),
	CHAINMAIL("chainmail"), LEATHER("leather");

	private String name;

	MineralProperties(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
