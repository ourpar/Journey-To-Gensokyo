package net.katsstuff.journeytogensokyo.entity.living

import net.katsstuff.danmakucore.entity.living.ai.EntityAIWanderHover
import net.katsstuff.danmakucore.entity.living.{EntityDanmakuCreature, TouhouSpecies}
import net.minecraft.entity.INpc
import net.minecraft.entity.ai.{EntityAILookIdle, EntityAISwimming, EntityAIWander, EntityAIWatchClosest}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.DamageSource
import net.minecraft.world.World

class EntityReimuNPC(_world: World) extends EntityDanmakuCreature(_world) with INpc {

  setSize(0.5F, 1.2F)

  setSpecies(TouhouSpecies.HUMAN)
  setFlyingSpeed(0.3D)
  setGroundSpeed(0.2D)

  override def initEntityAI(): Unit = {
    tasks.addTask(0, new EntityAISwimming(this))
    tasks.addTask(4, new EntityAIWander(this, 1D, 120))
    tasks.addTask(4, new EntityAIWanderHover(this, 1D, 140))
    tasks.addTask(6, new EntityAIWatchClosest(this, classOf[EntityPlayer], 16F))
    tasks.addTask(7, new EntityAILookIdle(this))
  }

  override def isEntityInvulnerable(source: DamageSource): Boolean = true

  override def getIsInvulnerable: Boolean = true

  override def canBeLeashedTo(player: EntityPlayer): Boolean = false

}
