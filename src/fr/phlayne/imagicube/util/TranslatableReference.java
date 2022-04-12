package fr.phlayne.imagicube.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public enum TranslatableReference {

	RESOURCEPACK_KICK(
			"§3Please accept the downloading of the resource pack §b(Multiplayer " + (char) 8594 + " Edit "
					+ (char) 8594 + " Server Resource Packs: Enabled)", //
			"§3Veuillez accepter le téléchargement du pack de ressources §b(Multijoueur " + (char) 8594 + " Modifier "
					+ (char) 8594 + " Packs de ressources : Activés)", //
			"§3Por favor, acepte la descarga del paquete de recursos §b(Multijugador " + (char) 8594 + " Editar "
					+ (char) 8594 + " Paquete de recursos: Sí)"), //
	RESOURCEPACK_KICK_FAILED("§3Resourcepack download failed. Please contact an administrator.", //
			"§3Échec du téléchargement du pack de ressources. Veuillez contacter un administrateur.", //
			"§3La descarga del paquete de recursos falló. Por favor, póngase en contacto con un administrador"), //
	RESOURCEPACK_DOWNLOADING("§b§lDownloading resource pack...", //
			"§b§lTéléchargement du pack de ressources...", //
			"§b§lDescargamiento del paquete de recursos...");

	public String english;
	public String french;
	public String spanish;

	TranslatableReference(String english, String french, String spanish) {
		this.english = english;
		this.french = french;
		this.spanish = spanish;
	}

	TranslatableReference(String english, String french) {
		this.english = english;
		this.french = french;
		this.spanish = english;
	}

	TranslatableReference(String english) {
		this.english = english;
		this.french = english;
		this.spanish = english;
	}

	public String translate(String playerLocale) {
		return playerLocale.startsWith("fr_") ? this.french
				: playerLocale.startsWith("es_") ? this.spanish : this.english;
	}

	public String translate(Player player) {
		String playerLocale = player.getLocale();
		return translate(playerLocale);
	}

	public static String get(TranslatableReference translatableReference, String playerLocale) {
		return translatableReference.translate(playerLocale);
	}

	public static String getUnlocalizedName(Material material) {
		return (material.isBlock() ? "block." : "item.") + material.getKey().getNamespace()
				+ "."
				+ material.getKey().getKey();
	}
}