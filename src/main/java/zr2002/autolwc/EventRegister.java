package zr2002.autolwc;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;

public class EventRegister {

	protected static String user;
	private static final Logger LOGGER = LogManager.getLogger();
	private static boolean run;
	

	public EventRegister() {
		

	}



	@SubscribeEvent
	public static void onSendChat(ClientChatEvent event) {
		if (event.getMessage().equalsIgnoreCase("/LWC")) {
			event.setCanceled(true);
			run = true;
			Minecraft.getInstance().player.sendMessage(new StringTextComponent("§4EasyLWC Mode Enabled (Type '/done' to finish or '/cancel' to cancel)"));
		}
		
		if(event.getMessage().equalsIgnoreCase("/done") && run) {
			event.setCanceled(true);
			copyToClipboard();
			run = false;
			Minecraft.getInstance().player.sendMessage(new StringTextComponent("§4Locks list copied to clipboard!"));
		}
		
		if(event.getMessage().equalsIgnoreCase("/cancel") && run) {
			event.setCanceled(true);
			run = false;
			Minecraft.getInstance().player.sendMessage(new StringTextComponent("§4EasyLWC Mode Disabled"));
			EasyLWC.instance.clearLock();
			
		}
	}
	
	@SubscribeEvent
	public static void loggedOut(PlayerLoggedOutEvent event) {
		run = false;
		EasyLWC.instance.clearLock();
	}

	@SubscribeEvent
	public static void onChat(ClientChatReceivedEvent event) {

		LOGGER.info(event.getMessage().getString());
		if (event.getMessage().getString().length() > 6) {

			if (event.getMessage().getString().substring(0, 12).equals("Notice: That") && run) {
				
				String test = event.getMessage().getString().substring(21).toLowerCase();
				
				if(test.contains("chest")) {
					int x = Minecraft.getInstance().player.getPosition().getX();
					int y = Minecraft.getInstance().player.getPosition().getY();
					int z = Minecraft.getInstance().player.getPosition().getZ();
					String pos = "X: " + x + " Y: " + y + " Z: " + z;
					Lock chest = new Lock("Chest",pos);
					EasyLWC.instance.addLock(chest);
					Minecraft.getInstance().player.sendMessage(new StringTextComponent("§bChest added to lock list!"));
				}
				
				else if(test.contains("door") && !test.contains("trapdoor")) {
					int x = Minecraft.getInstance().player.getPosition().getX();
					int y = Minecraft.getInstance().player.getPosition().getY();
					int z = Minecraft.getInstance().player.getPosition().getZ();
					String pos = "X: " + x + " Y: " + y + " Z: " + z;
					Lock door = new Lock("Door",pos);
					EasyLWC.instance.addLock(door);
					Minecraft.getInstance().player.sendMessage(new StringTextComponent("§bDoor added to lock list!"));
					
				}
				
				else if(test.contains("trapdoor")) {
					int x = Minecraft.getInstance().player.getPosition().getX();
					int y = Minecraft.getInstance().player.getPosition().getY();
					int z = Minecraft.getInstance().player.getPosition().getZ();
					String pos = "X: " + x + " Y: " + y + " Z: " + z;
					Lock trapdoor = new Lock("Trapdoor",pos);
					EasyLWC.instance.addLock(trapdoor);
					Minecraft.getInstance().player.sendMessage(new StringTextComponent("§bTrapdoor added to lock list!"));
				}
				
				else if(test.contains("furnace")) {
					int x = Minecraft.getInstance().player.getPosition().getX();
					int y = Minecraft.getInstance().player.getPosition().getY();
					int z = Minecraft.getInstance().player.getPosition().getZ();
					String pos = "X: " + x + " Y: " + y + " Z: " + z;
					Lock furnace = new Lock("Furnace",pos);
					EasyLWC.instance.addLock(furnace);
					Minecraft.getInstance().player.sendMessage(new StringTextComponent("§bFurnace added to lock list!"));
				}
				
				else if(test.contains("sign")) {
					int x = Minecraft.getInstance().player.getPosition().getX();
					int y = Minecraft.getInstance().player.getPosition().getY();
					int z = Minecraft.getInstance().player.getPosition().getZ();
					String pos = "X: " + x + " Y: " + y + " Z: " + z;
					Lock sign = new Lock("Sign",pos);
					EasyLWC.instance.addLock(sign);
					Minecraft.getInstance().player.sendMessage(new StringTextComponent("§bSign added to lock list!"));
				}
				
				else {
					Minecraft.getInstance().player.sendMessage(new StringTextComponent("§4Lock type not supported - contact mod developer (zr2002) for help."));
				}

			}

		}

	}
	
	private static void copyToClipboard() {
		
		ArrayList<Lock> locks = EasyLWC.instance.getLocks();
		
		String copy = "";
		for(int i = 0; i<locks.size();i++) {
			copy += "\n" + locks.get(i);
			
			
		}
		
		StringSelection stringSelection = new StringSelection(copy);
		System.setProperty("java.awt.headless", "false");
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		EasyLWC.instance.clearLock();

		
	
		
	}

}
