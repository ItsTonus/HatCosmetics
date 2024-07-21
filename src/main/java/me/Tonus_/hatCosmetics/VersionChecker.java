package me.Tonus_.hatCosmetics;

import me.Tonus_.hatCosmetics.networking.ModrinthAPIClient;
import org.jetbrains.annotations.NotNull;
import java.util.logging.Logger;


public class VersionChecker {
	private static final Logger LOGGER = Main.getInstance().getLogger();
	
	public static void checkForUpdates(String projectID) {
		String remoteVersion = ModrinthAPIClient.getLatestPluginVersion(projectID);
		if (remoteVersion == null) {
			LOGGER.warning("Failed to check for updates.");
			return;
		}

		String pluginVersion = getPluginVersion();

		int versionComparison = compareVersions(pluginVersion, remoteVersion);
		if (versionComparison < 0) {
			LOGGER.warning("A new version is available: " + remoteVersion);
			LOGGER.warning("You are currently running: " + pluginVersion);
			LOGGER.warning("Update here: https://modrinth.com/plugin/hatcosmetics/version/latest");
		} else if (versionComparison > 0) {
			LOGGER.warning("You are running an unknown version!");
			LOGGER.warning("Ensure you download the plugin from trusted sources.");
		}
	}

	private static @NotNull String getPluginVersion() {
		return Main.getInstance().getDescription().getVersion();
	}

	private static int compareVersions(@NotNull String v1, @NotNull String v2) {
		String[] v1Parts = v1.split("\\.");
		String[] v2Parts = v2.split("\\.");

		int length = Math.max(v1Parts.length, v2Parts.length);

		for (int i = 0; i < length; i++) {
			int v1Part = i < v1Parts.length ? Integer.parseInt(v1Parts[i]) : 0;
			int v2Part = i < v2Parts.length ? Integer.parseInt(v2Parts[i]) : 0;

			if (v1Part < v2Part) return -1;
			else if (v1Part > v2Part) return 1;
		}

		return 0;
	}
}
