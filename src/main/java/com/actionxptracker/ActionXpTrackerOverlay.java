package com.actionxptracker;

        import java.awt.Color;
        import java.awt.Dimension;
        import java.awt.Graphics2D;
        import java.awt.Point;
        import java.awt.Rectangle;
        import java.awt.image.BufferedImage;
        import lombok.AccessLevel;
        import lombok.Getter;
        import net.runelite.api.Experience;
        import static net.runelite.api.MenuAction.RUNELITE_OVERLAY_CONFIG;
        import net.runelite.api.Skill;
        import net.runelite.client.ui.FontManager;
        import net.runelite.client.ui.SkillColor;
        import static net.runelite.client.ui.overlay.OverlayManager.OPTION_CONFIGURE;
        import net.runelite.client.ui.overlay.OverlayPanel;
        import net.runelite.client.ui.overlay.components.ComponentOrientation;
        import net.runelite.client.ui.overlay.components.ImageComponent;
        import net.runelite.client.ui.overlay.components.LineComponent;
        import net.runelite.client.ui.overlay.components.PanelComponent;
        import net.runelite.client.ui.overlay.components.ProgressBarComponent;
        import net.runelite.client.ui.overlay.components.SplitComponent;

class ActionXpTrackerOverlay extends OverlayPanel
{
    private static final int BORDER_SIZE = 2;
    private static final int XP_AND_PROGRESS_BAR_GAP = 2;
    private static final int XP_AND_ICON_GAP = 4;
    private static final Rectangle XP_AND_ICON_COMPONENT_BORDER = new Rectangle(2, 1, 4, 0);

    private final PanelComponent iconXpSplitPanel = new PanelComponent();
    private final ActionXpTrackerPlugin plugin;
    private final ActionXpTrackerConfig config;

    @Getter(AccessLevel.PACKAGE)
    private final Skill skill;
    private final BufferedImage icon;

    ActionXpTrackerOverlay(
            ActionXpTrackerPlugin plugin,
            ActionXpTrackerConfig config,
            Skill skill,
            BufferedImage icon)
    {
        super(plugin);
        this.plugin = plugin;
        this.config = config;
        this.skill = skill;
        this.icon = icon;
        panelComponent.setBorder(new Rectangle(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));
        panelComponent.setGap(new Point(0, XP_AND_PROGRESS_BAR_GAP));
        iconXpSplitPanel.setBorder(XP_AND_ICON_COMPONENT_BORDER);
        iconXpSplitPanel.setBackgroundColor(null);
        addMenuEntry(RUNELITE_OVERLAY_CONFIG, OPTION_CONFIGURE, "XP Tracker overlay");
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        iconXpSplitPanel.getChildren().clear();

        //Setting the font to rs small font so that the overlay isn't huge
        graphics.setFont(FontManager.getRunescapeSmallFont());

//        final XpSnapshotSingle snapshot = plugin.getSkillSnapshot(skill);
//
//        final String leftStr = config.onScreenDisplayMode().getActionKey(snapshot);
//        final String rightNum = config.onScreenDisplayMode().getValueFunc().apply(snapshot);

//        final LineComponent xpLine = LineComponent.builder()
//                .left(leftStr + ":")
//                .right(rightNum)
//                .build();
//
//        final String bottomLeftStr = config.onScreenDisplayModeBottom().getActionKey(snapshot);
//        final String bottomRightNum = config.onScreenDisplayModeBottom().getValueFunc().apply(snapshot);

//        final LineComponent xpLineBottom = LineComponent.builder()
//                .left(bottomLeftStr + ":")
//                .right(bottomRightNum)
//                .build();
//
//        final SplitComponent xpSplit = SplitComponent.builder()
//                .first(xpLine)
//                .second(xpLineBottom)
//                .orientation(ComponentOrientation.VERTICAL)
//                .build();

//        final ImageComponent imageComponent = new ImageComponent(icon);
//        final SplitComponent iconXpSplit = SplitComponent.builder()
//                .first(imageComponent)
//                .second(xpSplit)
//                .orientation(ComponentOrientation.HORIZONTAL)
//                .gap(new Point(XP_AND_ICON_GAP, 0))
//                .build();

//        iconXpSplitPanel.getChildren().add(iconXpSplit);

        final ProgressBarComponent progressBarComponent = new ProgressBarComponent();

        progressBarComponent.setBackgroundColor(new Color(61, 56, 49));
        progressBarComponent.setForegroundColor(SkillColor.find(skill).getColor());

//        progressBarComponent.setLeftLabel(String.valueOf(snapshot.getStartLevel()));
//        progressBarComponent.setRightLabel(snapshot.getEndGoalXp() == Experience.MAX_SKILL_XP
//                ? "200M"
//                : String.valueOf(snapshot.getEndLevel()));
//
//        progressBarComponent.setValue(snapshot.getSkillProgressToGoal());

        panelComponent.getChildren().add(iconXpSplitPanel);
        panelComponent.getChildren().add(progressBarComponent);

        return super.render(graphics);
    }

    @Override
    public String getName()
    {
        return super.getName() + skill.getName();
    }
}