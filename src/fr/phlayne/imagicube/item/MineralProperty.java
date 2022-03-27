package fr.phlayne.imagicube.item;

public enum MineralProperty {

	WOOD("wood"), STONE("stone"), GOLD("gold"), IRON("iron"), DIAMOND("diamond"), NETHERITE("netherite"),
	CHAINMAIL("chainmail"), LEATHER("leather");
	// Was "wooden" and "golden" and netherite was diamond: check when decrafting

	private String name;

	MineralProperty(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
