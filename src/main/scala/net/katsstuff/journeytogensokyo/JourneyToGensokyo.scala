/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Journey To Gensokyo Mod. Get the Source Code in github:
 * https://github.com/Katrix-/JTG
 *
 * Journey To Gensokyo is Open Source and distributed under the
 * a modifed Botania license: https://github.com/Katrix-/JTG/blob/master/LICENSE.md
 */
package net.katsstuff.journeytogensokyo

import net.katsstuff.journeytogensokyo.client.ClientProxy
import net.katsstuff.journeytogensokyo.handler.GuiHandler
import net.katsstuff.journeytogensokyo.lib.{LibMod, LibModJ}
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.{FMLCommonHandler, Mod, SidedProxy}
import net.minecraftforge.fml.relauncher.Side

@Mod(modid = LibMod.Id, name = LibMod.Name, version = LibMod.Version, modLanguage = "scala", dependencies = "require-after:danmakucore")
object JourneyToGensokyo {
	MinecraftForge.EVENT_BUS.register(CommonProxy)

	if(FMLCommonHandler.instance().getSide == Side.CLIENT) {
		MinecraftForge.EVENT_BUS.register(ClientProxy)
	}

	assert(LibMod.Id == LibModJ.ID)

	@SidedProxy(serverSide = LibMod.CommonProxy, clientSide = LibMod.ClientProxy, modId = LibMod.Id)
	var proxy: CommonProxy = _

	@EventHandler
	def preInit(event: FMLPreInitializationEvent): Unit = {
		proxy.registerDanmakuCrafting()
		proxy.registerRenderers()
	}

	@EventHandler
	def init(event: FMLInitializationEvent): Unit = {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, GuiHandler)
		proxy.registerEntities()
	}
}
