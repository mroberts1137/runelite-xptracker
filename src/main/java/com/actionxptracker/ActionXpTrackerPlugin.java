package com.actionxptracker;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Skill;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.StatChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Action XP Tracker"
)
public class ActionXpTrackerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ActionXpTrackerConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private ActionXpTrackerOverlay overlay;

	private int totalXpGained = 0;
	private int totalAttacks = 0;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Example started!");
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
		overlayManager.remove(overlay);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
		}
	}

	@Provides
    ActionXpTrackerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ActionXpTrackerConfig.class);
	}

	@Subscribe
	public void onExperienceChanged(StatChanged event)
	{
		Skill skill = event.getSkill();
		int xpGained = event.getXp();
		totalXpGained += xpGained;
		totalAttacks++;
	}

	public double getAverageXpPerAttack()
	{
		return totalAttacks == 0 ? 0 : (double) totalXpGained / totalAttacks;
	}

	public void resetValues()
	{
		totalXpGained = 0;
		totalAttacks = 0;
	}
}
