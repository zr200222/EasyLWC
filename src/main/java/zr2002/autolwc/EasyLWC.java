package zr2002.autolwc;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.ClientGameSession;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.ForgeConfig.Client;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.ClientModLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("easylwc")
public class EasyLWC {
	
	public static EasyLWC instance;
	public static final String modid = "autolwc";
	private static final Logger logger = LogManager.getLogger(modid);
	private static ArrayList<Lock> locks = new ArrayList<Lock>();
	
	public EasyLWC() {
		
		instance = this;
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(EventRegister.class);
		
		
	}
	
	
	private void setup(final FMLCommonSetupEvent event) {
		
		logger.info("Initializing " + modid + "...");
		
		
		
		
		
	}
	
	private void clientRegistries(final FMLClientSetupEvent event) {
	}
	
	public ArrayList<Lock> getLocks() {
		return locks;
	}
	
	public void addLock(Lock lock) {
		locks.add(lock);
	}
	
	public void clearLock() {
		locks.clear();
	}

}


